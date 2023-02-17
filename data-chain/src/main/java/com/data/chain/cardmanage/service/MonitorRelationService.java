package com.data.chain.cardmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.chain.cardmanage.entity.MonitorRelation;

/**
 * 节点明细的督导关系表(MonitorRelation)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 11:11:09
 */
public interface MonitorRelationService extends IService<MonitorRelation> {

    int insert(MonitorRelation monitorRelation);

}

