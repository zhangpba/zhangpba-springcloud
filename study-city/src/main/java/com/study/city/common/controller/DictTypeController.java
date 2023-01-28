package com.study.city.common.controller;

import com.study.city.common.entity.DictType;
import com.study.city.common.service.IDictTypeService;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典类型表(DictType)表控制层
 *
 * @author zhangpba
 * @since 2023-01-28 23:00:01
 */
@RestController
@RequestMapping("dictType")
@Api(value = "字典类型", tags = "字典类型")
public class DictTypeController {
    /**
     * 服务对象
     */
    @Resource
    private IDictTypeService dictTypeService;

    /**
     * 查询所有字典类型
     *
     * @param dictType 筛选条件
     * @return 查询结果
     */
    @PostMapping("/queryAll")
    @ApiOperation(value = "所有字典类型")
    public ResponseMessage<List<DictType>> queryAll(@RequestBody DictType dictType) {
        return ResponseMessage.success(this.dictTypeService.queryAll(dictType));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过主键查询字典类型")
    public ResponseMessage<DictType> queryById(@PathVariable("id") Long id) {
        return ResponseMessage.success(this.dictTypeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dictType 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增字典类型")
    public ResponseMessage<DictType> add(@RequestBody DictType dictType) {
        return ResponseMessage.success(this.dictTypeService.insert(dictType));
    }

    /**
     * 编辑数据
     *
     * @param dictType 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    @ApiOperation(value = "编辑字典类型")
    public ResponseMessage<DictType> edit(@RequestBody DictType dictType) {
        return ResponseMessage.success(this.dictTypeService.update(dictType));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除字典类型")
    public ResponseMessage<Boolean> deleteById(Long id) {
        return ResponseMessage.success(this.dictTypeService.deleteById(id));
    }
}

