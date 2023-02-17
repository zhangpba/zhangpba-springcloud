package com.data.chain.comsumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.data.chain.admin.entity.AdminUser;
import com.data.chain.cardmanage.dao.CardTaskDao;
import com.data.chain.cardmanage.service.CardTaskService;
import com.data.chain.cardmanage.vo.CardTaskVO;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.entity.EnterpriseInfo;
import com.data.chain.eventflow.entity.EventNodeDetail;
import com.data.chain.eventflow.service.EnterpriseInfoService;
import com.data.chain.eventflow.service.EventNodeDetailService;
import com.data.chain.eventflow.service.EventService;
import com.data.chain.eventflow.service.FlowModelService;
import com.data.chain.eventflow.vo.FlowModelVO;
import com.data.chain.utils.ConsumerUtils;
import com.data.chain.utils.ProducerUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zhangpba
 * @description kafka消费者
 * @date 2022/8/8
 */
@Component
public class ConsumerRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerRunner.class);

    // 监听的topic
    @Value("${kafka.consumer.topics}")
    private String topics;
    @Autowired
    private ConsumerUtils consumerUtils;
    @Autowired
    private EventService eventService;
    @Autowired
    private FlowModelService flowModelService;
    @Autowired
    private EventNodeDetailService eventNodeDetailService;
    @Autowired
    private CardTaskService cardTaskService;
    @Autowired
    private EnterpriseInfoService enterpriseInfoService;

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void run(String... args) {
        KafkaConsumer<String, String> consumer = consumerUtils.buildConsumer();

        String[] topicsArray = topics.split(",");
        // 消费者订阅的topic, 可同时订阅多个
        consumer.subscribe(Arrays.asList(topicsArray));
        while (true) {
            // 读取数据，读取超时时间为100ms
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                // 1、消费业务系统发过来的消息 TOPIC=test
                logger.info("topic:{},offset:{},消息:{}", record.topic(), record.offset(), record.value());
                logger.info("================================业务系统报文 接收成功！===========================================");

                try {
                    // 2、解析消息：结合流程配置，生成事件实体
                    String value = record.value();
                    EventDTO eventDTO = JSONObject.parseObject(value, EventDTO.class);
                    if (eventDTO == null) {
                        logger.info("入参错误，record：{}", JSON.toJSONString(eventDTO));
                        continue;
                    }
                    // 查企业对应的监察部门监管机构
                    Optional<EnterpriseInfo> enOpt = enterpriseInfoService.lambdaQuery()
                                    .eq(EnterpriseInfo::getEnterpriseCode, eventDTO.getReceiverCode()).oneOpt();
                    if (enOpt.isPresent()){
                        EnterpriseInfo enterpriseInfo = enOpt.get();
                        eventDTO.setProvince(enterpriseInfo.getProvince());
                        eventDTO.setProvinceNm(enterpriseInfo.getProvinceName());
                        eventDTO.setEnforcement(enterpriseInfo.getEnforcement());
                        eventDTO.setEnforcementName(enterpriseInfo.getEnforcementName());
                        eventDTO.setCity(enterpriseInfo.getCity());
                        eventDTO.setCityNm(enterpriseInfo.getCityName());
                        eventDTO.setCounty(enterpriseInfo.getCounty());
                        eventDTO.setCountyNm(enterpriseInfo.getCountyName());
                    }else{
                        logger.info("该企业没有同步监察/监管机构信息：企业编码：{}；企业名称：{}",eventDTO.getReceiverCode(),eventDTO.getReceiverName());
                        continue;
                    }
                    Long eventId = eventService.saveEvent(eventDTO);
                    eventDTO.setId(eventId);
                    // 3、事件信息匹配流程模板
                    FlowModelVO flowModelVO = flowModelService.matchFlowModel(eventDTO);
                    logger.info("用事件信息匹配流程模版，关联流程节点OK,结果:{}",JSON.toJSONString(flowModelVO));
                    if (flowModelVO == null){
                        logger.error("匹配流程模板失败：{}",JSON.toJSONString(flowModelVO));
                        continue;
                    }
                    // 4、流程模板节点生成事件流程节点明细数据,督导关系数据在这里生成
                    List<EventNodeDetail> eventNodeDetails = eventNodeDetailService.makeEventNodeDetails(eventDTO, flowModelVO);
                    logger.info("根据流程模板节点生成事件流程节点明细数据OK，结果:{}",JSON.toJSONString(eventNodeDetails));
                    // 5、流程节点明细数据指定的处置人手机号匹配企业微信用户ID，生成卡片消息体，并放入Kafka队列中
                    List<CardTaskVO> cardTaskVOList = eventNodeDetailService.makeCardMsgs(eventDTO, eventNodeDetails);
                    logger.info("卡片消息生成OK，结果:{}",JSON.toJSONString(cardTaskVOList));
                    if (cardTaskVOList == null){
                        logger.error("生成卡片消息体失败：{}",JSON.toJSONString(cardTaskVOList));
                        continue;
                    }
                    // 6、将消息体放到Kafka队列中
                    cardTaskService.sendCardMsgToKafka(cardTaskVOList, eventDTO);
                } catch (Exception e) {
                    logger.info("发送卡片消息失败，异常信息：{}", e.getMessage());
                }
                // 6、将生成的卡片消息体存入Kafka，卡片发送 TOPIC=card
                // 请求头设置,x-www-form-urlencoded格式的数据
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.APPLICATION_JSON);
//
//                HttpEntity<String> httpEntity = new HttpEntity<>(record.value(), headers);
//
//                logger.info("================================给企业微信发送报文 发送给卡片的报文信息：===========================================");
//                logger.info("发送给卡片的报文信息：" + httpEntity.getBody());
//                ResponseEntity<String> exchange = restTemplate.exchange(ipassCardUrl, HttpMethod.POST, httpEntity, String.class);
//                logger.info("================================给企业微信报文 发送成功！===========================================");
            }
        }
    }
}
