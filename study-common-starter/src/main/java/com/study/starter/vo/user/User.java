package com.study.starter.vo.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author zhangpba
 * @description 用户模型
 * @date 2022/9/30
 */
@Setter
@Getter
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Integer age;
    private String birthday;
    private Integer sex;

    private String createBy;
    private String updateBy;
    private Date createDate;
    private Date updateDate;


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
