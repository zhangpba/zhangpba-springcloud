package com.data.chain.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/3/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = -8713837118340960775L;

    private Integer code;
    private String message;
    private T data;

    public ResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseVO success() {
        return new ResponseVO(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg());
    }

    public static ResponseVO success(Object data) {
        return new ResponseVO(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(), data);
    }

    public static ResponseVO success(Integer code, String message) {
        return new ResponseVO(code, message);
    }

    public static ResponseVO success(Integer code, String message, Object data) {
        return new ResponseVO(code, message, data);
    }

    public static ResponseVO fail() {
        return new ResponseVO(ResponseEnum.FAIL.getCode(), ResponseEnum.FAIL.getMsg());
    }

    public static ResponseVO fail(Integer code, String message) {
        return new ResponseVO(code, message);
    }
}
