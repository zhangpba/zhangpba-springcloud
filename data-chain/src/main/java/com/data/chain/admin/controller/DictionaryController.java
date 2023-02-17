package com.data.chain.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.data.chain.base.ResponseVO;
import com.data.chain.cardmanage.entity.Dictionary;
import com.data.chain.cardmanage.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2023/1/9
 */
@RestController
@Api(tags = "通用字典")
@RequestMapping("admin/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("common/query")
    @ApiOperation(value = "通用字典查询", response = Dictionary.class)
    public ResponseVO queryList(String type) {
        List<Dictionary> list;
        if (StringUtils.isBlank(type)) {
            list = dictionaryService.lambdaQuery().eq(Dictionary::getStatus, 0).orderByAsc(Dictionary::getSort).list();
        } else {
            list = dictionaryService.lambdaQuery().eq(Dictionary::getType, type).eq(Dictionary::getStatus, 0).orderByAsc(Dictionary::getSort).list();
        }
        return ResponseVO.success(list);
    }

    @GetMapping("queryType")
    @ApiOperation(value = "通用字典type查询", response = Dictionary.class)
    public ResponseVO queryType() {
        List<Dictionary> types = dictionaryService.list(new QueryWrapper<Dictionary>().select("DISTINCT type, type_name").lambda().eq(Dictionary::getStatus, 0));
        return ResponseVO.success(types);
    }

    @PostMapping("add")
    @ApiOperation(value = "通用字典添加", response = Dictionary.class)
    public ResponseVO add(@RequestBody Dictionary dictionary) {
        dictionaryService.save(dictionary);
        return ResponseVO.success();
    }

}
