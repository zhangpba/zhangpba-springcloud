package com.study.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.constant.FeeApiUrl;
import com.study.city.entity.email.EmailLog;
import com.study.city.entity.tianxing.Characters;
import com.study.city.mapper.CharactersMapper;
import com.study.city.service.IEmailLogService;
import com.study.city.service.IEmailService;
import com.study.city.service.ITxCharactersService;
import com.study.common.utils.ArraysUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TxCharactersServiceImpl implements ITxCharactersService {

    private static final Logger logger = LoggerFactory.getLogger(TxCharactersServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CharactersMapper charactersMapper;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IEmailLogService emailLogService;

    @Value("${module.character.key}")
    private String key;

    @Value("${spring.mail.username}")
    private String from;

    public List<Characters> getCharacters(String month, String day) {
        List<Characters> charactersList = new ArrayList<>();
        String url = String.format(FeeApiUrl.TIANXING_CHARACTERS_URL, key, month, day);
        // 生日格式为：01-01
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        String birthday = month + day;

        // 如果已经同步过，就从数据库中直接获取
        Characters c = charactersMapper.getCharacters(birthday);
        if (c != null) {
            logger.info("{} 的性格数据已经同步过，不需要再次同步！", birthday);
            charactersList.add(c);
            return charactersList;
        } else {
            // 如果没有同步过，那么就请求API
            logger.info("同步{}的性格请求 url：{}", birthday, url);
            ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
            String body = (String) responseEntity.getBody();
            JSONObject bodyJson = (JSONObject) JSON.parse(body);
            logger.info("请求返回：{}", bodyJson);
            String msg = bodyJson.getString("msg");
            Integer code = bodyJson.getInteger("code");
            JSONArray newslist = bodyJson.getJSONArray("newslist");
            if ("success".equals(msg)) {
                for (Object obj : newslist) {
                    JSONObject newsJson = (JSONObject) obj;
                    String title = newsJson.getString("title");
                    String content = newsJson.getString("content");
                    Characters characters = new Characters();
                    characters.setTitle(title);
                    characters.setContent(content);
                    characters.setBrithday(birthday);
                    charactersList.add(characters);
                }
            }
            return charactersList;
        }
    }

    // 批量插入
    @Override
    public void batchAddcharacters(List<Characters> charactersList) {
        if (charactersList != null && !charactersList.isEmpty()) {
            for (Characters c : charactersList) {
                this.addCharacters(c);
            }
        }
    }

    @Override
    public int addCharacters(Characters characters) {
        Characters c = charactersMapper.getCharacters(characters.getBrithday());
        if (c == null) {
            return charactersMapper.addCharacters(characters);
        }
        return 0;
    }

    /**
     * 查询366天所有的性格
     *
     * @return 366天所有的性格
     */
    @Override
    public List<Characters> getAllCharacters() {
        return charactersMapper.getAllCharacters();
    }

    /**
     * 根据生日查询性格
     */
    @Override
    public Characters getCharacters(String brithday) {
        return charactersMapper.getCharacters(brithday);
    }

    /**
     * TODO 暂时不用了：Thymeleaf模板邮件
     */
    @Override
    public void sendThymeleafMail(String birthday, String toUsers) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("根据您的生日，对您的性格估计如下");
        helper.setFrom(from);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        String[] userList = emailService.getToUser(toUsers);
        logger.info("接收邮件的人：{}", userList);
        helper.setTo(userList);
        helper.setSentDate(new Date());
        // 这里引入的是Template的Context
        Context context = new Context();
        Characters characters = charactersMapper.getCharacters(birthday);
        // 格式化内容
        String[] charactersList = prase(characters.getContent());
        // 设置模板中的变量
        context.setVariable("characters", charactersList);
        logger.info("characters.getContent(): {}", characters.getContent());
        // 第一个参数为模板的名称
        String process = templateEngine.process("生日性格.html", context);
        // 第二个参数true表示这是一个html文本
        helper.setText(process, true);
        sender.send(mimeMessage);
    }

    /**
     * 发送普通邮件
     */
    @Override
    public void sendEmail(String birthday, String toUsers) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("根据您的生日，对您的性格估计如下");
        helper.setFrom(from);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        String[] userList = emailService.getToUser(toUsers);
        logger.info("接收邮件的人：{}", userList);
        helper.setTo(userList);
        helper.setSentDate(new Date());
        // 获取数据
        Characters characters = charactersMapper.getCharacters(birthday);
        String content = characters.getContent();
        // src='cid:p01' 占位符写法 ，第二个参数true表示这是一个html文本
        // 因为这个邮件中没有图片，所以删掉部分代码
        helper.setText(content, true);
        sender.send(mimeMessage);


        // 增加日志
        EmailLog emailLog = new EmailLog();
        emailLog.setTitle(null);
        emailLog.setContext(content);
        emailLog.setReceiveBcc(null);
        emailLog.setReceive(ArraysUtils.toString(userList));
        emailLog.setSendUsers(from);
        emailLog.setCount(1);
        emailLog.setSendTime(new Date());
        emailLog.setCreateDate(new Date());
        emailLogService.addEmailLog(emailLog);
    }


    /**
     * 格式化性格内容
     *
     * @param content 性格内容
     * @return
     */
    private String[] prase(String content) {
        List<String> result = new ArrayList();
        String[] pList = content.replaceAll("</p>", "")
                .replaceAll("<br><br>", "")
                .replace("<P>", "")
                .replaceAll("</strong>", "")
                .split("<p>");
        for (int i = 0; i < pList.length; i++) {
            String pStr = pList[i].replace("<P>", "");

            if (pStr.contains("<strong>")) {
                String[] strongs = pStr.split("<strong>");
                for (int j = 0; j < strongs.length; j++) {
                    setResult(result, strongs[j]);
                }
            } else {
                setResult(result, pStr);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    private void setResult(List<String> result, String hang) {
        // 如果<br>在尾部，那么用空格替代；
        if (hang.endsWith("<br>")) {
            String str = hang.replace("<br>", "");
            if (str != null && !str.isEmpty()) {
                result.add(str);
            }
        } else {
            // 如果不在尾部，用：代替
            String str = hang.replace("<br>", "：");
            if (str != null && !str.isEmpty()) {
                result.add(str);
            }
        }
    }
}
