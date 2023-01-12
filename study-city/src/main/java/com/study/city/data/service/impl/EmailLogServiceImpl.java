package com.study.city.data.service.impl;

import com.study.city.data.entity.email.EmailLog;
import com.study.city.data.mapper.EmailLogMapper;
import com.study.city.data.service.IEmailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 邮件发送日志记录
 *
 * @author zhangpba
 * @date 2022-06-21
 */
@Service
public class EmailLogServiceImpl implements IEmailLogService {
    private static final Logger logger = LoggerFactory.getLogger(EmailLogServiceImpl.class);

    @Autowired
    private EmailLogMapper emailLogMapper;

    @Override
    public void addEmailLog(EmailLog emailLog) {
        try {
            emailLogMapper.addEmailLog(emailLog);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<EmailLog> getEmailLogs(EmailLog emailLog) {
        return emailLogMapper.queryEmailLogs(emailLog);
    }
}