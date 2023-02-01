package com.study.city.exception;

import com.study.city.base.ResponseEnum;
import com.study.common.web.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangpba
 * @desc 描述信息
 * @date 2023/01/18
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseMessage customExceptionHandler(CustomException e) {
        log.error("自定义CustomException异常处理：{}", e.getMessage(), e);
        return ResponseMessage.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage exceptionHandler(Exception e) {
        log.error("全局异常处理：{}", e.getMessage(), e);
        return ResponseMessage.error(ResponseEnum.FAIL.getCode(), ResponseEnum.FAIL.getMsg());
    }
}
