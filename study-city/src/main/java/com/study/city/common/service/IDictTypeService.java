package com.study.city.common.service;

import com.study.city.common.entity.DictType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 字典类型表(DictType)表服务接口
 *
 * @author zhangpba
 * @since 2023-01-28 23:00:07
 */
public interface IDictTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    DictType queryById(Long dictId);

    /**
     * 分页查询
     *
     * @param dictType 筛选条件
     * @return 查询结果
     */
    List<DictType> queryAll(DictType dictType);

    /**
     * 新增数据
     *
     * @param dictType 实例对象
     * @return 实例对象
     */
    DictType insert(DictType dictType);

    /**
     * 修改数据
     *
     * @param dictType 实例对象
     * @return 实例对象
     */
    DictType update(DictType dictType);

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 是否成功
     */
    boolean deleteById(Long dictId);

}
