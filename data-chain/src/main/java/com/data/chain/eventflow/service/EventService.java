package com.data.chain.eventflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.entity.Event;

/**
 * 预警事件表，接收到预警信息后创建一条预警事件，匹配相应的流程按节点处置。(Event)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 11:07:28
 */
public interface EventService extends IService<Event> {

    /**
     * 保存事件实体到数据库
     */
    Long saveEvent(EventDTO eventDTO);
}

