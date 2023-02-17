package com.data.chain.eventflow.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.chain.cardmanage.dao.FlowModelDao;
import com.data.chain.cardmanage.dao.FlowModelNodeDao;
import com.data.chain.enums.IsDeleteEnum;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.dto.FlowModeAddDto;
import com.data.chain.eventflow.dto.QueryFlowDto;
import com.data.chain.eventflow.entity.FlowModel;
import com.data.chain.eventflow.entity.FlowModelNode;
import com.data.chain.eventflow.enums.NodeTypeEnum;
import com.data.chain.eventflow.service.FlowModelNodeService;
import com.data.chain.eventflow.service.FlowModelService;
import com.data.chain.eventflow.vo.FlowModelNodeVO;
import com.data.chain.eventflow.vo.FlowModelVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程模板主表(FlowModel)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 11:09:42
 */
@Service
public class FlowModelServiceImpl extends ServiceImpl<FlowModelDao, FlowModel> implements FlowModelService {

    Logger logger = LoggerFactory.getLogger(FlowModelServiceImpl.class);

    @Autowired
    FlowModelDao flowModelDao ;
    @Autowired
    FlowModelNodeDao flowModelNodeDao ;
    @Autowired
    private FlowModelNodeService flowModelNodeService;
    @Override
    public FlowModelVO matchFlowModel(EventDTO eventDTO) {

        // 用事件信息匹配流程模版，关联流程节点
        List<FlowModel> flowModels = flowModelDao.selectFlowModelByEvent(eventDTO);
        if (CollectionUtils.isEmpty(flowModels)){
            logger.error("事件没有匹配到流程模版，入参：{}", JSON.toJSONString(eventDTO));
            return null;
        }
        FlowModelVO flowModelVO = new FlowModelVO() ;
        BeanUtils.copyProperties(flowModels.get(0),flowModelVO);
        List<FlowModelNode> flowModelNodes = flowModelNodeDao.selectFlowModelNode(flowModels.get(0).getId());
        if (CollectionUtils.isEmpty(flowModelNodes)){
            logger.error("流程模版配置有误，没有找到处置流程，入参：{}", JSON.toJSONString(eventDTO));
            return null;
        }
        List<FlowModelNodeVO> nodeList = new ArrayList<>();
        for (FlowModelNode node : flowModelNodes){
            FlowModelNodeVO vo = new FlowModelNodeVO();
            BeanUtils.copyProperties(node,vo);
            nodeList.add(vo);
        }
        flowModelVO.setFlowModelNodeVOList(nodeList);
        return flowModelVO;
    }

    @Override
    public IPage<FlowModel> queryList(QueryFlowDto dto) {
        LambdaQueryWrapper<FlowModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FlowModel::getIsDelete, IsDeleteEnum.NORMAL.getStatus());
        if (StringUtils.isNotBlank(dto.getModelName())) {
            wrapper.like(FlowModel::getModelName, dto.getModelName());
        }
        if (StringUtils.isNotBlank(dto.getBusinessType())) {
            wrapper.like(FlowModel::getBusinessType, dto.getBusinessType());
        }
        if (Objects.nonNull(dto.getIsValid())) {
            wrapper.eq(FlowModel::getIsValid, dto.getIsValid());
        }
        Page<FlowModel> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        return this.page(page, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        FlowModelNode flowModelNode = new FlowModelNode();
        flowModelNode.setIsDelete(IsDeleteEnum.DELETED.getStatus());
        flowModelNodeService.lambdaUpdate().eq(FlowModelNode::getModelId, id).update(flowModelNode);
        FlowModel flowModel = new FlowModel();
        flowModel.setIsDelete(IsDeleteEnum.DELETED.getStatus());
        flowModel.setId(id);
        this.updateById(flowModel);
    }

    @Override
    public FlowModelVO detail(Long id) {
        List<FlowModelNode> list = flowModelNodeService.lambdaQuery()
                .eq(FlowModelNode::getModelId, id)
                .eq(FlowModelNode::getIsDelete, IsDeleteEnum.NORMAL.getStatus())
                .list();
        Map<Long, FlowModelNodeVO> map = new HashMap<>();
        List<FlowModelNodeVO> headerNodes = new ArrayList<>();
        List<FlowModelNodeVO> supervisionNodes = new ArrayList<>();
        List<FlowModelNodeVO> nodeList = list.stream().map(item -> {
            FlowModelNodeVO vo = new FlowModelNodeVO();
            BeanUtils.copyProperties(item, vo);
            map.put(vo.getId(), vo);
            if (NodeTypeEnum.SUPERVISION_NODE.getType().equals(vo.getNodeType())) {
                supervisionNodes.add(vo);
                return vo;
            }
            if (Objects.isNull(item.getPreNodeId()) || 0 == item.getPreNodeId()) {
                headerNodes.add(vo);
            }
            return vo;
        }).collect(Collectors.toList());
        FlowModel flowModel = this.getById(id);
        FlowModelVO flowModelVO = new FlowModelVO();
        BeanUtils.copyProperties(flowModel, flowModelVO);
        flowModelVO.setFlowModelNodeVOList(nodeList);
        flowModelVO.setSupervisionNodes(supervisionNodes);
        flowModelVO.setDealNodes(getNodeList(map, headerNodes));
        return flowModelVO;
    }

    private List<List<FlowModelNodeVO>> getNodeList(Map<Long, FlowModelNodeVO> map, List<FlowModelNodeVO> headerNodes) {
        List<List<FlowModelNodeVO>> nodeVOList = new ArrayList<>();
        headerNodes.forEach(item -> {
            List<FlowModelNodeVO> list = new ArrayList<>();
            list.add(item);
            FlowModelNodeVO tempNode = item;
            while (Objects.nonNull(tempNode.getNextNodeId()) && 0 != tempNode.getNextNodeId()) {
                FlowModelNodeVO flowModelNodeVO = map.get(tempNode.getNextNodeId());
                list.add(flowModelNodeVO);
                tempNode = flowModelNodeVO;
            }
            nodeVOList.add(list);
        });
        return nodeVOList;
    }

    @Override
    public FlowModel saveFlowMode(FlowModeAddDto dto) {
        FlowModel flowModel = dto.convert();
        this.saveOrUpdate(flowModel);
        return flowModel;
    }
}

