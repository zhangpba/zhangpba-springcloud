package com.data.chain.eventflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.chain.cardmanage.vo.CardTaskVO;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.entity.EventNodeDetail;
import com.data.chain.eventflow.vo.EventNodeDetailVO;
import com.data.chain.eventflow.vo.FlowModelVO;

import java.util.List;

/**
 * 事件处置流程节点明细表(EventNodeDetail)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 11:09:11
 */
public interface EventNodeDetailService extends IService<EventNodeDetail> {

    List<EventNodeDetail> makeEventNodeDetails(EventDTO eventDTO, FlowModelVO flowModelVO);

    List<CardTaskVO> makeCardMsgs(EventDTO eventDTO, List<EventNodeDetail> eventNodeDetails)  throws Exception ;
}

