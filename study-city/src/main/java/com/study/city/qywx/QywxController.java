package com.study.city.qywx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.study.city.qywx.entity.QywxEntityBase;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Dictionary;

/**
 * 角色信息表(SysRole)表控制层
 *
 * @author zhangpba
 * @since 2023-01-12 19:16:53
 */
@RestController
@RequestMapping("qywx")
@Api(value = "企业微信", tags = "企业微信")
public class QywxController {

    private static final Logger logger = LoggerFactory.getLogger(QywxController.class);

    @Resource
    private RestTemplate restTemplate;

    @Value("${qywx.config.corpId}")
    private String corpId;

    @Value("${qywx.config.corpSecret}")
    private String corpSecret;

    @Value("${qywx.config.agentId}")
    private String agentId;

    @Value("${qywx.url.get-token}")
    private String getTokenUrl;

    @Value("${qywx.url.send-message}")
    private String sendMessageUrl;

    /**
     * 分页查询角色
     *
     * @return 查询结果
     */
    @GetMapping
    @ApiOperation(value = "获取企业微信token", response = Dictionary.class)
    public ResponseMessage getToken() {
        return ResponseMessage.success(getTokenServer());
    }


    private String getTokenServer() {
        StringBuffer buffer = new StringBuffer(getTokenUrl);
        buffer.append("?");
        buffer.append("corpid=");
        buffer.append(corpId);
        buffer.append("&corpsecret=");
        buffer.append(corpSecret);
        QywxEntityBase qywxEntityBase = null;
        try {
            logger.info("请求url:{}", buffer.toString());
            qywxEntityBase = restTemplate.getForObject(buffer.toString(), QywxEntityBase.class);
            logger.info("返回的token:{}", qywxEntityBase.getAccess_token());
            if (qywxEntityBase.getErrcode() == 0) {
                return qywxEntityBase.getAccess_token();
            } else {
                return qywxEntityBase.getErrmsg();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    /**
     * 给企业应用发送消息
     *
     * @return 查询结果
     */
    @PostMapping("/sendMessageUrl")
    @ApiOperation(value = "给企业应用发送消息", response = Dictionary.class)
    public ResponseMessage sendMessageUrl() {
        StringBuffer buffer = new StringBuffer(sendMessageUrl);
        buffer.append("?");
        buffer.append("access_token=");
        buffer.append(getTokenServer());
        try {
            JSONObject map = new JSONObject();
            map.put("touser", "ZhangPengBo");
            map.put("toparty", "PartyID1|PartyID2");
            map.put("totag", "TagID1 | TagID2");
            map.put("msgtype", "text");
            map.put("agentid", 1000002);
            JSONObject text = new JSONObject();
            text.put("content", "您的企业微信的第一条消息已经发送成功，请携带工卡前往邮件中心领取。");
            map.put("text", text);
            map.put("safe", 0);
            map.put("enable_id_trans", 0);
            map.put("enable_duplicate_check", 0);
            map.put("duplicate_check_interval", 1800);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(map, null);

            String requestJson = JSON.toJSONString(request);
            logger.info("请求url:{}", buffer.toString());
            logger.info("请求参数:{}", requestJson);
            String result = restTemplate.postForObject(buffer.toString(), requestJson, String.class);
            return ResponseMessage.success(result);
        } catch (Exception e) {
            return ResponseMessage.error(e.getMessage());
        }
    }


    /**
     * 给企业应用发送消息1
     *
     * @return 查询结果
     */
    @PostMapping("/sendMessageUrl1")
    @ApiOperation(value = "给企业应用发送消息1", response = Dictionary.class)
    public ResponseMessage sendMessageUrl1(@RequestBody String s) {
        StringBuffer buffer = new StringBuffer(sendMessageUrl);
        buffer.append("?");
        buffer.append("access_token=");
        buffer.append(getTokenServer());
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String body = "{\n" +
                    "    \"touser\" : \"ZhangPengBo\",\n" +
                    "    \"toparty\" : \"PartyID1 | PartyID2\",\n" +
                    "    \"totag\" : \"TagID1 | TagID2\",\n" +
                    "    \"msgtype\" : \"template_card\",\n" +
                    "    \"agentid\" : 1000002,\n" +
                    "    \"template_card\" : {\n" +
                    "        \"card_type\" : \"news_notice\",\n" +
                    "        \"source\" : {\n" +
                    "            \"icon_url\": \"图片的url\",\n" +
                    "            \"desc\": \"企业微信\",\n" +
                    "            \"desc_color\": 1\n" +
                    "        },\n" +
                    "        \"action_menu\": {\n" +
                    "            \"desc\": \"卡片副交互辅助文本说明\",\n" +
                    "            \"action_list\": [\n" +
                    "                {\"text\": \"接受推送\", \"key\": \"A\"},\n" +
                    "                {\"text\": \"不再推送\", \"key\": \"B\"}\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        \"task_id\": \"task_id\",\n" +
                    "        \"main_title\" : {\n" +
                    "            \"title\" : \"欢迎使用企业微信\",\n" +
                    "            \"desc\" : \"您的好友正在邀请您加入企业微信\"\n" +
                    "        },\n" +
                    "        \"quote_area\": {\n" +
                    "            \"type\": 1,\n" +
                    "            \"url\": \"https://work.weixin.qq.com\",\n" +
                    "            \"title\": \"企业微信的引用样式\",\n" +
                    "            \"quote_text\": \"企业微信真好用呀真好用\"\n" +
                    "        },\n" +
                    "        \"image_text_area\": {\n" +
                    "            \"type\": 1,\n" +
                    "            \"url\": \"https://work.weixin.qq.com\",\n" +
                    "            \"title\": \"企业微信的左图右文样式\",\n" +
                    "            \"desc\": \"企业微信真好用呀真好用\",\n" +
                    "            \"image_url\": \"https://img.iplaysoft.com/wp-content/uploads/2019/free-images/free_stock_photo_2x.jpg\"\n" +
                    "        },\n" +
                    "        \"card_image\": {\n" +
                    "            \"url\": \"图片的url\",\n" +
                    "            \"aspect_ratio\": 1.3\n" +
                    "        },\n" +
                    "        \"vertical_content_list\": [\n" +
                    "            {\n" +
                    "                \"title\": \"惊喜红包等你来拿\",\n" +
                    "                \"desc\": \"下载企业微信还能抢红包！\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"horizontal_content_list\" : [\n" +
                    "            {\n" +
                    "                \"keyname\": \"邀请人\",\n" +
                    "                \"value\": \"张三\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"type\": 1,\n" +
                    "                \"keyname\": \"企业微信官网\",\n" +
                    "                \"value\": \"点击访问\",\n" +
                    "                \"url\": \"https://work.weixin.qq.com\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"type\": 2,\n" +
                    "                \"keyname\": \"企业微信下载\",\n" +
                    "                \"value\": \"企业微信.apk\",\n" +
                    "                \"media_id\": \"文件的media_id\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"type\": 3,\n" +
                    "                \"keyname\": \"员工信息\",\n" +
                    "                \"value\": \"点击查看\",\n" +
                    "                \"userid\": \"zhangsan\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"jump_list\" : [\n" +
                    "            {\n" +
                    "                \"type\": 1,\n" +
                    "                \"title\": \"企业微信官网\",\n" +
                    "                \"url\": \"https://work.weixin.qq.com\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"type\": 2,\n" +
                    "                \"title\": \"跳转小程序\",\n" +
                    "                \"appid\": \"小程序的appid\",\n" +
                    "                \"pagepath\": \"/index.html\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"card_action\": {\n" +
                    "            \"type\": 2,\n" +
                    "            \"url\": \"https://work.weixin.qq.com\",\n" +
                    "            \"appid\": \"小程序的appid\",\n" +
                    "            \"pagepath\": \"/index.html\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"enable_id_trans\": 0,\n" +
                    "    \"enable_duplicate_check\": 0,\n" +
                    "    \"duplicate_check_interval\": 1800\n" +
                    "}";

            JSONObject jsonObject = JSONObject.parseObject(body);
            HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(jsonObject, null);

            String requestJson = JSON.toJSONString(request);
            logger.info("请求url:{}", buffer.toString());
            logger.info("请求参数:{}", requestJson);
            String result = restTemplate.postForObject(buffer.toString(), requestJson, String.class);
            return ResponseMessage.success(result);
        } catch (Exception e) {
            return ResponseMessage.error(e.getMessage());
        }
    }


}

