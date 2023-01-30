package com.study.city.common.controller;

import com.study.city.common.entity.DictData;
import com.study.city.common.service.IDictDataService;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典数据表(DictData)表控制层
 *
 * @author zhangpba
 * @since 2023-01-28 23:02:38
 */
@RestController
@RequestMapping("dictData")
@Api(value = "字典数据", tags = "字典数据")
public class DictDataController {
    /**
     * 服务对象
     */
    @Resource
    private IDictDataService dictDataService;

    /**
     * 根据类型查询字典数据
     *
     * @param dictType 筛选条件
     * @return 查询结果
     */
    @ApiOperation(value = "根据类型查询字典数据")
    @GetMapping("/getDataByDictType")
    public ResponseMessage<List<DictData>> getDataByDictType(@RequestParam(value = "dictType") String dictType) {
        return ResponseMessage.success(this.dictDataService.getDataByDictType(dictType));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过主键查询单条数据")
    public ResponseMessage<DictData> queryById(@PathVariable("id") Long id) {
        return ResponseMessage.success(this.dictDataService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dictData 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增字典数据")
    public ResponseMessage<DictData> add(DictData dictData) {
        return ResponseMessage.success(this.dictDataService.insert(dictData));
    }

    /**
     * 编辑数据
     *
     * @param dictData 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    @ApiOperation(value = "编辑字典数据")
    public ResponseMessage<DictData> edit(DictData dictData) {
        return ResponseMessage.success(this.dictDataService.update(dictData));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "编辑字典数据")
    public ResponseMessage<Boolean> deleteById(@PathVariable("id")Long id) {
        return ResponseMessage.success(this.dictDataService.deleteById(id));
    }

}

