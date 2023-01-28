package com.study.city.common.service.impl;

import com.study.city.common.entity.DictType;
import com.study.city.common.mapper.DictTypeMapper;
import com.study.city.common.service.IDictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典类型表(DictType)表服务实现类
 *
 * @author zhangpba
 * @since 2023-01-28 23:00:10
 */
@Service("dictTypeService")
public class DictTypeServiceImpl implements IDictTypeService {
    @Resource
    private DictTypeMapper dictTypeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    @Override
    public DictType queryById(Long dictId) {
        return this.dictTypeMapper.queryById(dictId);
    }

    /**
     * 分页查询
     *
     * @param dictType 筛选条件
     * @return 查询结果
     */
    @Override
    public List<DictType> queryAll(DictType dictType) {
        return this.dictTypeMapper.queryAll(dictType);
    }

    /**
     * 新增数据
     *
     * @param dictType 实例对象
     * @return 实例对象
     */
    @Override
    public DictType insert(DictType dictType) {
        this.dictTypeMapper.insert(dictType);
        return dictType;
    }

    /**
     * 修改数据
     *
     * @param dictType 实例对象
     * @return 实例对象
     */
    @Override
    public DictType update(DictType dictType) {
        this.dictTypeMapper.update(dictType);
        return this.queryById(dictType.getDictId());
    }

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long dictId) {
        return this.dictTypeMapper.deleteById(dictId) > 0;
    }
}
