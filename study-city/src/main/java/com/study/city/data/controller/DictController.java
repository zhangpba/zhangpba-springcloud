package com.study.city.data.controller;

import com.study.city.data.entity.Dict;
import com.study.city.data.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 字典表(Dict)表控制层
 *
 * @author zhangpba
 * @since 2022-12-12 20:23:21
 */
@Api(value = "字典表", tags = "字典表")
@RestController
@RequestMapping("dict")
public class DictController {
    /**
     * 服务对象
     */
    @Resource
    private IDictService dictService;

    /**
     * 分页查询
     *
     * @param dict 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询")
    @GetMapping
    public ResponseEntity<Page<Dict>> queryByPage(Dict dict, PageRequest pageRequest) {
        return ResponseEntity.ok(this.dictService.queryByPage(dict, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<Dict> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.dictService.queryById(id));
    }

    /**
     * 新增字典
     *
     * @param dict 实体
     * @return 新增结果
     */
    @ApiOperation(value = "新增字典")
    @PostMapping
    public ResponseEntity<Dict> add(Dict dict) {
        return ResponseEntity.ok(this.dictService.insert(dict));
    }

    /**
     * 编辑字典
     *
     * @param dict 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "编辑字典")
    @PutMapping
    public ResponseEntity<Dict> edit(Dict dict) {
        return ResponseEntity.ok(this.dictService.update(dict));
    }

    /**
     * 删除字典
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "删除字典")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.dictService.deleteById(id));
    }

}

