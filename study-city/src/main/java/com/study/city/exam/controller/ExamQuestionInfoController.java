package com.study.city.exam.controller;

import com.github.pagehelper.PageInfo;
import com.study.city.annotation.LoginToken;
import com.study.city.exam.entity.ExamQuestionInfo;
import com.study.city.exam.entity.request.ExamQuestionInfoRequest;
import com.study.city.exam.service.IExamQuestionInfoService;
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
 * (ExamQuestionInfo)表控制层
 *
 * @author zhangpba
 * @since 2023-01-13 16:57:29
 */
@RestController
@RequestMapping("questionInfo")
@Api(value = "考试题库-改良版", tags = "考试题库-改良版库")
public class ExamQuestionInfoController {

    private static final Logger logger = LoggerFactory.getLogger(ExamQuestionInfoController.class);

    /**
     * 服务对象
     */
    @Resource
    private IExamQuestionInfoService examQuestionInfoService;

    @GetMapping("/synQuestionInfo/{id}")
    @ApiOperation(value = "根据ID同步题目", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamQuestionInfo> synQuestionInfo(@PathVariable("id") Integer id) {
        logger.info("根据ID同步题目! id:{}",id);
        examQuestionInfoService.synQuestionInfo(id);
        return ResponseMessage.success("同步ID为" + id + "的题目成功！");
    }

    @GetMapping("/synQuestionInfo")
    @ApiOperation(value = "同步所有题目", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamQuestionInfo> synQuestionInfoAll() {
        logger.info("同步所有题目! ");
        examQuestionInfoService.synQuestionInfoAll();
        return ResponseMessage.success("所有题目同步成功！");
    }

    /**
     * 分页查询题目
     *
     * @param examQuestionInfoRequest 筛选条件
     * @return 查询结果
     */
    @PostMapping("/queryByPage")
    @ApiOperation(value = "分页查询题目", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<PageInfo<ExamQuestionInfo>> queryByPage(@RequestBody ExamQuestionInfoRequest examQuestionInfoRequest) {
        logger.info("分页查询题目! 参数:{}",examQuestionInfoRequest.toString());
        return ResponseMessage.success(this.examQuestionInfoService.queryByPage(examQuestionInfoRequest));
    }

    /**
     * 通过主键查询单条题目
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过主键查询单条题目", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamQuestionInfo> queryById(@PathVariable("id") Integer id) {
        logger.info("通过主键查询单条题目! 参数id:{}",id);
        return ResponseMessage.success(this.examQuestionInfoService.queryById(id));
    }

    /**
     * 新增题目
     *
     * @param examQuestionInfo 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增题目", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamQuestionInfo> add(@RequestBody ExamQuestionInfo examQuestionInfo) {
        logger.info("新增题目! 参数:{}",examQuestionInfo.toString());
        return ResponseMessage.success(this.examQuestionInfoService.insert(examQuestionInfo));
    }

    /**
     * 编辑题目
     *
     * @param examQuestionInfo 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    @ApiOperation(value = "编辑题目", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamQuestionInfo> edit(@RequestBody ExamQuestionInfo examQuestionInfo) {
        logger.info("编辑题目! 参数:{}",examQuestionInfo.toString());
        return ResponseMessage.success(this.examQuestionInfoService.update(examQuestionInfo));
    }

    /**
     * 删除题目
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除题目", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<Boolean> deleteById(@PathVariable("id")Integer id) {
        logger.info("删除题目! 参数id:{}",id);
        return ResponseMessage.success(this.examQuestionInfoService.deleteById(id));
    }

}

