package com.study.city.common.service;

import com.study.city.common.entity.DictData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 字典数据表(DictData)表服务接口
 *
 * @author zhangpba
 * @since 2023-01-28 23:02:38
 */
public interface IDictDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param dictCode 主键
     * @return 实例对象
     */
    DictData queryById(Long dictCode);

    /**
     * 根据类型查询字典数据
     *
     * @param dictType
     * @return
     */
    List<DictData> getDataByDictType(String dictType);

    /**
     * 新增数据
     *
     * @param dictData 实例对象
     * @return 实例对象
     */
    DictData insert(DictData dictData);

    /**
     * 修改数据
     *
     * @param dictData 实例对象
     * @return 实例对象
     */
    DictData update(DictData dictData);

    /**
     * 通过主键删除数据
     *
     * @param dictCode 主键
     * @return 是否成功
     */
    boolean deleteById(Long dictCode);

}
