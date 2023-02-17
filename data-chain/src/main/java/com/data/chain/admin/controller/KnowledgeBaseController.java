package com.data.chain.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.data.chain.base.ResponseVO;
import com.data.chain.knowledgebase.dto.KnowledgeBaseDto;
import com.data.chain.knowledgebase.entity.KnowledgeBase;
import com.data.chain.knowledgebase.service.KnowledgeBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/15
 */
@RestController
@Api(tags = "知识库")
@RequestMapping("admin/knowledge")
@CrossOrigin
public class KnowledgeBaseController {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @GetMapping("list")
    @ApiOperation(value = "知识库列表", response = KnowledgeBase.class)
    public ResponseVO list(@ApiParam("机构或姓名") @RequestParam  String name,
                           @ApiParam("接收人类型") @RequestParam  String operatorType,
                           @ApiParam("分页页数") @RequestParam  Integer pageNum,
                           @ApiParam("页容量") @RequestParam  Integer pageSize) {
        IPage<KnowledgeBase> result = knowledgeBaseService.queryList(name, operatorType, pageNum, pageSize);
        return ResponseVO.success(result);
    }

    @PostMapping("saveOrUpdate")
    @ApiOperation("新增/修改知识库")
    public ResponseVO saveOrUpdate(@RequestBody KnowledgeBaseDto dto) {
        knowledgeBaseService.saveOrUpdate(dto);
        return ResponseVO.success();
    }

    @PostMapping("delete/{id}")
    @ApiOperation("知识库删除")
    public ResponseVO delete(@PathVariable Long id) {
        knowledgeBaseService.delete(id);
        return ResponseVO.success();
    }

    @GetMapping("detail/{id}")
    @ApiOperation(value = "知识库详情", response = KnowledgeBase.class)
    public ResponseVO detail(@PathVariable Long id) {
        KnowledgeBase knowledgeBase = knowledgeBaseService.getById(id);
        return ResponseVO.success(knowledgeBase);
    }
}
