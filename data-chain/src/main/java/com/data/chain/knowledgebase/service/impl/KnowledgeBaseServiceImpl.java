package com.data.chain.knowledgebase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.chain.base.ResponseEnum;
import com.data.chain.config.exception.CustomException;
import com.data.chain.knowledgebase.dao.KnowledgeBaseDao;
import com.data.chain.knowledgebase.dto.KnowledgeBaseDto;
import com.data.chain.knowledgebase.entity.KnowledgeBase;
import com.data.chain.knowledgebase.service.KnowledgeBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 知识库表(KnowledgeBase)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 11:10:20
 */
@Service
public class KnowledgeBaseServiceImpl extends ServiceImpl<KnowledgeBaseDao, KnowledgeBase> implements KnowledgeBaseService {

    @Autowired
    private KnowledgeBaseDao knowledgeBaseDao;

    @Override
    public IPage<KnowledgeBase> queryList(String name, String operatorType, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<KnowledgeBase> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(KnowledgeBase::getIsDelete, 0);
        if (StringUtils.isNotBlank(operatorType)) {
            wrapper1.eq(KnowledgeBase::getOperatorType, operatorType);
        }

        if (StringUtils.isNotBlank(name)) {
            wrapper1.and((wrapper) -> wrapper.like(KnowledgeBase::getName, name).or().like(KnowledgeBase::getOrgName, name));
        }
        Page<KnowledgeBase> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        return this.page(page, wrapper1);
    }

    @Override
    public void saveOrUpdate(KnowledgeBaseDto dto) {
        KnowledgeBase model = dto.convert();
        Optional<KnowledgeBase> knowledgeBaseOptional = this.lambdaQuery().eq(KnowledgeBase::getOperatorType, model.getOperatorType())
                .eq(KnowledgeBase::getName, model.getName())
                .eq(KnowledgeBase::getOrgName, model.getOrgName())
                .eq(KnowledgeBase::getOrgId, model.getOrgId())
                .eq(KnowledgeBase::getPhone, model.getPhone())
                .oneOpt();
        if (knowledgeBaseOptional.isPresent() && (Objects.isNull(dto.getId()) || 0 == dto.getId())) {
            throw new CustomException(ResponseEnum.ERROR_501.getCode(), "数据重复");
        }
        this.saveOrUpdate(model);
    }

    @Override
    public void delete(Long id) {
        KnowledgeBase knowledgeBase = this.getById(id);
        if (Objects.isNull(knowledgeBase) || 1 == knowledgeBase.getIsDelete()) {
            throw new CustomException(ResponseEnum.ERROR_501.getCode(), "数据不存在");
        }
        knowledgeBase.setIsDelete(1L);
        this.updateById(knowledgeBase);
    }

    @Override
    public String getOperatorNameByPhone(String phone) {
        String name = null;
        if (phone != null && !phone.isEmpty()) {
            List<KnowledgeBase> knowledgeBases = knowledgeBaseDao.getOperatorByPhone(phone);
            if (knowledgeBases != null && knowledgeBases.size() > 0) {
                name = knowledgeBases.get(0).getName();
            }
        }
        return name;
    }

}

