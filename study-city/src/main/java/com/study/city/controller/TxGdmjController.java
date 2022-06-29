package com.study.city.controller;

import com.study.city.service.ITxPyqService;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 朋友圈文案
 *
 * @author zhangpba
 * @date 2022-05-13
 */
@Api(value = "古典名句", tags = "古典名句")
@RestController
@RequestMapping("/gdmj")
public class TxGdmjController {
    private static final Logger logger = LoggerFactory.getLogger(TxGdmjController.class);

    @Autowired
    private ITxPyqService pyqService;

    // 古典名句需要发送的人
    @Value("${spring.mail.send.gdmj.switch}")
    private String gdmjSwitch;

    /**
     * 获取朋友圈文案
     *
     * @return
     */
    @ApiOperation(value = "获取古典名句")
    @GetMapping(value = "/getGdmj")
    public ResponseMessage getGdmj() {
        Map<String, String> map = pyqService.getGdmj();
        return ResponseMessage.success(map);
    }

    /**
     * 发送古典名句邮件
     *
     * @return
     */
    @ApiOperation(value = "发送古典名句邮件")
    @GetMapping(value = "/send")
    public ResponseMessage send() {
        pyqService.sendGdmjEmail();
        return ResponseMessage.success("发送成功");
    }

    /**
     * 每天清晨九点发送朋友圈信息
     */
    @ApiOperation(value = "定时发送古典名句 cron = ${spring.mail.send.gdmj.syn-cron")
    @Scheduled(cron = "${spring.mail.send.gdmj.syn-cron}")
    public void synGdmj() {
        logger.info("定时发送古典名句 start...");
        // 开关关闭不发送邮件
        if ("1".equals(gdmjSwitch)) {
            send();
        } else {
            logger.info("定时发送古典名句 开关关闭 ...");
        }
        logger.info("定时发送古典名句 end...");
    }
}
