package com.study.city.data.mapper;

import com.study.city.data.entity.Dict;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 字典表(Dict)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-12 20:23:25
 */
public interface DictMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dict queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param dict     查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Dict> queryAllByLimit(Dict dict, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param dict 查询条件
     * @return 总行数
     */
    long count(Dict dict);

    /**
     * 新增数据
     *
     * @param dict 实例对象
     * @return 影响行数
     */
    int insert(Dict dict);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dict> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Dict> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dict> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Dict> entities);

    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 影响行数
     */
    int update(Dict dict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据条件查询字典
     *
     * @param dict
     * @return
     */
    Dict query(Dict dict);
}

