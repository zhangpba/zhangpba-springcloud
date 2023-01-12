package com.study.city.user.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 列表查询实体类
 *
 * @author makejava
 * @since 2023-01-12 12:28:55
 */
@Setter
@Getter
public class SysUserListRequest implements Serializable {
    private static final long serialVersionUID = 843219744776281933L;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String username;

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

    @ApiModelProperty(value = "页码")
    @Min(message = "页码不能小于0", value = 0)
    private int pageNum;

    @ApiModelProperty(value = "页面大小")
    @Min(message = "页面不能小于0", value = 0)
    private int pageSize;

    @ApiModelProperty(value = "总数")
    private int total;

}

