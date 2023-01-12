package com.study.city.user.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 新增用户-实体类
 *
 * @author makejava
 * @since 2023-01-12 12:28:55
 */
@Setter
@Getter
public class SysUserCreateRequest implements Serializable {
    private static final long serialVersionUID = 843219744776281933L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

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
}

