package com.study.city.exam.controller;

import com.github.pagehelper.PageInfo;
import com.study.city.annotation.LoginToken;
import com.study.city.exam.entity.ExamPaperDetail;
import com.study.city.exam.entity.ExamPaperUser;
import com.study.city.exam.entity.request.ExamPaperDetailSaveRequest;
import com.study.city.exam.entity.request.ExamPaperUserListRequest;
import com.study.city.exam.entity.response.ExamPaperUserResponse;
import com.study.city.exam.entity.response.ExamPaperUserSubmitResponse;
import com.study.city.exam.service.IExamPaperDetailService;
import com.study.city.exam.service.IExamPaperUserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Dictionary;
import java.util.List;

/**
 * (ExamPaperUser)表控制层
 *
 * @author zhangpba
 * @since 2023-01-14 19:52:19
 */
@RestController
@RequestMapping("paperUser")
@Api(value = "考生考卷", tags = "考生考卷")
public class ExamPaperUserController {

    private static final Logger logger = LoggerFactory.getLogger(ExamPaperUserController.class);

    /**
     * 服务对象
     */
    @Resource
    private IExamPaperUserService examPaperUserService;

    @Resource
    private IExamPaperDetailService examPaperDetailService;

    /**
     * 分页查询考生考卷
     *
     * @param examPaperUserListRequest 筛选条件
     * @return 查询结果
     */
    @PostMapping("/queryAll")
    @ApiOperation(value = "分页查询考生考卷", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<PageInfo<ExamPaperUser>> queryAll(@RequestBody ExamPaperUserListRequest examPaperUserListRequest) {
        logger.info("分页查询考生考卷! id:{}", examPaperUserListRequest.toString());
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
    @LoginToken
    public ResponseMessage<ExamPaperUser> queryById(@PathVariable("id") Integer id) {
        logger.info("通过主键查询单条考生考卷! id:{}", id);
        return ResponseMessage.success(this.examPaperUserService.queryById(id));
    }

    /**
     * 新增考生考卷
     *
     * @param examPaperUser 实体
     * @return 新增结果
     */
    @PostMapping("/addExamPaperUser")
    @ApiOperation(value = "新增考生考卷", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamPaperUser> addExamPaperUser(@RequestBody ExamPaperUser examPaperUser) {
        logger.info("新增考生考卷! 参数:{}", examPaperUser.toString());
        return ResponseMessage.success(this.examPaperUserService.insert(examPaperUser));
    }

    /**
     * 编辑考生考卷
     *
     * @param examPaperUser 实体
     * @return 编辑结果
     */
    @PutMapping("/editExamPaperUser")
    @ApiOperation(value = "编辑考生考卷", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamPaperUser> editExamPaperUser(@RequestBody ExamPaperUser examPaperUser) {
        logger.info("编辑考生考卷! 参数:{}", examPaperUser.toString());
        return ResponseMessage.success(this.examPaperUserService.update(examPaperUser));
    }

    /**
     * 删除考生考卷
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除考生考卷", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<Boolean> deleteById(@PathVariable("id") Integer id) {
        logger.info("删除考生考卷! id:{}", id);
        return ResponseMessage.success(this.examPaperUserService.deleteById(id));
    }

    /**
     * 生成试卷
     *
     * @param userId      考生ID
     * @param examPaperId 考卷定义ID
     * @return 单条数据
     */
    @GetMapping("buildExamPaperUserDetail")
    @ApiOperation(value = "生成试卷", response = Dictionary.class)
    public ResponseMessage<List<ExamPaperDetail>> buildExamPaperUserDetail(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "examPaperId") Integer examPaperId) {
        logger.info("生成试卷! userId:{}，examPaperId：{}", userId, examPaperId);
        return ResponseMessage.success(this.examPaperDetailService.buildExamPaperUserDetail(userId, examPaperId));
    }


    /**
     * 考生考卷
     *
     * @param paperUserId 考卷主键
     * @return 删除是否成功
     */
    @GetMapping("/queryPaperUserDetail")
    @ApiOperation(value = "考生试卷信息", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamPaperUserResponse> queryPaperUserDetail(@RequestParam("paperUserId") Integer paperUserId) {
        logger.info("考生试卷信息! paperUserId:{}", paperUserId);
        return ResponseMessage.success(this.examPaperUserService.queryPaperUserDetail(paperUserId));
    }

    /**
     * 保存考试草稿
     *
     * @param examPaperDetailSaveRequest
     * @return
     */
    @PostMapping("saveExamPaperDetail")
    @ApiOperation(value = "保存考试草稿", response = Dictionary.class)
    @LoginToken
    public ResponseMessage saveExamPaperDetail(@Valid @RequestBody ExamPaperDetailSaveRequest examPaperDetailSaveRequest) {
        logger.info("保存考试草稿! 参数:{}", examPaperDetailSaveRequest.toString());
        ExamPaperDetail examPaperDetail = new ExamPaperDetail();
        examPaperDetail.setId(examPaperDetailSaveRequest.getId());
        examPaperDetail.setAnswer(examPaperDetailSaveRequest.getAnswer());
        return ResponseMessage.success(this.examPaperDetailService.update(examPaperDetail));
    }


    /**
     * 提交考试试卷
     *
     * @param paperUserId 考试试卷ID
     * @return
     */
    @GetMapping("submitExamPaper")
    @ApiOperation(value = "提交考试试卷", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<ExamPaperUserSubmitResponse> submitExamPaper(@RequestParam(value = "paperUserId") Integer paperUserId) {
        logger.info("提交考试试卷! paperUserId:{}", paperUserId);
        ExamPaperUserSubmitResponse examPaperUserSubmitResponse = examPaperUserService.submitExamPaper(paperUserId);
        return ResponseMessage.success(examPaperUserSubmitResponse);
    }
}

