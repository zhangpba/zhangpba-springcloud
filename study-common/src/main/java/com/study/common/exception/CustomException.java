package com.study.common.exception;

import com.study.common.web.ResponseEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author zhangpba
 * @desc 描述信息
 * @date 2022/3/8
 */
@Getter
@Setter
@ControllerAdvice
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
