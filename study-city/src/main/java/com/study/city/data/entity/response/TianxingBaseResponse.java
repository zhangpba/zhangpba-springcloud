package com.study.city.data.entity.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhangpba
 * @description 天行数据基础类
 * @date 2023/1/25
 */
@Setter
@Getter
public class TianxingBaseResponse<T> {
    private String msg;
    private String code;
    private List<T> newslist;
}
