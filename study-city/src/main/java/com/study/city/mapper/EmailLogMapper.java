package com.study.city.mapper;

import com.study.city.entity.email.EmailLog;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 邮件发送日志
 *
 * @author zhangpba
 * @date 2022-06-21
 */
@Mapper
public interface EmailLogMapper {
    // 插入配置信息
    int addEmailLog(EmailLog emailLog);

    // 根据条件查询所有符合条件的配置信息
    List<EmailLog> queryEmailLogs(EmailLog configInfo);
}
