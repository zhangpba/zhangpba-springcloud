package com.study.city.common.service.impl;

import com.study.city.common.entity.DictData;
import com.study.city.common.mapper.DictDataMapper;
import com.study.city.common.service.IDictDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典数据表(DictData)表服务实现类
 *
 * @author zhangpba
 * @since 2023-01-28 23:02:38
 */
@Service("dictDataService")
public class DictDataServiceImpl implements IDictDataService {
    @Resource
    private DictDataMapper dictDataMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param dictCode 主键
     * @return 实例对象
     */
    @Override
    public DictData queryById(Long dictCode) {
        return this.dictDataMapper.queryById(dictCode);
    }


    /**
     * 根据类型查询字典数据
     *
     * @param dictType
     * @return
     */
    @Override
    public List<DictData> getDataByDictType(String dictType) {
        DictData dictData = new DictData();
        dictData.setDictType(dictType);
        return dictDataMapper.queryAll(dictData);
    }

    /**
     * 新增数据
     *
     * @param dictData 实例对象
     * @return 实例对象
     */
    @Override
    public DictData insert(DictData dictData) {
        this.dictDataMapper.insert(dictData);
        return dictData;
    }

    /**
     * 修改数据
     *
     * @param dictData 实例对象
     * @return 实例对象
     */
    @Override
    public DictData update(DictData dictData) {
        this.dictDataMapper.update(dictData);
        return this.queryById(dictData.getDictCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param dictCode 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long dictCode) {
        return this.dictDataMapper.deleteById(dictCode) > 0;
    }
}
