package com.data.chain.cardmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.chain.cardmanage.dao.MonitorRelationDao;
import com.data.chain.cardmanage.entity.MonitorRelation;
import com.data.chain.cardmanage.service.MonitorRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 节点明细的督导关系表(MonitorRelation)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 11:11:09
 */
@Service
public class MonitorRelationServiceImpl extends ServiceImpl<MonitorRelationDao, MonitorRelation> implements MonitorRelationService {

    @Autowired
    MonitorRelationDao monitorRelationDao ;

    @Override
    public int insert(MonitorRelation monitorRelation) {
        return monitorRelationDao.insert(monitorRelation);
    }
}

