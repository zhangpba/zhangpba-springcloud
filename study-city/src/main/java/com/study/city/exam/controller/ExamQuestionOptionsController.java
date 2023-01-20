package com.study.city.exam.controller;

import com.study.city.annotation.LoginToken;
import com.study.city.exam.entity.ExamQuestionOptions;
import com.study.city.exam.service.IExamQuestionOptionsService;
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
 * (ExamQuestionOptions)表控制层
 *
 * @author zhangpba
 * @since 2023-01-13 17:02:56
 */
@RestController
@RequestMapping("questionOptions")
@Api(value = "题库选项", tags = "题库选项")
public class ExamQuestionOptionsController {

    private static final Logger logger = LoggerFactory.getLogger(ExamQuestionOptionsController.class);

    /**
     * 服务对象
     */
    @Resource
    private IExamQuestionOptionsService examQuestionOptionsService;

    /**
     * 分页查询选项
     *
     * @param examQuestionOptions 筛选条件
     * @return 查询结果
     */
    @GetMapping
    @ApiOperation(value = "查询选项", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<List<ExamQuestionOptions>> queryExamQuestionOptions(@RequestBody ExamQuestionOptions examQuestionOptions) {
        logger.info("查询选项! 参数:{}", examQuestionOptions);
        return ResponseMessage.success(this.examQuestionOptionsService.queryExamQuestionOptions(examQuestionOptions));
    }

    /**
     * 通过主键查询单条选项
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "通过主键查询单条选项", response = Dictionary.class)
    @GetMapping("{id}")
    @LoginToken
    public ResponseMessage<ExamQuestionOptions> queryById(@PathVariable("id") Integer id) {
        logger.info("通过主键查询单条选项! 参数:{}", id);
        return ResponseMessage.success(this.examQuestionOptionsService.queryById(id));
    }

    /**
     * 新增选项
     *
     * @param examQuestionOptions 实体
     * @return 新增结果
     */
    @ApiOperation(value = "新增选项", response = Dictionary.class)
    @PostMapping
    @LoginToken
    public ResponseMessage<ExamQuestionOptions> add(@RequestBody ExamQuestionOptions examQuestionOptions) {
        logger.info("新增选项! 参数:{}", examQuestionOptions.toString());
        return ResponseMessage.success(this.examQuestionOptionsService.insert(examQuestionOptions));
    }

    /**
     * 编辑选项
     *
     * @param examQuestionOptions 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "编辑选项", response = Dictionary.class)
    @PutMapping
    @LoginToken
    public ResponseMessage<ExamQuestionOptions> edit(@RequestBody ExamQuestionOptions examQuestionOptions) {
        logger.info("编辑选项! 参数:{}", examQuestionOptions.toString());
        return ResponseMessage.success(this.examQuestionOptionsService.update(examQuestionOptions));
    }

    /**
     * 删除选项
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "删除选项", response = Dictionary.class)
    @DeleteMapping
    @LoginToken
    public ResponseMessage<Boolean> deleteById(Integer id) {
        logger.info("删除选项! 参数id:{}", id);
        return ResponseMessage.success(this.examQuestionOptionsService.deleteById(id));
    }
}

