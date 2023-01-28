package com.study.city.user.entity.request;

import com.study.city.user.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 新增用户-实体类
 *
 * @author zhangpba
 * @since 2023-01-12 12:28:55
 */
@Setter
@Getter
public class SysUserCreateRequest implements Serializable {
    private static final long serialVersionUID = 843219744776281933L;

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

    @ApiModelProperty(value = "角色列表")
    private List<SysRoleListRequest> roleList;
}

