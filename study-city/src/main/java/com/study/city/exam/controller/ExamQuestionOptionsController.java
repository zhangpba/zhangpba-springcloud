package com.study.city.exam.controller;

import com.study.city.exam.entity.ExamQuestionOptions;
import com.study.city.exam.service.ExamQuestionOptionsService;
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
import java.util.Dictionary;
import java.util.List;

/**
 * (ExamQuestionOptions)表控制层
 *
 * @author zhangpba
 * @since 2023-01-13 17:02:56
 */
@RestController
@RequestMapping("examQuestionOptions")
@Api(value = "题库选项", tags = "题库选项")
public class ExamQuestionOptionsController {
    /**
     * 服务对象
     */
    @Resource
    private ExamQuestionOptionsService examQuestionOptionsService;

    /**
     * 分页查询选项
     *
     * @param examQuestionOptions 筛选条件
     * @return 查询结果
     */
    @GetMapping
    @ApiOperation(value = "查询选项", response = Dictionary.class)
    public ResponseMessage<List<ExamQuestionOptions>> queryExamQuestionOptions(@RequestBody ExamQuestionOptions examQuestionOptions) {
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
    public ResponseMessage<ExamQuestionOptions> queryById(@PathVariable("id") Integer id) {
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
    public ResponseMessage<ExamQuestionOptions> add(@RequestBody ExamQuestionOptions examQuestionOptions) {
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
    public ResponseMessage<ExamQuestionOptions> edit(@RequestBody ExamQuestionOptions examQuestionOptions) {
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
    public ResponseMessage<Boolean> deleteById(Integer id) {
        return ResponseMessage.success(this.examQuestionOptionsService.deleteById(id));
    }

}

