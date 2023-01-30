package com.study.city.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * 字典类型表(DictType)实体类
 *
 * @author zhangpba
 * @since 2023-01-28 23:00:05
 */
@Setter
@Getter
@ApiModel
public class DictType implements Serializable {
    private static final long serialVersionUID = 161290431315156349L;
    /**
     * 字典主键
     */
    @ApiModelProperty(value = "字典主键")
    private Long dictId;
    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;
    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    private String status;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    private String updateBy;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}

