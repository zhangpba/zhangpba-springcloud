package com.data.chain.admin.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 功能 :
 * 登陆之后的返回信息
 *
 * @author : peilizhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiLoginInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value ="用户ID")
    private String userId;
    @ApiModelProperty(value ="用户名")
    private String username;
    @ApiModelProperty (value ="姓名")
    private String name;
    @ApiModelProperty (value ="手机号")
    private String phone;
}
