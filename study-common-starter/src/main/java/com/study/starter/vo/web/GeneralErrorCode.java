package com.study.starter.vo.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralErrorCode {
    private Integer code;
    private Integer httpCode;
    private String message;
    private String service;
    private String strCode;
}
