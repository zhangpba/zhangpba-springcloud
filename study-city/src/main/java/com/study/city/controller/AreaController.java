package com.study.city.controller;

import com.study.city.entity.area.Area;
import com.study.city.entity.area.City;
import com.study.city.entity.area.Province;
import com.study.city.service.IAreaService;
import com.study.city.service.IProvinceService;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 区域数据
 *
 * @author zhangpba
 * @date 2022-04-15
 */
@Api(value = "区域数据", tags = "区域数据")
@RestController
@RequestMapping("/area")
public class AreaController {
    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IProvinceService provinceService;

    @ApiOperation(value = "初始化入库-全国各个城市区域数据")
    @GetMapping(value = "/initArea")
    public ResponseMessage initArea() {
        logger.info("全国各个城市区域数据入库 start...");
        List<Area> areas = areaService.getAllArea();
        String result = "";
        if (areas == null || areas.isEmpty()) {
            result = areaService.initArea();
        } else {
            result = "数据已经初始化，无需再次初始化！";
        }
        return ResponseMessage.success(result);
    }

    @ApiOperation(value = "去重后的所有城市名")
    @GetMapping(value = "/eveCityNames")
    public ResponseMessage eveCityNames() {
        List<String> cityNames = areaService.eveCityNames();
        return ResponseMessage.success(cityNames);
    }

    @ApiOperation(value = "去重后的所有区县名")
    @GetMapping(value = "/eveAreaNames")
    public ResponseMessage eveAreaNames() {
        List<String> areaNames = areaService.eveAreaNames();
        return ResponseMessage.success(areaNames);
    }

    /**
     * 后期可以开放提供给外界 一：查询所有的省信息
     *
     * @return
     */
    @ApiOperation(value = "查询所有的省信息")
    @GetMapping(value = "/getProvinceDesc")
    public ResponseMessage getProvinceDesc() {
        List<Province> provinces = provinceService.getAllProvinceDesc();
        return ResponseMessage.success(provinces);
    }

    /**
     * 后期可以开放提供给外界 二：全国所有的省、市或辖区、区信息
     *
     * @return
     */
    @ApiOperation(value = "查询所有的省及其下属部门信息")
    @GetMapping(value = "/allProvince")
    public ResponseMessage getAllProvince() {
        List<Province> areaNames = provinceService.getAllProvince();
        return ResponseMessage.success(areaNames);
    }

    /**
     * 后期可以开放提供给外界 三：某一个省的市获或者辖区、区信息
     *
     * @param codeOrName 省编码或者名称
     * @return
     */
    @ApiOperation(value = "根据省编码获取名称查询 省下面的市或者辖区、以及辖区下面的地域")
    @GetMapping(value = "/getAreas")
    public ResponseMessage getAreaByProvince(@ApiParam(name = "codeOrName", value = "省编码或者名称", required = true) @RequestParam String codeOrName) {
        Province province = provinceService.getProvinceByCodeOrName(codeOrName);
        List<City> cityList = areaService.getAreaByProvince(province.getCode());
        return ResponseMessage.success(cityList);
    }
}
