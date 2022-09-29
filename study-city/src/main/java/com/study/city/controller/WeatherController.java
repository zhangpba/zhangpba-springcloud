package com.study.city.controller;

import com.github.pagehelper.PageInfo;
import com.study.city.entity.GroupBy;
import com.study.city.entity.weather.Weather;
import com.study.city.entity.weather.WeatherRequest;
import com.study.city.entity.weather.WeatherResult;
import com.study.city.service.IWeatherService;
import com.study.city.utils.PathUtils;
import com.study.city.utils.RedisUtils;
import com.study.starter.utils.DateUtils;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 天气预报控制类
 *
 * @author zhangpba
 * @date 2022-04-13
 */
@Api(value = "天气预报", tags = "天气预报")
@RestController
@EnableScheduling
@RequestMapping("/weather")
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private IWeatherService weatherService;

    @ApiOperation(value = "获取某一个地方的原始天气预报")
    @GetMapping(value = "/get-source")
    public ResponseMessage getSource(@ApiParam(name = "cityName", value = "城市名称", required = true) @RequestParam String cityName) {
        logger.info("入参：{}", cityName);
        WeatherResult weatherResult = weatherService.getWheatherResult(cityName);
        return ResponseMessage.success(weatherResult);
    }

    @ApiOperation(value = "获取某一个城市的6天的天气预报详情")
    @GetMapping(value = "/get-weathers")
    public ResponseMessage getWeathers(@ApiParam(name = "cityName", value = "城市名称", required = true) @RequestParam String cityName) {
        logger.info("入参：{}", cityName);
        List<Weather> weatherEveDays = weatherService.getWheatherByCity(cityName);
        return ResponseMessage.success(weatherEveDays);
    }

    @ApiOperation(value = "保存天气预报详情 到redis")
    @GetMapping(value = "/save-weathers")
    public ResponseMessage saveWeathers(@ApiParam(name = "cityName", value = "城市名称", required = true) @RequestParam String cityName) {
        logger.info("入参：{}", cityName);
        List<Weather> weatherEveDays = weatherService.getWheatherByCity(cityName);
        if (weatherEveDays != null) {
            redisUtils.hmSet("weather", cityName, weatherEveDays.toString());
        }
        return ResponseMessage.success("保存成功");
    }

    @ApiOperation(value = "分页获取天气预报")
    @PostMapping(value = "/getWeatherByPage")
    public ResponseMessage getWeatherByPage(@RequestBody WeatherRequest weatherRequest) {
        logger.info("pageNum:{}; pageSize:{},startDate,{}", weatherRequest.getPageNum(), weatherRequest.getPageSize(), weatherRequest.getStartDate());
        String startDate = null;
        String endDate = null;
        if (weatherRequest.getStartDate() != null) {
            startDate = DateUtils.format(weatherRequest.getStartDate(), DateUtils.YYYY_MM_DD);
        }
        if (weatherRequest.getEndDate() != null) {
            endDate = DateUtils.format(weatherRequest.getEndDate(), DateUtils.YYYY_MM_DD);
        }
        PageInfo result = weatherService.getWeatherByPage(weatherRequest.getPageNum(), weatherRequest.getPageSize(), startDate, endDate, weatherRequest.getCityName());
        return ResponseMessage.success(result);
    }

    @ApiOperation(value = "获取、保存所有城市天气预报")
    @GetMapping(value = "/all-city-weathers")
    public ResponseMessage allCityWeathers() {
        logger.info("手动调用-天气预报数据入库 start...");
        String result = weatherService.saveAllCityWeathers();
        return ResponseMessage.success(result);
    }

    @ApiOperation(value = "天气预报所需要的城市名称集合")
    @GetMapping(value = "/getAllCityForWeather")
    public ResponseMessage getAllCityForWeather() {
        logger.info("天气预报所需要的城市名称集合 start...");
        Set<String> citys = weatherService.getAllCityForWeather();
        return ResponseMessage.success(citys);
    }

    /**
     * 天气预报更换服务 2022-09-29
     */
    // @Scheduled(cron = "*/5 * * * * *")  // 每隔5秒执行一次
    // @Scheduled(cron = "${module.weather.syn-cron}")    // 每天21点01
    public void synWeathers() {
        logger.info("定时任务调度-天气预报数据入库 start...");
        allCityWeathers();
        logger.info("定时任务调度-天气预报数据入库 end...");
    }

    /**
     * 利用模版生成word文档
     *
     * @param filePath
     * @return
     */
    @ApiOperation(value = "利用模版生成word文档")
    @RequestMapping(value = "/word", method = RequestMethod.GET)
    public ResponseMessage word(@ApiParam(name = "filePath", value = "生成文件路径（例：/opt/）", required = true) @RequestParam String filePath,
                                @ApiParam(name = "cityName", value = "城市名称", required = true) @RequestParam String cityName) {
        logger.info("生成{}天气预报的word文档的路径: {}", cityName, filePath);
        // 模板路径
        String tempPath = PathUtils.getSpecific("word") + "weather.doc";
        String date = DateUtils.format(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS);
        // 生成文件路径+名称
        String targetPath = filePath + cityName + "天气预报" + date + ".doc";
        // 根据模板将数据写入word
        weatherService.createWord(cityName, tempPath, targetPath);
        return ResponseMessage.success(targetPath);
    }

    /**
     * 生成excle文档
     *
     * @param response
     * @return
     */
    @ApiOperation(value = "生成excle文档，直接在浏览器中请求可以下载生成的文档")
    @GetMapping("/export")
    public void export(@ApiParam(name = "cityName", value = "城市名称", required = false) @RequestParam String cityName,
                       @ApiParam(name = "startDate", value = "开始时间", required = false) @RequestParam String startDate,
                       @ApiParam(name = "endDate", value = "结束时间", required = false) @RequestParam String endDate,
                       HttpServletResponse response) {
        weatherService.export(response, cityName, startDate, endDate);
    }

    /**
     * 扇形统计图
     *
     * @param cityName
     * @param startDate
     * @param endDate
     * @return
     */
    @ApiOperation(value = "根据城市的天气类型分组，查询在某段时间内数据")
    @GetMapping(value = "/getGroupByType")
    public ResponseMessage getGroupByType(@ApiParam(name = "cityName", value = "城市名称", required = false) @RequestParam String cityName,
                                          @ApiParam(name = "startDate", value = "开始时间", required = false) @RequestParam String startDate,
                                          @ApiParam(name = "endDate", value = "结束时间", required = false) @RequestParam String endDate) {

        List<GroupBy> maps = weatherService.getGroupByType(cityName, startDate, endDate);
        return ResponseMessage.success(maps);
    }
}
