package com.study.city.data.service;

import com.study.city.data.entity.Dict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 字典表(Dict)表服务接口
 *
 * @author makejava
 * @since 2022-12-12 20:23:32
 */
public interface IDictService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dict queryById(Integer id);

    /**
     * 分页查询
     *
     * @param dict        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Dict> queryByPage(Dict dict, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    Dict insert(Dict dict);

    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    Dict update(Dict dict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据条件查询字典
     *
     * @param dict
     * @return
     */
    Dict query(Dict dict);
}
