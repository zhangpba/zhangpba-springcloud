package com.study.city.user.enums;

import lombok.Getter;

/**
 * @author zhangpba
 * @desc 用户错误码
 * @date 2022/01/18
 */
@Getter
public enum UserCodeEnum {

    // 返回值枚举
    ERROR_405(405, "token失效"),
    ERROR_406(406, "用户已停用"),
    ERROR_407(407, "无token，请传入token"),
    ERROR_408(408, "token验证失败"),
    ERROR_501(501, "数据异常！");

    private Integer code;
    private String msg;

    UserCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
