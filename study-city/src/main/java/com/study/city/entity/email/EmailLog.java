package com.study.city.entity.email;

import com.study.city.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author zhangpba
 * @description 邮件发送日志
 * @date 2022/6/21
 */
@Getter
@Setter
public class EmailLog extends BaseEntity {

    // 发送人
    private Integer id;

    // 发送人
    private String sendUsers;

    // 接收人
    private String receive;

    // 隐秘接收人
    private String receiveBcc;

    // 邮件主题
    private String title;

    // 邮件内容
    private String context;

    // 发送次数
    private Integer count;

    // 发送时间
    private Date sendTime;
}
