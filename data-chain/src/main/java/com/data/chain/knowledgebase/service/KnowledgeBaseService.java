package com.data.chain.knowledgebase.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.data.chain.knowledgebase.dto.KnowledgeBaseDto;
import com.data.chain.knowledgebase.entity.KnowledgeBase;

/**
 * 知识库表(KnowledgeBase)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 11:10:20
 */
public interface KnowledgeBaseService extends IService<KnowledgeBase> {
    /**
     * 分页查询知识库列表
     *
     * @param name         机构或姓名
     * @param operatorType 接收人类型
     * @param pageNum      分页页数
     * @param pageSize     分页页容量
     * @return 分页数据
     */
    IPage<KnowledgeBase> queryList(String name, String operatorType, Integer pageNum, Integer pageSize);

    /**
     * 知识库新增/修改
     *
     * @param dto 入参
     */
    void saveOrUpdate(KnowledgeBaseDto dto);

    /**
     * 逻辑删除
     *
     * @param id id
     */
    void delete(Long id);

    /**
     * 根据电话号码查询处置人姓名
     *
     * @param phone 电话号码
     * @return 处置人姓名
     */
    String getOperatorNameByPhone(String phone);
}

