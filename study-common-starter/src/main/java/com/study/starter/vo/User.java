package com.study.starter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户模型
 */
@Setter
@Getter
@ApiModel(value = "用户类", description = "用户基本信息")
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "用户姓名", required = true)
    private String userName;

    @ApiModelProperty(value = "用户年龄", example = "19")
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
