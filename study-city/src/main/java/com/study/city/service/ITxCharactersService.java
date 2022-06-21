package com.study.city.service;

import com.study.city.entity.tianxing.Characters;

import javax.mail.MessagingException;
import java.util.List;

public interface ITxCharactersService {
    // 从天行数据获取生日性格数据
    List<Characters> getCharacters(String month, String day);

    // 批量插入
    void batchAddcharacters(List<Characters> charactersList);

    // 插入
    int addCharacters(Characters characters);

    // 查询366天所有的性格
    List<Characters> getAllCharacters();

    // 根据生日查询
    Characters getCharacters(String brithday);

    // TODO 暂时不用了：Thymeleaf模板邮件
    void sendThymeleafMail(String birthday, String toUsers) throws MessagingException;

    // 发送普通邮件
    void sendEmail(String birthday, String toUsers) throws MessagingException;
}


