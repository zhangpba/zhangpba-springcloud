package com.study.city.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BaseEntity {
    private String createBy;
    private String updateBy;
    private Date createDate;
    private Date updateDate;
}
