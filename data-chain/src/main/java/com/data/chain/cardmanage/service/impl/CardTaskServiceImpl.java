package com.data.chain.cardmanage.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.chain.base.ResponseEnum;
import com.data.chain.base.ResponseVO;
import com.data.chain.cardmanage.dao.CardTaskDao;
import com.data.chain.cardmanage.entity.CardTask;
import com.data.chain.cardmanage.enums.HandleStatusEnum;
import com.data.chain.cardmanage.service.CardTaskService;
import com.data.chain.cardmanage.vo.CardTaskVO;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.entity.Event;
import com.data.chain.eventflow.entity.EventNodeDetail;
import com.data.chain.eventflow.entity.EventParams;
import com.data.chain.eventflow.enums.BusinessTypeEnum;
import com.data.chain.eventflow.service.EventParamsService;
import com.data.chain.eventflow.service.EventService;
import com.data.chain.knowledgebase.entity.KnowledgeBase;
import com.data.chain.knowledgebase.service.KnowledgeBaseService;
import com.data.chain.utils.HttpUtil;
import com.data.chain.utils.ProducerUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 卡片消息表，每一个卡片代表一个任务(CardTask)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 11:07:06
 */
@Service
public class CardTaskServiceImpl extends ServiceImpl<CardTaskDao, CardTask> implements CardTaskService {
    Logger logger = LoggerFactory.getLogger(CardTaskServiceImpl.class);

    @Autowired
    CardTaskDao cardTaskDao;
    @Autowired
    EventService eventService;
    @Value("${qywx.url.getUserId}")
    private String getUserIdUrl;
    @Autowired
    EventParamsService eventParamsService;
    @Autowired
    KnowledgeBaseService knowledgeBaseService;
    @Autowired
    ProducerUtils producerUtils;
    // 卡片发送地址
    @Value("${kafka.producer.user-msg-topic}")
    private String userMsgTopic;
    @Value("${kafka.producer.group-msg-topic}")
    private String groupMsgTopic;
    @Value("${ipaas.card-msg-detail}")
    private String cardMsgDetail ;

    @Override
    public Long saveCardTask(CardTask cardTask) {
        return cardTaskDao.saveCardTask(cardTask);
    }

    @Override
    public ResponseVO<Boolean> newCardTaskByNodeDtl(EventNodeDetail eventNodeDetail) {
        List<CardTaskVO> cardMsgList = new ArrayList<CardTaskVO>();
        Event event = eventService.getById(eventNodeDetail.getEventId());
        QueryWrapper<EventParams> query = new QueryWrapper<>();
        query.eq("EVENT_ID", eventNodeDetail.getEventId());
        List<EventParams> eventParams = eventParamsService.list(query);
        List<String> phoneNumArr = Arrays.asList(eventNodeDetail.getOperators().split(",")); //手机号
        QueryWrapper<KnowledgeBase> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("PHONE", phoneNumArr);
        List<KnowledgeBase> list = knowledgeBaseService.list(queryWrapper);
        Map<String, String> phoneNameMap = new HashMap<>();
        for (KnowledgeBase base : list) {
            phoneNameMap.put(base.getPhone(), base.getName());
        }
        //  用手机号查企微ID
        JSONObject obj = new JSONObject();
        obj.put("phone_list", phoneNumArr);
        HttpUtil httpUtil = HttpUtil.create();
        HttpUtil post = httpUtil.createPost(getUserIdUrl, obj.toJSONString());
        logger.info("调用HTTP接口获取企微用户ID，请求路径：{}，请求参数:{}", getUserIdUrl, obj.toJSONString());
        HashMap<String, String> phoneMap = JSONObject.parseObject(HttpUtil.getData(post.execute()), HashMap.class);
        logger.info("调用HTTP接口获取企微用户ID，响应结果：{}", JSON.toJSONString(phoneMap));
        if (phoneMap == null) {
            return ResponseVO.fail(ResponseEnum.ERROR_402.getCode(), "未查询到手机号对应的企微用户");
        }
        // 数据插入卡片表，给企微用户发送卡片消息
        for (String phoneNum : phoneNumArr) {
            CardTask cardTask = new CardTask();
            cardTask.setUserId(phoneMap.get(phoneNum));
            cardTask.setUserName(phoneNameMap.get(phoneNum));
            cardTask.setCreateTime(new Date());
            cardTask.setOperateStatus(0);
            cardTask.setNodeDetailId(eventNodeDetail.getId());
            cardTaskDao.saveCardTask(cardTask);
            Long cardId = cardTask.getId();
            // 构造卡片消息体：
            CardTaskVO cardMsgBody = new CardTaskVO();
            cardMsgBody.setId(cardId); // 卡片消息ID
            cardMsgBody.setUserId(phoneMap.get(phoneNum));   // 接收人企微号
            cardMsgBody.setOperateStatus(0); // 处置状态：未接受
            cardMsgBody.setNodeDetailId(eventNodeDetail.getId()); // 节点明细ID
            cardMsgBody.setChatId(eventNodeDetail.getChatId());  // 群聊ID，如果有就往群里发消息，没有就不发
            cardMsgBody.setEventId(event.getId());  // 事件ID
            cardMsgBody.setEventDesc(event.getEventDesc()); // 事件描述
            cardMsgBody.setHandleSuggest(eventNodeDetail.getHandleSuggest()); // 处置建议
            cardMsgBody.setEventParams(eventParams);  // 事件参数列表
            cardMsgBody.setPreNodeId(eventNodeDetail.getPreNodeDtlId());  // 前一个节点
            cardMsgBody.setNextNodeId(eventNodeDetail.getNextNodeDtlId());  // 下个节点
            cardMsgBody.setOperationList(eventNodeDetail.getOperationList()); // 可选操作
            cardMsgBody.setUserName(phoneNameMap.get(phoneNum));
            cardMsgList.add(cardMsgBody);
        }
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setReceiverCode(event.getReceiverCode());
        eventDTO.setReceiverName(event.getReceiverName());
        eventDTO.setBusinessLevel(event.getBusinessLevel());
        eventDTO.setBusinessType(event.getBusinessType());
        eventDTO.setOccurTime(event.getOccurTime());
        eventDTO.setReceiverName(event.getReceiverName());
        sendCardMsgToKafka(cardMsgList, eventDTO);
        return ResponseVO.success(ResponseEnum.SUCCESS.getCode(), "成功发送到Kafka消息队列");
    }

    @Override
    public Boolean sendCardMsgToKafka(List<CardTaskVO> cardMsgList, EventDTO event) {
        logger.info("将消息体放到Kafka队列中,cardMsgList:{},event:{}",JSON.toJSONString(cardMsgList), JSON.toJSONString(event));
        // 6、将消息体放到Kafka队列中
        for (CardTaskVO cardTaskVO : cardMsgList) {
            try {
                JSONObject cardJson = new JSONObject();
                cardJson.put("user_Id", cardTaskVO.getUserId());
                cardJson.put("title", event.getReceiverName() + "发出了" + descOfBusinessType(event.getBusinessType()) + "预警");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                cardJson.put("time", dateFormat.format(event.getOccurTime()));
                cardJson.put("source", descOfBusinessType(event.getBusinessType()));
                cardJson.put("user_name",StringUtils.isEmpty(cardTaskVO.getUserName()) ? "" : cardTaskVO.getUserName());
                cardJson.put("recommend_dis", StringUtils.isEmpty(cardTaskVO.getHandleSuggest()) ? "" : cardTaskVO.getHandleSuggest());
                cardJson.put("url", cardMsgDetail + "?nodeDetailId=" + cardTaskVO.getNodeDetailId() + "&userId=" + cardTaskVO.getUserId());
                cardJson.put("state", descOfHandleStatus(cardTaskVO.getOperateStatus()));
                cardJson.put("msg_ID", cardTaskVO.getId());
                producerUtils.send(producerUtils.buildProducer(), userMsgTopic, JSONObject.toJSONString(cardJson));
                if (StringUtils.isNotEmpty(cardTaskVO.getChatId())) {
                    // 群消息内容
                    cardJson.put("group_ID", cardTaskVO.getChatId());
                    producerUtils.send(producerUtils.buildProducer(), groupMsgTopic, JSONObject.toJSONString(cardJson));
                }
                logger.info("卡片消息发送成功");
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<CardTask> selectNodeDtlsCards(Long id) {
        QueryWrapper<CardTask> query = new QueryWrapper<>();
        query.eq("NODE_DETAIL_ID",id);
        return cardTaskDao.selectList(query);
    }

    private String descOfBusinessType(String type) {
        BusinessTypeEnum[] values = BusinessTypeEnum.values();
        for (BusinessTypeEnum em : values) {
            if (em.getType().equals(type)) {
                return em.getDesc();
            }
        }
        return null;
    }

    private String descOfHandleStatus(Integer status) {
        HandleStatusEnum[] values = HandleStatusEnum.values();
        for (HandleStatusEnum em : values) {
            if (em.getStatus().equals(status)) {
                return em.getDesc();
            }
        }
        return null;
    }
}

