package com.data.chain.eventflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.chain.eventflow.dao.EventDao;
import com.data.chain.eventflow.dao.EventParamsDao;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.entity.Event;
import com.data.chain.eventflow.entity.EventParams;
import com.data.chain.eventflow.service.EventService;
import com.data.chain.eventflow.vo.FlowModelVO;
import com.data.chain.knowledgebase.entity.KnowledgeBase;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 预警事件表，接收到预警信息后创建一条预警事件，匹配相应的流程按节点处置。(Event)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 11:07:28
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventDao, Event> implements EventService {

    @Autowired
    private EventDao eventDao;
    @Autowired
    private EventParamsDao eventParamsDao ;

    @Override
    public Long saveEvent(EventDTO eventDTO) {
        // TODO 校验入参
        Event event = new Event();
        BeanUtils.copyProperties(eventDTO, event);
        event.setCreateTime(new Date());
        Long eventId = eventDao.saveEvent(event);
        if(CollectionUtils.isNotEmpty(eventDTO.getEventParamsList())){
            for (EventParams eventParams : eventDTO.getEventParamsList()){
                eventParams.setEventId(eventId);
                int result = eventParamsDao.insert(eventParams);
            }
        }
        return event.getId() ;
    }
}

