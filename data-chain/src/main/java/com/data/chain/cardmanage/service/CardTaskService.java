package com.data.chain.cardmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.chain.base.ResponseVO;
import com.data.chain.cardmanage.entity.CardTask;
import com.data.chain.cardmanage.vo.CardTaskVO;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.entity.EventNodeDetail;

import java.util.List;

/**
 * 卡片消息表，每一个卡片代表一个任务(CardTask)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 11:07:06
 */
public interface CardTaskService extends IService<CardTask> {

    Long saveCardTask(CardTask cardTask);

    ResponseVO<Boolean> newCardTaskByNodeDtl(EventNodeDetail eventNodeDetail);

    Boolean sendCardMsgToKafka(List<CardTaskVO> cardMsgList, EventDTO event);

    List<CardTask> selectNodeDtlsCards(Long id);
}

