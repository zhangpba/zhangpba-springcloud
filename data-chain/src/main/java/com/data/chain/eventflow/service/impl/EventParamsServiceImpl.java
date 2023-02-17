package com.data.chain.eventflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.chain.eventflow.dao.EventParamsDao;
import com.data.chain.eventflow.entity.EventParams;
import com.data.chain.eventflow.service.EventParamsService;
import org.springframework.stereotype.Service;

/**
 * 事件参数表(EventParams)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 11:08:40
 */
@Service
public class EventParamsServiceImpl extends ServiceImpl<EventParamsDao, EventParams> implements EventParamsService {

}

