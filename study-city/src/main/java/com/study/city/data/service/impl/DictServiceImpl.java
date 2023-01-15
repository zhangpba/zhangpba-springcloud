package com.study.city.data.service.impl;

import com.study.city.data.entity.Dict;
import com.study.city.data.mapper.DictMapper;
import com.study.city.data.service.IDictService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 字典表(Dict)表服务实现类
 *
 * @author zhangpba
 * @since 2022-12-12 20:23:36
 */
@Service("dictService")
public class DictServiceImpl implements IDictService {
    @Resource
    private DictMapper dictMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dict queryById(Integer id) {
        return this.dictMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param dict        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Dict> queryByPage(Dict dict, PageRequest pageRequest) {
        long total = this.dictMapper.count(dict);
        return new PageImpl<>(this.dictMapper.queryAllByLimit(dict, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    @Override
    public Dict insert(Dict dict) {
        this.dictMapper.insert(dict);
        return dict;
    }

    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    @Override
    public Dict update(Dict dict) {
        this.dictMapper.update(dict);
        return this.queryById(dict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.dictMapper.deleteById(id) > 0;
    }

    /**
     * 根据条件查询字典
     *
     * @param dict
     * @return
     */
    @Override
    public Dict query(Dict dict) {
        return this.dictMapper.query(dict);
    }
}
