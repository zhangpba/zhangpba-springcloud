package com.study.city.service;

import com.study.city.entity.email.EmailLog;

import java.util.List;

public interface IEmailLogService {

    // 增加日志
    void addEmailLog(EmailLog emailLog);

    // 获取配置信息
    List<EmailLog> getEmailLogs(EmailLog emailLog);
}