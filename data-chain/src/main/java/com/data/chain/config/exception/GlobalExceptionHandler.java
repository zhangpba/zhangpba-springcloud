package com.data.chain.config.exception;

import com.data.chain.base.ResponseVO;
import com.data.chain.base.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/3/8
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseVO customExceptionHandler(CustomException e) {
        log.error("自定义CustomException异常处理：{}", e.getMessage(),e);
        return ResponseVO.fail(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseVO exceptionHandler(Exception e) {
        log.error("全局异常处理：{}", e.getMessage(),e);
        return ResponseVO.fail(ResponseEnum.FAIL.getCode(), ResponseEnum.FAIL.getMsg());
    }
}
