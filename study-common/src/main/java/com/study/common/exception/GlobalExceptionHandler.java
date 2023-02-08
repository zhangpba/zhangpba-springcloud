package com.study.common.exception;

import com.study.common.web.ResponseEnum;
import com.study.common.web.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangpba
 * @desc 全局异常处理
 * @date 2023/01/18
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseMessage<Object> customhandle(CustomException e) {
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
