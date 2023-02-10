package com.study.city.data.controller;

import com.study.city.data.entity.area.Area;
import com.study.city.data.entity.area.City;
import com.study.city.data.entity.area.Province;
import com.study.city.data.service.IAreaService;
import com.study.city.data.service.IProvinceService;
import com.study.common.exception.CustomException;
import com.study.common.utils.CollectionUtils;
import com.study.common.utils.ObjectUtils;
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
        if (CollectionUtils.isNotEmpty(areas)) {
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
     * 级连查询-查询所有的省信息
     *
     * @return
     */
    @ApiOperation(value = "级连查询-查询所有的省信息")
    @GetMapping(value = "/getProvinceDesc")
    public ResponseMessage<List<Province>> getProvinceDesc() {
        List<Province> provinces = provinceService.getAllProvinceDesc();
        return ResponseMessage.success(provinces);
    }

    /**
     * 级连查询-根据省编码查询市
     *
     * @return
     */
    @ApiOperation(value = "级连查询-根据省编码查询市")
    @GetMapping(value = "/getCityDesc")
    public ResponseMessage<List<City>> getCityDesc(@ApiParam(name = "provinceCode", value = "省编码", required = true) @RequestParam String provinceCode) {
        List<City> cities = provinceService.getCityDesc(provinceCode);
        return ResponseMessage.success(cities);
    }


    /**
     * 级连查询-根据省编码查询市
     *
     * @return
     */
    @ApiOperation(value = "级连查询-根据市编码查询区县")
    @GetMapping(value = "/getAreaDesc")
    public ResponseMessage<List<Area>> getAreaDesc(@ApiParam(name = "cityCode", value = "省编码", required = true) @RequestParam String cityCode) {
        List<Area> areas = areaService.getAreaListByCode(cityCode);
        return ResponseMessage.success(areas);
    }


    /**
     * 查询全国的省、市、区县信息
     *
     * @return
     */
    @ApiOperation(value = "查询全国的省、市、区县信息")
    @GetMapping(value = "/allProvince")
    public ResponseMessage<List<Province>> getAllProvince() {
        List<Province> areaNames = provinceService.getAllProvince();
        return ResponseMessage.success(areaNames);
    }

    /**
     * 根据省编码（或者名称）查询市、区县信息
     *
     * @param codeOrName 省编码或者名称
     * @return
     */
    @ApiOperation(value = "根据省编码（或者名称）查询市、区县信息")
    @GetMapping(value = "/getAreas")
    public ResponseMessage getAreaByProvince(@ApiParam(name = "codeOrName", value = "省编码或者名称", required = true) @RequestParam String codeOrName) {
        Province province = provinceService.getProvinceByCodeOrName(codeOrName);
        List<City> cityList = null;
        if (ObjectUtils.isNotNull(province)) {
            cityList = areaService.getAreaByProvince(province.getCode());
            if (CollectionUtils.isNotEmpty(cityList)) {
                return ResponseMessage.success(cityList);
            } else {
                return ResponseMessage.success(province);
            }
        } else {
            throw new CustomException(-1, "没有" + codeOrName + "的信息");
        }
    }
}
