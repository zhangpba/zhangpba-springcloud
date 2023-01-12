package com.study.city.data.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.data.constant.FeeApiUrl;
import com.study.city.data.entity.GroupBy;
import com.study.city.data.entity.area.Province;
import com.study.city.data.entity.weather.Weather;
import com.study.city.data.entity.weather.WeatherResult;
import com.study.city.data.mapper.ProvinceMapper;
import com.study.city.data.mapper.WeatherMapper;
import com.study.city.data.service.IAreaService;
import com.study.city.data.service.IWeatherService;
import com.study.city.utils.excle.ExcleUtils;
import com.study.common.utils.DateUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class WeatherServiceImpl implements IWeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherMapper weatherMapper;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private ProvinceMapper provinceMapper;

    /**
     * 解析原始天气预报
     *
     * @param cityName 城市名称
     * @return
     */
    public WeatherResult getWheatherResult(String cityName) {
        logger.info("{}的天气预报 start...", cityName);

        String url = FeeApiUrl.WEATHER_URL + cityName;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        logger.info("{}的天气预报 返回: {}", cityName, responseEntity);
        HttpStatus statusCode = responseEntity.getStatusCode();
        if (!HttpStatus.OK.equals(statusCode)) {
            logger.error("请求失败！");
        }
        String body = responseEntity.getBody();
        JSONObject jsonBody = JSONObject.parseObject(body);

        // 返回原样集合
        WeatherResult weatherResult = new WeatherResult();

        // 返回集合
        List<Weather> weatherList = new ArrayList<>();

        String status = jsonBody.getString("status");
        if ("1000".equals(status)) {
            // 天气概况
            JSONObject data = jsonBody.getJSONObject("data");
            String city = data.getString("city");
            String ganmao = data.getString("ganmao");
            Integer wendu = data.getInteger("wendu");
            weatherResult.setCity(city);
            weatherResult.setGanmao(ganmao);
            weatherResult.setWendu(wendu);

            JSONArray forecast = data.getJSONArray("forecast");
            JSONObject yesterday = data.getJSONObject("yesterday");

            // 昨天天气
            Weather yesterdayWeather = new Weather();
            String fl = yesterday.getString("fl");
            if (fl != null && fl.contains("<![CDATA[") && fl.contains("]]>")) {
                fl = this.matcher(fl);
            }
            yesterdayWeather.setFl(fl);
            yesterdayWeather.setHigh(yesterday.getString("high"));
            yesterdayWeather.setFx(yesterday.getString("fx"));
            yesterdayWeather.setLow(yesterday.getString("low"));
            yesterdayWeather.setType(yesterday.getString("type"));

            yesterdayWeather.setDate(DateUtils.format(DateUtils.getYesterday(), DateUtils.YYYY_MM_DD));
            yesterdayWeather.setCity(city);
            yesterdayWeather.setUpdateDate(new Date());
            yesterdayWeather.setCreateDate(new Date());
            yesterdayWeather.setCreateBy("syn-admin");
            yesterdayWeather.setUpdateBy("syn-admin");
            weatherResult.setYesterday(yesterdayWeather);

            // 天气明细
            // 获取未来5天的明细数据
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i < forecast.size(); i++) {
                calendar.add(Calendar.DATE, i);
                Date timestamp = calendar.getTime();
                calendar.add(Calendar.DATE, -i);
                JSONObject weatherJson = (JSONObject) forecast.get(i);

                String fengli = weatherJson.getString("fengli");
                if (fengli != null && fengli.contains("<![CDATA[") && fengli.contains("]]>")) {
                    fengli = this.matcher(fengli);
                }
                String fengxiang = weatherJson.getString("fengxiang");
                String high = weatherJson.getString("high");
                String low = weatherJson.getString("low");
                String type = weatherJson.getString("type");

                Weather weather = new Weather();
                weather.setDate(DateUtils.format(timestamp, DateUtils.YYYY_MM_DD));
                weather.setFl(fengli);
                weather.setFx(fengxiang);
                weather.setHigh(high);
                weather.setLow(low);
                weather.setType(type);
                weather.setUpdateDate(new Date());
                weather.setCreateDate(new Date());
                weather.setCreateBy("syn-admin");
                weather.setUpdateBy("syn-admin");
                weather.setCity(city);
                if (i == 0) {
                    weather.setWarn(ganmao);
                }
                weatherList.add(weather);
            }
            weatherResult.setForecast(weatherList);
        } else {
            // 请求失败
            logger.error("没有返回正常的天气预报信息！");
            return null;
        }
        return weatherResult;
    }

    /**
     * 利用正则表达式提取风力字符串
     *
     * @param str 原始数据：<![CDATA[1级]]>
     * @return 提取后的数据：[1级]
     * @auther zhangpba
     * @date 2022-05-05
     */
    private String matcher(String str) {
        String fl = null;
        try {
            Pattern ptn = Pattern.compile("(^<!\\[CDATA)?(\\[[1-9][^\\x00-\\xff]\\])?(.*)");
            Matcher matcher = ptn.matcher(str);
            if (matcher.matches()) {
                // <![CDATA
                String prefix = matcher.group(1);
                // [1级]
                fl = matcher.group(2);
                // ]>
                String suffix = matcher.group(3);
            }
        } catch (PatternSyntaxException e) {
            logger.error("提取风力数据异常：{}", e.getMessage());
        } finally {
            return fl;
        }
    }

    /**
     * 根据城市名称查询天气预报明细
     *
     * @param cityName 城市名称
     * @return
     */
    @Override
    public List<Weather> getWheatherByCity(String cityName) {
        WeatherResult weatherResult = getWheatherResult(cityName);
        if (weatherResult != null) {
            Weather yesterday = weatherResult.getYesterday();
            List<Weather> forecast = weatherResult.getForecast();
            forecast.add(0, yesterday);
            return forecast;
        } else {
            return null;
        }
    }

    /**
     * 分页查询
     *
     * @param pageNum   页码
     * @param pageSize  页面大小
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param cityName  城市名称
     * @return
     */
    @Override
    public PageInfo getWeatherByPage(int pageNum, int pageSize, String startDate, String endDate, String cityName) {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, String> map = new HashMap<>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("city", cityName);
        List<Weather> weatherList = weatherMapper.getWeather(map);
        PageInfo<Weather> pageInfo = new PageInfo<>(weatherList);
        return pageInfo;
    }


    /**
     * 根据日期和城市名查询
     *
     * @param cityName 城市名称
     * @param date     日期
     * @return
     */
    @Override
    public List<Weather> getWeatherByCityAndDate(String cityName, String date) {
        Map<String, String> map = new HashMap<>();
        map.put("city", cityName);
        map.put("date", date);
        List<Weather> weatherList = weatherMapper.getWeatherByCityAndDate(map);
        return weatherList;
    }

    /**
     * 获取所有城市的天气预报，并存入数据库中
     *
     * @return
     */
    @Override
    public String saveAllCityWeathers() {
        int success = 0;
        int fail = 0;

        StringBuffer successList = new StringBuffer();
        StringBuffer failList = new StringBuffer();

        // 所有天气预报需要的城市
        Set<String> cityNameList = this.getAllCityForWeather();

        for (String name : cityNameList) {
            // 如果城市名称为空不进行请求
            if (name == null || name.isEmpty()) {
                continue;
            }
            String cityName = "";
            if (name.contains("市")) {
                cityName = name.replace("市", "");
            } else {
                cityName = name;
            }
            List<Weather> weatherList = this.getWheatherByCity(cityName);
            if (weatherList != null) {
                // 解析成功
                this.batchAddWeathers(weatherList);
                successList.append(cityName).append("、");
                success++;
            } else {
                // 解析失败
                fail++;
                failList.append(cityName).append("、");
            }
        }
        logger.info("成功城市名称：{}", successList);

        String massage = "同步城市天气预报！成功：" + success
                + "条，失败：" + fail + "条,总共："
                + (success + fail) + "条！";
        if (!failList.toString().isEmpty()) {
            logger.info("失败城市名称：{}", failList);
            massage = massage + ">>>> 失败城市名称:" + failList;
        } else {
            massage = massage + "全部同步成功，没有同步失败的城市！";
            logger.info(massage);
        }
        return massage;
    }

    /**
     * 获取所有天气预报所需的城市名称
     *
     * @return 城市名称
     */
    @Override
    public Set<String> getAllCityForWeather() {
        Set<String> cityNames = new HashSet<>();
        // 市名称
        List<String> cityNameList = areaService.eveCityNames();
        for (String cityName : cityNameList) {
            cityNames.add(cityName);
        }
        // 直辖市的名称
        List<String> crowncityNameList = areaService.eveAreaNames();
        for (String crownCityName : crowncityNameList) {
            if (crownCityName != null && crownCityName.isEmpty() && crownCityName.contains("市")) {
                cityNames.add(crownCityName);
            }
        }
        // 直辖省名称
        List<Province> provinces = provinceMapper.getAllProvincelist();
        for (Province p : provinces) {
            if (p.getName().contains("市")) {
                String cityName = p.getName().replace("市", "");
                cityNameList.add(cityName);
            } else {
                continue;
            }
        }
        return cityNames;
    }


    /**
     * 批量增加
     *
     * @param weatherList
     */
    @Override
    public void batchAddWeathers(List<Weather> weatherList) {
        if (weatherList != null) {
            for (Weather weather : weatherList) {
                List<Weather> list = getWeatherByCityAndDate(weather.getCity(), weather.getDate());
                if (list == null || list.isEmpty()) {
                    // 新增
                    weatherMapper.addWeather(weather);
                } else {
                    // 修改
                    weatherMapper.updateWeather(weather);
                }
            }
        }
    }

    @Override
    public String createWord(String cityName, String tempPath, String targetPath) {
        List<Weather> weathers = getWheatherByCity(cityName);
        Weather weather = weathers.get(1);
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(tempPath);
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
            // 把range范围内的${reportDate}替换为当前的日期
            range.replaceText("${reportDate}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            range.replaceText("${high}", weather.getHigh());
            range.replaceText("${low}", weather.getLow());
            range.replaceText("${type}", weather.getType());
            range.replaceText("${city}", weather.getCity());
            range.replaceText("${warn}", weather.getWarn());
            os = new FileOutputStream(targetPath);
            // 把doc输出到输出流中
            doc.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭输入流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭输出流
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return targetPath;
    }

    /**
     * execle导出天气预报
     *
     * @param response  返回请求
     * @param cityName  城市名称
     * @param startDate 开始时间
     * @param endDate   结束时间
     */
    @Override
    public void export(HttpServletResponse response, String cityName, String startDate, String endDate) {
        List<List<Object>> sheetDataList = new ArrayList<>();
        // 表头数据
        List<Object> head = Arrays.asList("城市", "日期", "温馨提示", "天气类型", "最高温度", "最低温度", "风向", "风力");
        sheetDataList.add(head);

        Map<String, String> map = new HashMap<>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("city", cityName);
        List<Weather> weathers = weatherMapper.getWeather(map);
        for (Weather weather : weathers) {
            List<Object> weatherObject = new ArrayList<>();
            weatherObject.add(weather.getCity());
            weatherObject.add(weather.getDate());
            weatherObject.add(weather.getWarn());
            weatherObject.add(weather.getType());
            weatherObject.add(weather.getHigh());
            weatherObject.add(weather.getLow());
            weatherObject.add(weather.getFx());
            weatherObject.add(weather.getFl());
            sheetDataList.add(weatherObject);
        }
        if (cityName == null || cityName.isEmpty()) {
            cityName = "全国";
        }
        // 导出数据
        ExcleUtils.export(response, cityName + "天气预报", sheetDataList);
    }

    /**
     * 查询在某段时间内城市的天气类型分组数据
     *
     * @param cityName  城市名称
     * @param startDate 开始时间
     * @param endDate   结束时间
     */
    public List<GroupBy> getGroupByType(String cityName, String startDate, String endDate) {
        Map<String, String> map = new HashMap<>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("city", cityName);
        return weatherMapper.getGroupByType(map);
    }

}