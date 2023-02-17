package com.data.chain.cardmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.data.chain.base.ResponseVO;
import com.data.chain.cardmanage.dto.MonitorRecordDto;
import com.data.chain.cardmanage.entity.CardTask;
import com.data.chain.cardmanage.vo.MonitorBusinessVO;
import com.data.chain.cardmanage.vo.QueryCardTaskVO;
import com.data.chain.eventflow.entity.EventNodeDetail;
import com.data.chain.eventflow.vo.EventNodeDetailVO;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/14
 */
public interface CardManageService {
    /**
     * 我的卡片列表
     *
     * @param status 状态
     * @param userId 用户id
     * @param pageNum 分页页数
     * @param pageSize 分页条数
     * @return 结果
     */
    IPage<QueryCardTaskVO> myTask(Integer status, String userId, Integer pageNum, Integer pageSize);

    /**
     * 接受、处置
     *
     * @param cardTaskId  卡片id
     * @param userId 用户id
     */
    ResponseVO<Boolean> handle(Long cardTaskId, String userId);

    /**
     * 转交
     *
     * @param cardTaskId 卡片任务id
     * @param phone 手机号
     */
    ResponseVO<Boolean> reassign(Long cardTaskId, String phone);

    /**
     * 流程查询
     *
     * @param nodeDetailId 节点详情id
     * @return 排序后的节点列表
     */
    List<EventNodeDetail>  flowQuery(Long nodeDetailId);

    /**
     * 卡片详情
     *
     * @param cardTaskId 卡片id
     * @return 卡片详情
     */
    QueryCardTaskVO detail(Long cardTaskId);


    /**
     * 卡片详情
     *
     * @param nodeDetailId 节点详情id
     * @param userId 用户id
     * @return 卡片详情
     */
    QueryCardTaskVO detail(Long nodeDetailId, String userId);

    /**
     * 督导业务列表
     *
     * @param nodeId 督导节点id
     * @param cardTaskId 卡片id
     * @return 督导业务列表
     */
    List<MonitorBusinessVO> monitorBusinessList(Long nodeId, Long cardTaskId);

    /**
     * 提交督导意见
     *
     * @param dto 入参
     */
    void submitMonitorOpinions(MonitorRecordDto dto);


    /**
     * 督导业务详情
     *
     * @param nodeId 督导节点id
     * @param cardTaskId 卡片id
     * @return 督导业务详情
     */
    MonitorBusinessVO monitorBusinessDetail(Long nodeId, Long cardTaskId);

    ResponseVO<Boolean> accept(Long cardTaskId, String userId);
}
