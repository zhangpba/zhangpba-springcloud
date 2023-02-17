package com.data.chain.cardmanage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.data.chain.base.ResponseEnum;
import com.data.chain.base.ResponseVO;
import com.data.chain.cardmanage.dto.MonitorRecordDto;
import com.data.chain.cardmanage.entity.CardTask;
import com.data.chain.cardmanage.entity.MonitorRecord;
import com.data.chain.cardmanage.entity.MonitorRelation;
import com.data.chain.cardmanage.enums.HandleStatusEnum;
import com.data.chain.cardmanage.service.CardManageService;
import com.data.chain.cardmanage.service.CardTaskService;
import com.data.chain.cardmanage.service.MonitorRecordService;
import com.data.chain.cardmanage.service.MonitorRelationService;
import com.data.chain.cardmanage.vo.MonitorBusinessVO;
import com.data.chain.cardmanage.vo.QueryCardTaskVO;
import com.data.chain.config.exception.CustomException;
import com.data.chain.enums.IsDeleteEnum;
import com.data.chain.eventflow.dao.EventNodeDetailDao;
import com.data.chain.eventflow.entity.Event;
import com.data.chain.eventflow.entity.EventNodeDetail;
import com.data.chain.eventflow.enums.BusinessTypeEnum;
import com.data.chain.eventflow.enums.EventNodeDtlStatusEnum;
import com.data.chain.eventflow.enums.EventStatusEnum;
import com.data.chain.eventflow.enums.NodeTypeEnum;
import com.data.chain.eventflow.service.EventNodeDetailService;
import com.data.chain.eventflow.service.EventService;
import com.data.chain.knowledgebase.dao.KnowledgeBaseDao;
import com.data.chain.knowledgebase.entity.KnowledgeBase;
import com.data.chain.knowledgebase.service.KnowledgeBaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/14
 */
@Service
public class CardManageServiceImpl implements CardManageService {
    @Autowired
    private CardTaskService cardTaskService;
    @Autowired
    private EventNodeDetailService eventNodeDetailService;
    @Autowired
    private EventService eventService;
    @Autowired
    private MonitorRelationService monitorRelationService;
    @Autowired
    private MonitorRecordService monitorRecordService;
    @Autowired
    private EventNodeDetailDao eventNodeDetailDao;
    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @Override
    public IPage<QueryCardTaskVO> myTask(Integer status, String userId, Integer pageNum, Integer pageSize) {
        Page<CardTask> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        IPage<CardTask> cardTaskPage = cardTaskService.lambdaQuery()
                .eq(CardTask::getUserId, userId)
                .eq(CardTask::getOperateStatus, status)
                .page(page);
        if (CollectionUtils.isEmpty(cardTaskPage.getRecords())) {
            return new Page<>();
        }
        // 相关数据查询
        Set<Long> nodeDetailIds = cardTaskPage.getRecords().stream().map(CardTask::getNodeDetailId).collect(Collectors.toSet());
        List<EventNodeDetail> eventNodeDetails = eventNodeDetailService.lambdaQuery().in(EventNodeDetail::getId, nodeDetailIds).list();
        Map<Long, EventNodeDetail> eventNodeDetailMap = eventNodeDetails.stream().collect(Collectors.toMap(EventNodeDetail::getId, Function.identity()));
        Set<Long> eventIds = eventNodeDetails.stream().map(EventNodeDetail::getEventId).collect(Collectors.toSet());
        List<Event> events = eventService.lambdaQuery().in(Event::getId, eventIds).list();
        Map<Long, Event> eventMap = events.stream().collect(Collectors.toMap(Event::getId, Function.identity()));

        List<QueryCardTaskVO> queryCardTasks = new ArrayList<>();
        cardTaskPage.getRecords().forEach(item -> {
            EventNodeDetail eventNodeDetail = eventNodeDetailMap.get(item.getNodeDetailId());
            Event event = eventMap.get(eventNodeDetail.getEventId());
            QueryCardTaskVO vo = handleQueryCardTaskData(item, event, eventNodeDetail);
            queryCardTasks.add(vo);
        });
        IPage<QueryCardTaskVO> result = new Page<>();
        BeanUtils.copyProperties(cardTaskPage, result);
        result.setRecords(queryCardTasks);
        return result;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVO<Boolean> accept(Long cardTaskId, String userId) {
        CardTask cardTask = cardTaskService.getById(cardTaskId);
        if (HandleStatusEnum.FINISH.getStatus() == cardTask.getOperateStatus()) {
            return ResponseVO.fail(ResponseEnum.FAIL.getCode(), "该卡片已处置完成");
        }
        EventNodeDetail eventNodeDetail = eventNodeDetailService.getById(cardTask.getNodeDetailId());
        if (EventNodeDtlStatusEnum.FINISH.getStatus() == eventNodeDetail.getStatus()) {
            return ResponseVO.fail(ResponseEnum.FAIL.getCode(), "该任务已处置完成");
        }
        cardTask.setId(cardTaskId);
        cardTask.setOperateStatus(HandleStatusEnum.ACCEPT.getStatus());
        cardTask.setUpdateUser(userId);
        cardTask.setUpdateTime(new Date());
        cardTask.setAcceptTime(new Date());
        boolean cardResult = cardTaskService.updateById(cardTask);

//        eventNodeDetail.setStatus(EventNodeDtlStatusEnum.ACCEPT.getStatus());
//        boolean nodeResult = eventNodeDetailService.updateById(eventNodeDetail);

        return ResponseVO.success(ResponseEnum.SUCCESS.getCode(), "操作成功！", cardResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVO<Boolean> handle(Long cardTaskId, String userId) {
        ResponseVO<Boolean> responseVO = new ResponseVO<>();
        // 判断这个卡片对应的节点明细是否已完成，完成则不能再次操作
        CardTask cardTask = cardTaskService.getById(cardTaskId);
        if (HandleStatusEnum.FINISH.getStatus() == cardTask.getOperateStatus()) {
            return ResponseVO.fail(ResponseEnum.FAIL.getCode(), "该卡片已处置完成");
        }
        EventNodeDetail eventNodeDetail = eventNodeDetailService.getById(cardTask.getNodeDetailId());
        if (EventNodeDtlStatusEnum.FINISH.getStatus() == eventNodeDetail.getStatus()) {
            return ResponseVO.fail(ResponseEnum.FAIL.getCode(), "该任务已处置完成");
        }
        // 没有完成则执行下面步骤：
        // 1、修改卡片状态
        cardTask.setId(cardTaskId);
        cardTask.setOperateStatus(HandleStatusEnum.FINISH.getStatus());
        cardTask.setUpdateUser(userId);
        cardTask.setUpdateTime(new Date());
        cardTask.setHandleTime(new Date());
        boolean cardResult = cardTaskService.updateById(cardTask);
        if (!cardResult) {
            return ResponseVO.fail(ResponseEnum.FAIL.getCode(), "修改卡片状态失败");
        }
        // 2、修改节点明细状态
        if (eventNodeDetail.getPassWay() == 1) { // 任一完成通过的节点，一个人完成则节点处置完成
            eventNodeDetail.setHandleTime(new Date());
            eventNodeDetail.setStatus(EventNodeDtlStatusEnum.FINISH.getStatus());
            boolean nodeResult = eventNodeDetailService.updateById(eventNodeDetail);
            if (!nodeResult) {
                return ResponseVO.fail(ResponseEnum.FAIL.getCode(), "修改节点明细状态失败");
            }
        } else {  // 全部完成通过的节点，所有人都完成才算节点处置完成
            Boolean ifDone = true;
            List<CardTask> cardTasks = cardTaskService.selectNodeDtlsCards(eventNodeDetail.getId());
            for (CardTask c : cardTasks) {
                if (c.getOperateStatus() != 2) {
                    ifDone = false;
                    break;
                }
            }
            if (ifDone) {
                eventNodeDetail.setStatus(EventNodeDtlStatusEnum.FINISH.getStatus());
                eventNodeDetail.setHandleTime(new Date());
                boolean nodeResult = eventNodeDetailService.updateById(eventNodeDetail);
                if (!nodeResult) {
                    return ResponseVO.fail(ResponseEnum.FAIL.getCode(), "修改事件状态失败");
                }
            }
        }
        // 3、TODO 修改节点明细下的其他卡片消息的状态

        // 4、TODO 给相关的人和群发送状态变更提醒信息

        // 5、判断是否最后一个节点，如果是，修改事件状态
        if (eventNodeDetail.getNextNodeDtlId() == null && eventNodeDetail.getNodeType() == NodeTypeEnum.DEAL_NODE.getType()) { // 如果是最后一个处置节点，完成后事件也完成
            Event event = new Event();
            event.setId(eventNodeDetail.getEventId());
            event.setStatus(EventStatusEnum.FINISH.getStatus());
            eventService.updateById(event);
        } else if (eventNodeDetail.getNextNodeDtlId() != null) {
            // 如果下个节点不为空，生成下个节点的卡片消息，发送给企微用户
            responseVO = cardTaskService.newCardTaskByNodeDtl(eventNodeDetail);
        }
        return responseVO;
    }


    @Override
    @Transactional
    public ResponseVO<Boolean> reassign(Long cardTaskId, String phone) {
        // 1、修改状态
        CardTask cardTask = cardTaskService.getById(cardTaskId);
        if (cardTask.getOperateStatus() == 2) {
            throw new CustomException(ResponseEnum.ERROR_501.getCode(), "流程已结束！");
        }
        cardTask.setOperateStatus(2);
        cardTask.setUpdateTime(new Date());
        cardTask.setHandleTime(new Date());
        cardTaskService.updateById(cardTask);

        // 2、修改下标
        // 根据卡片Id查询事件明细节点
        Long nodeDetailId = cardTask.getNodeDetailId();
        // 当前节点为上一节点
        EventNodeDetail preNodeDetail = eventNodeDetailService.getById(nodeDetailId);

        // 新节点
        EventNodeDetail eventNodeDetailTarget = copyEventNodeDetail(preNodeDetail, phone);
        eventNodeDetailDao.insertEventNodeDetail(eventNodeDetailTarget);
        Long currentId = eventNodeDetailTarget.getId();

        // 上一节点，next改为新id
        preNodeDetail.setNextNodeDtlId(currentId);

        // 新节点，pre改为上一节点的id
        eventNodeDetailTarget.setPreNodeDtlId(preNodeDetail.getId());

        // 下一节点，pre改为新id
        EventNodeDetail nextNodeDetail = eventNodeDetailService.getById(preNodeDetail.getNextNodeDtlId());
        nextNodeDetail.setPreNodeDtlId(currentId);

        List<EventNodeDetail> eventNodeDetails = new ArrayList<>();
        eventNodeDetails.add(preNodeDetail);
        eventNodeDetails.add(eventNodeDetailTarget);
        eventNodeDetails.add(nextNodeDetail);

        eventNodeDetailDao.updatePreNext(eventNodeDetails);

        // 3、发送卡片
        return cardTaskService.newCardTaskByNodeDtl(eventNodeDetailTarget);
    }

    // 复制事件详情
    private EventNodeDetail copyEventNodeDetail(EventNodeDetail eventNodeDetail, String phone) {
        EventNodeDetail eventNodeDetailTarget = new EventNodeDetail();
        eventNodeDetailTarget.setEventId(eventNodeDetail.getEventId());
        eventNodeDetailTarget.setFlowModelId(eventNodeDetail.getFlowModelId());
        eventNodeDetailTarget.setOperators(phone);
        eventNodeDetailTarget.setChatId(eventNodeDetail.getChatId());
        eventNodeDetailTarget.setPassWay(eventNodeDetail.getPassWay());
        eventNodeDetailTarget.setNodeType(eventNodeDetail.getNodeType());
        eventNodeDetailTarget.setHandleSuggest(eventNodeDetail.getHandleSuggest());
        eventNodeDetailTarget.setPreNodeDtlId(eventNodeDetail.getPreNodeDtlId());
        eventNodeDetailTarget.setNextNodeDtlId(eventNodeDetail.getNextNodeDtlId());
        eventNodeDetailTarget.setOperationList(eventNodeDetail.getOperationList());
        eventNodeDetailTarget.setStatus(eventNodeDetail.getStatus());
        eventNodeDetailTarget.setIsValid(eventNodeDetail.getIsValid());
        eventNodeDetailTarget.setIsDelete(eventNodeDetail.getIsDelete());
        eventNodeDetailTarget.setCreateUser(eventNodeDetail.getCreateUser());
        eventNodeDetailTarget.setCreateTime(new Date());
        eventNodeDetailTarget.setUpdateUser(eventNodeDetail.getUpdateUser());
        eventNodeDetailTarget.setUpdateTime(new Date());
        return eventNodeDetailTarget;
    }

    // 复制卡片
    private CardTask copy(CardTask cardTask, String userId) {
        CardTask cardTaskTarget = new CardTask();
        cardTaskTarget.setNodeDetailId(cardTask.getNodeDetailId());
        cardTaskTarget.setOperateStatus(cardTask.getOperateStatus());
        cardTaskTarget.setMyOption(cardTask.getMyOption());
        cardTaskTarget.setAcceptTime(cardTask.getAcceptTime());
        cardTaskTarget.setUserId(userId);
        cardTaskTarget.setUserName(cardTask.getUserName());
        cardTaskTarget.setChatId(cardTask.getChatId());
        cardTaskTarget.setOpinions(cardTask.getOpinions());
        cardTaskTarget.setHandleTime(cardTask.getHandleTime());
        cardTaskTarget.setIsValid(cardTask.getIsValid());
        cardTaskTarget.setIsDelete(cardTask.getIsDelete());
        cardTaskTarget.setCreateUser(cardTask.getCreateUser());
        cardTaskTarget.setCreateTime(new Date());
        cardTaskTarget.setUpdateUser(cardTask.getUpdateUser());
        cardTaskTarget.setUpdateTime(new Date());

        return cardTaskTarget;
    }


    @Override
    public List<EventNodeDetail> flowQuery(Long nodeDetailId) {
        EventNodeDetail eventNodeDetail = eventNodeDetailService.getById(nodeDetailId);
        List<EventNodeDetail> list = eventNodeDetailService.lambdaQuery()
                .eq(EventNodeDetail::getEventId, eventNodeDetail.getEventId())
                .eq(EventNodeDetail::getFlowModelId, eventNodeDetail.getFlowModelId()).list();
        Optional<EventNodeDetail> first = list.stream().filter(item -> Objects.isNull(item.getPreNodeDtlId()) || 0 == item.getPreNodeDtlId()).findFirst();
        if (!first.isPresent()) {
            throw new CustomException(ResponseEnum.FAIL.getCode(), "未查询到系统流程");
        }
        //首节点id
        EventNodeDetail firstEventNodeDetail = first.get();
        Map<Long, EventNodeDetail> eventNodeDetailMap = list.stream().collect(Collectors.toMap(EventNodeDetail::getId, Function.identity()));
        List<EventNodeDetail> result = new ArrayList<>();
        result.add(firstEventNodeDetail);
        EventNodeDetail tempNode = firstEventNodeDetail;
        while (Objects.nonNull(tempNode.getNextNodeDtlId()) && 0 != tempNode.getNextNodeDtlId()) {
            EventNodeDetail eventNodeDetail1 = eventNodeDetailMap.get(firstEventNodeDetail.getNextNodeDtlId());
            result.add(eventNodeDetail1);
            tempNode = eventNodeDetail;
        }
        return result;
    }

    @Override
    public QueryCardTaskVO detail(Long cardTaskId) {
        CardTask cardTask = cardTaskService.getById(cardTaskId);
        EventNodeDetail eventNodeDetail = eventNodeDetailService.getById(cardTask.getNodeDetailId());
        Event event = eventService.getById(eventNodeDetail.getEventId());
        QueryCardTaskVO vo = handleQueryCardTaskData(cardTask, event, eventNodeDetail);
        List<MonitorRecord> list = monitorRecordService.lambdaQuery()
                .eq(MonitorRecord::getMonitoredNodeDtlId, eventNodeDetail.getId())
                .eq(MonitorRecord::getIsDelete, IsDeleteEnum.NORMAL.getStatus())
                .list();
        vo.setMonitorRecords(list);
        return vo;
    }

    @Override
    public QueryCardTaskVO detail(Long nodeDetailId, String userId) {
        Optional<CardTask> cardTaskOptional = cardTaskService.lambdaQuery()
                .eq(CardTask::getUserId, userId)
                .eq(CardTask::getNodeDetailId, nodeDetailId)
                .oneOpt();
        if (!cardTaskOptional.isPresent()) {
            throw new CustomException(ResponseEnum.ERROR_501.getCode(), "数据不存在！");
        }
        CardTask cardTask = cardTaskOptional.get();
        EventNodeDetail eventNodeDetail = eventNodeDetailService.getById(cardTask.getNodeDetailId());
        Event event = eventService.getById(eventNodeDetail.getEventId());
        QueryCardTaskVO vo = handleQueryCardTaskData(cardTask, event, eventNodeDetail);
        List<MonitorRecord> list = monitorRecordService.lambdaQuery()
                .eq(MonitorRecord::getMonitoredNodeDtlId, eventNodeDetail.getId())
                .eq(MonitorRecord::getIsDelete, IsDeleteEnum.NORMAL.getStatus())
                .list();
        vo.setMonitorRecords(list);
        // 如果查看时间为空，更新查看时间
        if (vo.getViewTime() == null){
            CardTask cardTask1 = new CardTask();
            cardTask1.setId(vo.getId());
            cardTask1.setViewTime(new Date());
            cardTaskService.updateById(cardTask);
        }
        return vo;
    }

    @Override
    public List<MonitorBusinessVO> monitorBusinessList(Long nodeId, Long cardTaskId) {
        List<MonitorRelation> list = monitorRelationService.lambdaQuery().eq(MonitorRelation::getMonitorNodeId, nodeId).list();
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        Set<Long> nodeDetailIds = list.stream().map(MonitorRelation::getMonitoredNodeId).collect(Collectors.toSet());

        List<EventNodeDetail> eventNodeDetails = eventNodeDetailService.lambdaQuery().in(EventNodeDetail::getId, nodeDetailIds).list();
        Set<Long> eventIds = eventNodeDetails.stream().map(EventNodeDetail::getEventId).collect(Collectors.toSet());
        List<Event> events = eventService.lambdaQuery().in(Event::getId, eventIds).list();
        Map<Long, Event> eventMap = events.stream().collect(Collectors.toMap(Event::getId, Function.identity()));

        List<MonitorBusinessVO> result = new ArrayList<>();
        eventNodeDetails.forEach(item -> {
            Event event = eventMap.get(item.getEventId());
            String receiverName = StringUtils.isEmpty(event.getReceiverName()) ? " " : event.getReceiverName();
            String typeName = StringUtils.isEmpty(BusinessTypeEnum.descOfBusinessType(event.getBusinessType())) ? " " : BusinessTypeEnum.descOfBusinessType(event.getBusinessType());
            String title = receiverName + "发出了" + typeName + "预警";
            MonitorBusinessVO vo = new MonitorBusinessVO();
            vo.setNodeId(item.getId());
            vo.setEventId(event.getId());
            vo.setTitle(title);
            vo.setCardTaskId(cardTaskId);
            result.add(vo);
        });
        return result;
    }

    @Override
    public void submitMonitorOpinions(MonitorRecordDto dto) {
        MonitorRecord monitorRecord = dto.convert();
        monitorRecord.setMonitorTime(new Date());
        monitorRecordService.save(monitorRecord);
    }

    @Override
    public MonitorBusinessVO monitorBusinessDetail(Long nodeId, Long cardTaskId) {
        EventNodeDetail eventNodeDetail = eventNodeDetailService.getById(nodeId);
//        if (NodeTypeEnum.SUPERVISION_NODE.getType().equals(eventNodeDetail.getNodeType())) {
//            throw new CustomException(ResponseEnum.ERROR_501);
//        }
        MonitorBusinessVO monitorBusinessVO = new MonitorBusinessVO();
        BeanUtils.copyProperties(eventNodeDetail, monitorBusinessVO);
        monitorBusinessVO.setCardTaskId(cardTaskId);
        monitorBusinessVO.setNodeId(eventNodeDetail.getId());
        monitorBusinessVO.setMonitors("");
        monitorBusinessVO.setHandlers("");
        // 处置人查询
        List<CardTask> cardTasks = cardTaskService.lambdaQuery()
                .eq(CardTask::getNodeDetailId, nodeId)
                .eq(CardTask::getIsDelete, IsDeleteEnum.NORMAL.getStatus())
                .list();
        String handlers = cardTasks.stream().map(CardTask::getUserName).collect(Collectors.joining(","));
        monitorBusinessVO.setHandlers(handlers);
        // 完善处理人姓名信息
        if (StringUtils.isBlank(handlers) && StringUtils.isNotBlank(eventNodeDetail.getOperators()) && !"null".equals(eventNodeDetail.getOperators())) {
            Set<String> phones = Arrays.stream(eventNodeDetail.getOperators().split(","))
                    .filter(item -> !"null".equals(item))
                    .collect(Collectors.toSet());
            List<KnowledgeBase> list = knowledgeBaseService.lambdaQuery().in(KnowledgeBase::getPhone, phones).list();
            if (CollectionUtils.isNotEmpty(list)) {
                handlers = list.stream().map(KnowledgeBase::getName).distinct().collect(Collectors.joining(","));
                monitorBusinessVO.setHandlers(handlers);
            }
        }
        // 事件描述查询
        Event event = eventService.getById(eventNodeDetail.getEventId());
        monitorBusinessVO.setEventDesc(event.getEventDesc());
        // 督导人查询
        List<MonitorRelation> monitorRelations = monitorRelationService.lambdaQuery().eq(MonitorRelation::getMonitoredNodeId, nodeId).list();
        if (CollectionUtils.isNotEmpty(monitorRelations)) {
            Set<Long> nodeIds = monitorRelations.stream().map(MonitorRelation::getMonitorNodeId).collect(Collectors.toSet());
            // 查卡片查人，
            List<CardTask> list = cardTaskService.lambdaQuery()
                    .in(CardTask::getNodeDetailId, nodeIds)
                    .eq(CardTask::getIsDelete, IsDeleteEnum.NORMAL.getStatus())
                    .list();
            String monitors = list.stream().map(CardTask::getUserName).collect(Collectors.joining(","));
            monitorBusinessVO.setMonitors(monitors);
            // 完善督导人姓名信息
            if (StringUtils.isBlank(monitors)) {
                List<EventNodeDetail> eventNodeDetails = eventNodeDetailService.lambdaQuery().in(EventNodeDetail::getId, nodeIds).list();
                Set<String> phones = new HashSet<>();
                eventNodeDetails.stream().filter(item -> StringUtils.isNotBlank(item.getOperators()) && !"null".equals(item.getOperators())).forEach(item -> {
                    Collections.addAll(phones, item.getOperators().split(","));
                });
                if (CollectionUtils.isNotEmpty(phones)) {
                    List<KnowledgeBase> knowledgeBases = knowledgeBaseService.lambdaQuery().in(KnowledgeBase::getPhone, phones).list();
                    if (CollectionUtils.isNotEmpty(list)) {
                        monitors = knowledgeBases.stream().map(KnowledgeBase::getName).distinct().collect(Collectors.joining(","));
                        monitorBusinessVO.setMonitors(monitors);
                    }
                }
            }
        }
        // 督导记录查询
        List<MonitorRecord> monitorRecords = monitorRecordService.lambdaQuery()
                .eq(MonitorRecord::getMonitoredNodeDtlId, nodeId)
                .eq(MonitorRecord::getIsDelete, IsDeleteEnum.NORMAL.getStatus())
                .list();
        monitorBusinessVO.setMonitorRecords(monitorRecords);
        return monitorBusinessVO;
    }

    /**
     * 卡片查询数据组装
     *
     * @param cardTask        卡片任务
     * @param event           事件信息
     * @param eventNodeDetail 节点信息
     * @return 查询卡片信息
     */
    private QueryCardTaskVO handleQueryCardTaskData(CardTask cardTask, Event event, EventNodeDetail eventNodeDetail) {
        QueryCardTaskVO vo = new QueryCardTaskVO();
        BeanUtils.copyProperties(cardTask, vo);
        vo.setEventId(event.getId());
        vo.setEventDesc(event.getEventDesc());
        vo.setBusinessType(event.getBusinessType());
        vo.setHandleSuggest(eventNodeDetail.getHandleSuggest());
        String receiverName = StringUtils.isEmpty(event.getReceiverName()) ? " " : event.getReceiverName();
        String typeName = StringUtils.isEmpty(BusinessTypeEnum.descOfBusinessType(event.getBusinessType())) ? " " : BusinessTypeEnum.descOfBusinessType(event.getBusinessType());
        String title = receiverName + "发出了" + typeName + "预警";
        vo.setTitle(title);
        vo.setNodeType(eventNodeDetail.getNodeType());
        vo.setOccurTime(event.getOccurTime());
        vo.setOperationList(eventNodeDetail.getOperationList());
        vo.setOperateStatus(cardTask.getOperateStatus());
        vo.setOperatorName(cardTask.getUserName());

        // 完善处理人姓名信息
        if (StringUtils.isBlank(vo.getOperatorName()) && StringUtils.isNotBlank(eventNodeDetail.getOperators()) && !"null".equals(eventNodeDetail.getOperators())) {
            Set<String> phones = Arrays.stream(eventNodeDetail.getOperators().split(",")).collect(Collectors.toSet());
            List<KnowledgeBase> list = knowledgeBaseService.lambdaQuery().in(KnowledgeBase::getPhone, phones).list();
            if (CollectionUtils.isNotEmpty(list)) {
                String handlers = list.stream().map(KnowledgeBase::getName).distinct().collect(Collectors.joining(","));
                vo.setUserName(handlers);
                vo.setOperatorName(handlers);
            }
        }
        return vo;
    }
}
