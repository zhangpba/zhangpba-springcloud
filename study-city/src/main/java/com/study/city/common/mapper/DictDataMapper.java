package com.study.city.common.mapper;

import com.study.city.common.entity.DictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典数据表(DictData)表数据库访问层
 *
 * @author zhangpba
 * @since 2023-01-28 23:02:38
 */
public interface DictDataMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param dictCode 主键
     * @return 实例对象
     */
    DictData queryById(Long dictCode);

    /**
     * 查询指定行数据
     *
     * @param dictData 查询条件
     * @return 对象列表
     */
    List<DictData> queryAll(DictData dictData);

    /**
     * 新增数据
     *
     * @param dictData 实例对象
     * @return 影响行数
     */
    int insert(DictData dictData);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DictData> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DictData> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DictData> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DictData> entities);

    /**
     * 修改数据
     *
     * @param dictData 实例对象
     * @return 影响行数
     */
    int update(DictData dictData);

    /**
     * 通过主键删除数据
     *
     * @param dictCode 主键
     * @return 影响行数
     */
    int deleteById(Long dictCode);

}
