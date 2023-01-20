package com.study.city.exam.controller;

import com.study.city.annotation.LoginToken;
import com.study.city.exam.entity.ExamPaperDetail;
import com.study.city.exam.service.IExamPaperDetailService;
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
import java.util.List;

/**
 * (ExamPaperDetail)考卷明细控制层
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:46
 */
@RestController
@RequestMapping("paperDetail")
@Api(value = "考卷明细", tags = "考卷明细")
public class ExamPaperDetailController {
    private static final Logger logger = LoggerFactory.getLogger(ExamPaperDetailController.class);
    /**
     * 服务对象
     */
    @Resource
    private IExamPaperDetailService examPaperDetailService;

    /**
     * 查询考卷明细列表
     *
     * @param examPaperDetail 筛选条件
     * @return 查询结果
     */
    @PostMapping("/queryByPage")
    @ApiOperation(value = "查询考卷明细列表", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<List<ExamPaperDetail>> queryByPage(@RequestBody ExamPaperDetail examPaperDetail) {
        logger.info("查询考卷明细列表! 参数:{}", examPaperDetail.toString());
        return ResponseMessage.success(this.examPaperDetailService.queryAll(examPaperDetail));
    }

    /**
     * 通过主键查询单条考卷明细
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过主键查询单条考卷明细", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamPaperDetail> queryById(@PathVariable("id") Integer id) {
        logger.info("通过主键查询单条考卷明细! id:{}", id);
        return ResponseMessage.success(this.examPaperDetailService.queryById(id));
    }

    /**
     * 新增考卷明细
     *
     * @param examPaperDetail 实体
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "新增考卷明细", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamPaperDetail> add(@RequestBody ExamPaperDetail examPaperDetail) {
        logger.info("新增考卷明细! id:{}", examPaperDetail.toString());
        return ResponseMessage.success(this.examPaperDetailService.insert(examPaperDetail));
    }

    /**
     * 编辑考卷明细
     *
     * @param examPaperDetail 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation(value = "编辑考卷明细", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamPaperDetail> edit(@RequestBody ExamPaperDetail examPaperDetail) {
        logger.info("编辑考卷明细! id:{}", examPaperDetail.toString());
        return ResponseMessage.success(this.examPaperDetailService.update(examPaperDetail));
    }

    /**
     * 删除考卷明细
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    @ApiOperation(value = "删除考卷明细", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<Boolean> deleteById(Integer id) {
        logger.info("删除考卷明细! id:{}", id);
        return ResponseMessage.success(this.examPaperDetailService.deleteById(id));
    }
}

