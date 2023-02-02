package com.study.city.user.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * Sys_User表 实体类
 *
 * @author zhangpba
 * @since 2023-01-12 12:28:55
 */
@Getter
@Setter
public class SysUser implements Serializable {
    private static final long serialVersionUID = 843219744776281933L;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "状态 0-正常 1-停用")
    private Integer statue;
    
    private String createBy;
    
    private String updateBy;
    
    private Date createDate;
    
    private Date updateDate;
}

