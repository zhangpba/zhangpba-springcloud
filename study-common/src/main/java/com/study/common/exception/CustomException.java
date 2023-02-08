package com.study.common.exception;

import com.study.common.web.ResponseEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpba
 * @desc 全局异常处理
 * @date 2022/3/8
 */
@Getter
@Setter
public class CustomException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public CustomException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.message = responseEnum.getMsg();
        this.code = responseEnum.getCode();
    }
}
