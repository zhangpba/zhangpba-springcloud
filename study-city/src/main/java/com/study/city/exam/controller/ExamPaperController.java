package com.study.city.exam.controller;

import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.ExamPaper;
import com.study.city.exam.entity.request.ExamPaperListRequest;
import com.study.city.exam.service.ExamPaperService;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Dictionary;

/**
 * (ExamPaper)考卷定义控制层
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:20
 */
@RestController
@RequestMapping("paper")
@Api(value = "考卷定义", tags = "考卷定义")
public class ExamPaperController {

    private static final Logger logger = LoggerFactory.getLogger(ExamPaperController.class);

    /**
     * 服务对象
     */
    @Resource
    private ExamPaperService examPaperService;

    /**
     * 分页查询考卷定义
     *
     * @param examPaperRequest 筛选条件
     * @return 查询结果
     */
    @PostMapping("/queryAll")
    @ApiOperation(value = "分页查询考卷定义", response = Dictionary.class)
    public ResponseMessage<PageInfo<ExamPaper>> queryAll(@RequestBody ExamPaperListRequest examPaperRequest) {
        logger.info("分页查询考卷定义!");
        return ResponseMessage.success(this.examPaperService.queryByPage(examPaperRequest));
    }

    /**
     * 通过主键查询单条考卷定义
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过主键查询单条考卷定义", response = Dictionary.class)
    public ResponseMessage<ExamPaper> queryById(@PathVariable("id") Integer id) {
        logger.info("通过主键查询单条考卷定义! id:{}", id);
        return ResponseMessage.success(this.examPaperService.queryById(id));
    }

    /**
     * 新增考卷定义
     *
     * @param examPaper 实体
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "新增考卷定义", response = Dictionary.class)
    public ResponseMessage<ExamPaper> add(@RequestBody ExamPaper examPaper) {
        logger.info("新增考卷定义! 参数：{}", examPaper);
        return ResponseMessage.success(this.examPaperService.insert(examPaper));
    }

    /**
     * 编辑考卷定义
     *
     * @param examPaper 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation(value = "编辑考卷定义", response = Dictionary.class)
    public ResponseMessage<ExamPaper> edit(@RequestBody ExamPaper examPaper) {
        logger.info("编辑考卷定义!");
        return ResponseMessage.success(this.examPaperService.update(examPaper));
    }

    /**
     * 删除考卷定义
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    @ApiOperation(value = "删除考卷定义", response = Dictionary.class)
    public ResponseMessage<Boolean> deleteById(Integer id) {
        logger.info("删除考卷定义! id:{}", id);
        return ResponseMessage.success(this.examPaperService.deleteById(id));
    }
}

