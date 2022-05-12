package com.study.city.controller;

import com.study.city.entity.characters.Characters;
import com.study.city.service.ICharactersService;
import com.study.starter.utils.DateUtils;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 生日性格
 *
 * @author zhangpba
 * @date 2022-05-06
 */
@Api(value = "生日性格", tags = "生日性格")
@RestController
@RequestMapping("/character")
public class CharactersController {
    private static final Logger logger = LoggerFactory.getLogger(CharactersController.class);

    @Autowired
    ICharactersService charactersService;

    /**
     * 根据生日查询人的性格
     *
     * @param month 生日-月
     * @param day   生日-日
     * @return
     */
    @ApiOperation(value = "根据生日查询人的性格")
    @GetMapping(value = "/getCharacterByBrithday")
    public ResponseMessage getCharacterByBrithday(@ApiParam(name = "month", value = "生日-月", required = true) @RequestParam String month,
                                                  @ApiParam(name = "day", value = "生日-日", required = true) @RequestParam String day) {

        String birthday = getBithhday(month, day);
        List<Characters> charactersList = new ArrayList<>();
        // 查询数据库中的
        Characters characters = charactersService.getCharacters(birthday);
        if (characters != null) {
            charactersList.add(characters);
        } else {
            // 查询天行数据往
            charactersList = charactersService.getCharacters(month, day);
            charactersService.batchAddcharacters(charactersList);
        }
        return ResponseMessage.success(charactersList);
    }

    /**
     * 同步数据100天的生日性格数据
     * 0 30 1 * * * # 每天01：30执行一次
     */
    @Scheduled(cron = "${module.character.syn-cron}")
    public void synData() {
        List<Characters> charactersList = charactersService.getAllCharacters();
        if (charactersList == null) {
            charactersList = new ArrayList<>();
        }
        if (charactersList.size() >= 365) {
            logger.info("一年366天，每一天的性格全部已经同步过！");
            return;
        }
        for (int i = 0; i < 365; i++) {
            String date = DateUtils.getDay(new Date(), i);
            String[] dataArray = date.split("-");
            String month = dataArray[1];
            String day = dataArray[2];
            try {
                // 处理 API调用频率超限的问题
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Characters> characterses = charactersService.getCharacters(month, day);
            charactersService.batchAddcharacters(characterses);
        }
    }


    /**
     * 手动触发同步性格定时任务
     *
     * @return
     */
    @ApiOperation(value = "手动触发同步性格定时任务")
    @GetMapping(value = "/send")
    public ResponseMessage send() {
        logger.info("手动触发同步性格定时任务 start！");
        synData();
        return ResponseMessage.success("发送成功");
    }

    /**
     * 发送带Thymeleaf模板邮件
     *
     * @return
     */
    @ApiOperation(value = "发送带Thymeleaf模板邮件")
    @GetMapping(value = "/sendThymeleafMail")
    public ResponseMessage sendThymeleafMail(@ApiParam(name = "month", value = "生日-月", required = true) @RequestParam String month,
                                             @ApiParam(name = "day", value = "生日-日", required = true) @RequestParam String day,
                                             @ApiParam(name = "toUsers", value = "邮箱,多个邮箱需要用英文','连接", required = true) @RequestParam String toUsers) {
        logger.info("发送带Thymeleaf模板邮件案 start...");
        try {
            String birthday = getBithhday(month, day);
            charactersService.sendThymeleafMail(birthday, toUsers);
        } catch (MessagingException e) {
            logger.error("发送带Thymeleaf模板邮件失败：{}", e.getMessage());
            return ResponseMessage.success("发送带Thymeleaf模板邮件失败：" + e.getMessage());
        }
        return ResponseMessage.success("发送带Thymeleaf模板邮件！");
    }

    private String getBithhday(String month, String day) {
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        String birthday = month + day;
        return birthday;
    }
}
