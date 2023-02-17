package com.data.chain.eventflow.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.chain.cardmanage.entity.CardTask;
import com.data.chain.cardmanage.entity.MonitorRelation;
import com.data.chain.cardmanage.service.CardTaskService;
import com.data.chain.cardmanage.service.MonitorRelationService;
import com.data.chain.cardmanage.vo.CardTaskVO;
import com.data.chain.eventflow.dao.EventNodeDetailDao;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.entity.EventNodeDetail;
import com.data.chain.eventflow.service.EventNodeDetailService;
import com.data.chain.eventflow.vo.FlowModelNodeVO;
import com.data.chain.eventflow.vo.FlowModelVO;
import com.data.chain.knowledgebase.dao.KnowledgeBaseDao;
import com.data.chain.knowledgebase.entity.KnowledgeBase;
import com.data.chain.utils.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 事件处置流程节点明细表(EventNodeDetail)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 11:09:12
 */
@Service
public class EventNodeDetailServiceImpl extends ServiceImpl<EventNodeDetailDao, EventNodeDetail> implements EventNodeDetailService {
    Logger logger = LoggerFactory.getLogger(EventNodeDetailServiceImpl.class);

    @Autowired
    EventNodeDetailDao eventNodeDetailDao;
    @Autowired
    KnowledgeBaseDao knowledgeBaseDao ;
    @Autowired
    MonitorRelationService monitorRelationService;
    @Autowired
    CardTaskService cardTaskService ;

    @Value("${qywx.url.getUserId}")
    private String getUserIdUrl ;
    @Value("${admin.userId}")
    private String adminUserId ;
    @Value("${admin.userName}")
    private String adminUserName ;


    @Override
    public List<EventNodeDetail> makeEventNodeDetails(EventDTO eventDTO, FlowModelVO flowModelVO) {
        logger.info("生成节点明细数据，入参eventDTO:{},入参flowModelVO:{}",JSON.toJSONString(eventDTO),JSON.toJSONString(flowModelVO));
        List<EventNodeDetail> list = new ArrayList<>() ;
        // 组织机构列表
        List<String> orgList = new ArrayList<>();
        List<String> operatorTypeList = new ArrayList<>();
        for (FlowModelNodeVO flowModelNodeVO : flowModelVO.getFlowModelNodeVOList()){
            operatorTypeList.addAll(Arrays.asList(flowModelNodeVO.getOperatorTypes().split(","))); // 接收人类型
            orgList.add(eventDTO.getProvince());// 省 - 监管
            orgList.add(eventDTO.getEnforcement()); // 执法处 - 监察
            orgList.add(eventDTO.getCity());//市 - 监管
            orgList.add(eventDTO.getCounty()); // 区县 - 监管
            orgList.add(eventDTO.getReceiverCode()); // 企业ID
        }
        // 根据事件查询知识库
        List<KnowledgeBase> knowledgeBases = knowledgeBaseDao.searchReceiverList(orgList,operatorTypeList);
        logger.info("查询知识库结果:{}",JSON.toJSONString(knowledgeBases));
        Map<String,String> operatorPhoneMap = new HashMap<>();
        for (KnowledgeBase knowledgeBase:knowledgeBases){
            if (orgList.contains(knowledgeBase.getOrgId())){
                String oldStr = operatorPhoneMap.get(knowledgeBase.getOperatorType());
                String addStr = knowledgeBase.getPhone() ;
                operatorPhoneMap.put(knowledgeBase.getOperatorType(),StringUtils.isNotEmpty(oldStr)?(oldStr + ",")+addStr:addStr);
            }
        }
        // 生成流程节点明细数据
        List<FlowModelNodeVO> flowModelNodeVOList = flowModelVO.getFlowModelNodeVOList();
        Map<String,Long> map = new HashMap<>();
        for (FlowModelNodeVO modelNodeVO:flowModelNodeVOList){
            EventNodeDetail eventNode = new EventNodeDetail();
            eventNode.setEventId(eventDTO.getId());
            eventNode.setFlowModelId(modelNodeVO.getModelId());
            for (String operatorType : modelNodeVO.getOperatorTypes().split(",")){
                String oldStr = eventNode.getOperators();
                String addStr = operatorPhoneMap.get(operatorType);
                eventNode.setOperators(StringUtils.isNotEmpty(oldStr)?(oldStr + ",")+addStr:addStr);  // 这里换成手机号
            }
            eventNode.setChatId(modelNodeVO.getChatId());
            eventNode.setPassWay(modelNodeVO.getPassWay());
            eventNode.setNodeType(modelNodeVO.getNodeType());
            eventNode.setOperationList(modelNodeVO.getOperationList());
            eventNode.setHandleSuggest(modelNodeVO.getHandleSuggest());
            eventNode.setStatus(0);
            eventNode.setIsValid(0);
            eventNode.setCreateTime(new Date());
            // 插入节点明细数据，记录id
            logger.info("插入节点明细数据,入参eventNode：{}",JSON.toJSONString(eventDTO));
            eventNodeDetailDao.insertEventNodeDetail(eventNode);
            Long eventNodeId = eventNode.getId() ;
            eventNode.setPreNodeDtlId(modelNodeVO.getPreNodeId());// 暂时将模板节点的前一个节点记入事件节点的前一个节点
            eventNode.setNextNodeDtlId(modelNodeVO.getNextNodeId());// 暂时将模板节点的后一个节点记入事件节点的后一个节点
            list.add(eventNode);
            map.put(modelNodeVO.getId()+"",eventNodeId);
        }
        logger.info("节点明细数据保存成功，开始维护督导关系，flowModelNodeVOList:{}",JSON.toJSONString(flowModelNodeVOList));
        // 节点明细间的督导关系
        for(FlowModelNodeVO modelNode:flowModelNodeVOList){
            if (StringUtils.isNotEmpty(modelNode.getMonitorNodes())){ // 如果该节点的督导节点不为空
                for (String modelNodeId : modelNode.getMonitorNodes().split(",")){
                    Long eventNodeId = map.get(modelNodeId);  // 取出事件节点ID为督导节点明细ID
                    Long monitoredId = map.get(modelNode.getId()+"") ; // 被督导节点明细ID
                    if (eventNodeId != null && monitoredId != null){
                        MonitorRelation monitorRelation = new MonitorRelation();
                        monitorRelation.setMonitorNodeId(eventNodeId);
                        monitorRelation.setMonitoredNodeId(monitoredId);
                        monitorRelationService.insert(monitorRelation);
                    }
                }
            }
        }
        // 根据流程模板中的链表顺序排列流程节点明细的链表顺序
        logger.info("根据流程模板中的链表顺序排列流程节点明细的链表顺序,list:{}",JSON.toJSONString(list));
        for (EventNodeDetail eventNode:list){
            if (eventNode.getPreNodeDtlId() != null){
                eventNode.setPreNodeDtlId(map.get(eventNode.getPreNodeDtlId() + ""));
            }
            if (eventNode.getNextNodeDtlId() != null){
                eventNode.setNextNodeDtlId(map.get(eventNode.getNextNodeDtlId() + ""));
            }
        }
        int result = eventNodeDetailDao.updatePreNext(list);
        return list;
    }


    @Override
    public List<CardTaskVO> makeCardMsgs(EventDTO eventDTO, List<EventNodeDetail> eventNodeDetails) {
        logger.info("开始构造卡片消息体数据,入参eventDTO：{}，入参eventNodeDetails:{}",JSON.toJSONString(eventDTO),JSON.toJSONString(eventNodeDetails));
        List<CardTaskVO> cardMsgList = new ArrayList<>() ;
        for (EventNodeDetail eventNodeDetail : eventNodeDetails){
            // 创建头节点的卡片消息体
            List<String> phoneNumArr ;
            if (eventNodeDetail.getPreNodeDtlId() == null){
                if (eventNodeDetail.getOperators() == null){
                    // 如果知识库中没有配置接收人的手机号，发送给系统管理员，告知其维护数据
                    CardTaskVO cardMsgBody = getCardTaskVO(eventDTO, eventNodeDetail, adminUserId, adminUserName);
                    cardMsgList.add(cardMsgBody) ;
                }else {
                    phoneNumArr = Arrays.asList(eventNodeDetail.getOperators().split(",")); //手机号
                    QueryWrapper<KnowledgeBase> queryWrapper = new QueryWrapper();
                    queryWrapper.in("PHONE", phoneNumArr);
                    List<KnowledgeBase> list = knowledgeBaseDao.selectList(queryWrapper );
                    Map<String,String> phoneNameMap = new HashMap<>();
                    for (KnowledgeBase base : list){
                        phoneNameMap.put(base.getPhone(),base.getName());
                    }
                    //  用手机号查企微ID
                    JSONObject obj = new JSONObject()   ;
                    obj.put("phone_list",phoneNumArr);
                    HttpUtil httpUtil = HttpUtil.create();
                    HttpUtil post = httpUtil.createPost(getUserIdUrl, obj.toJSONString());
                    logger.info("调用HTTP接口获取企微用户ID，请求路径：{}，请求参数:{}",getUserIdUrl,obj.toJSONString());
                    String data = HttpUtil.getData(post.execute());
                    logger.info("调用HTTP接口获取企微用户ID，响应结果：{}", data);
                    if (StringUtils.isEmpty(data)){
                        return null ;
                    }
                    HashMap<String,String> phoneMap = null;
                    try {
                        phoneMap = JSONObject.parseObject(data, HashMap.class);
                    } catch (Exception e) {
                        logger.error("查找手机号对应的企微用户ID失败，入参：{},响应结果：{}",obj.toJSONString(),data);
                    }
                    // 数据插入卡片表，给企微用户发送卡片消息
                    for (String phoneNum : phoneNumArr){
                        CardTaskVO cardMsgBody = getCardTaskVO(eventDTO, eventNodeDetail, phoneMap.get(phoneNum), phoneNameMap.get(phoneNum));
                        cardMsgList.add(cardMsgBody) ;
                    }
                }
            }
        }
        return cardMsgList ;
    }

    private CardTaskVO getCardTaskVO(EventDTO eventDTO, EventNodeDetail eventNodeDetail, String adminUserId, String adminUserName) {
        CardTask cardTask = new CardTask();
        cardTask.setUserId(adminUserId);
        cardTask.setUserName(adminUserName);
        cardTask.setCreateTime(new Date());
        cardTask.setOperateStatus(0);
        cardTask.setNodeDetailId(eventNodeDetail.getId());
        logger.info("保存卡片消息数据，入参:{}", JSON.toJSONString(cardTask));
        cardTaskService.saveCardTask(cardTask);
        Long cardId = cardTask.getId();
        // 构造卡片消息体：
        CardTaskVO cardMsgBody = new CardTaskVO();
        cardMsgBody.setId(cardId); // 卡片消息ID
        cardMsgBody.setNodeDetailId(eventNodeDetail.getId()); // 节点明细ID
        cardMsgBody.setUserId(adminUserId);   // 接收人企微号
        cardMsgBody.setOperateStatus(0); // 处置状态：未接受
        cardMsgBody.setChatId(eventNodeDetail.getChatId());  // 群聊ID，如果有就往群里发消息，没有就不发
        cardMsgBody.setEventId(eventDTO.getId());  // 事件ID
        cardMsgBody.setEventDesc(eventDTO.getEventDesc()); // 事件描述
        cardMsgBody.setHandleSuggest(eventNodeDetail.getHandleSuggest()); // 处置建议
        cardMsgBody.setEventParams(eventDTO.getEventParamsList());  // 事件参数列表
        cardMsgBody.setPreNodeId(eventNodeDetail.getPreNodeDtlId());  // 前一个节点
        cardMsgBody.setNextNodeId(eventNodeDetail.getNextNodeDtlId());  // 下个节点
        cardMsgBody.setOperationList(eventNodeDetail.getOperationList()); // 可选操作
        cardMsgBody.setUserName(adminUserName);
        return cardMsgBody;
    }
}

