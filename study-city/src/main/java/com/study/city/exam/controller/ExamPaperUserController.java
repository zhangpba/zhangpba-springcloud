package com.study.city.exam.controller;

import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.ExamPaperUser;
import com.study.city.exam.entity.request.ExamPaperUserListRequest;
import com.study.city.exam.service.ExamPaperUserService;
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

/**
 * (ExamPaperUser)表控制层
 *
 * @author zhangpba
 * @since 2023-01-14 19:52:19
 */
@RestController
@RequestMapping("examPaperUser")
@Api(value = "考生考卷", tags = "考生考卷")
public class ExamPaperUserController {
    /**
     * 服务对象
     */
    @Resource
    private ExamPaperUserService examPaperUserService;

    /**
     * 分页查询考生考卷
     *
     * @param examPaperUserListRequest 筛选条件
     * @return 查询结果
     */
    @PostMapping("/queryAll")
    @ApiOperation(value = "分页查询考生考卷", response = Dictionary.class)
    public ResponseMessage<PageInfo<ExamPaperUser>> queryAll(@RequestBody ExamPaperUserListRequest examPaperUserListRequest) {
        return ResponseMessage.success(this.examPaperUserService.queryByPage(examPaperUserListRequest));
    }

    /**
     * 通过主键查询单条考生考卷
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过主键查询单条考生考卷", response = Dictionary.class)
    public ResponseMessage<ExamPaperUser> queryById(@PathVariable("id") Integer id) {
        return ResponseMessage.success(this.examPaperUserService.queryById(id));
    }

    /**
     * 新增考生考卷
     *
     * @param examPaperUser 实体
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "新增考生考卷", response = Dictionary.class)
    public ResponseMessage<ExamPaperUser> add(@RequestBody ExamPaperUser examPaperUser) {
        return ResponseMessage.success(this.examPaperUserService.insert(examPaperUser));
    }

    /**
     * 编辑考生考卷
     *
     * @param examPaperUser 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation(value = "编辑考生考卷", response = Dictionary.class)
    public ResponseMessage<ExamPaperUser> edit(@RequestBody ExamPaperUser examPaperUser) {
        return ResponseMessage.success(this.examPaperUserService.update(examPaperUser));
    }

    /**
     * 删除考生考卷
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    @ApiOperation(value = "删除考生考卷", response = Dictionary.class)
    public ResponseMessage<Boolean> deleteById(Integer id) {
        return ResponseMessage.success(this.examPaperUserService.deleteById(id));
    }
}

