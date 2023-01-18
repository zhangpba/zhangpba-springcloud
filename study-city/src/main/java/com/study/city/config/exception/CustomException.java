package com.study.city.config.exception;

import com.study.city.base.ResponseEnum;
import lombok.Getter;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/3/8
 */
@Getter
public class CustomException extends RuntimeException {
    private Integer code;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.code = responseEnum.getCode();
    }
}
