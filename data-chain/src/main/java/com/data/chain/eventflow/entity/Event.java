package com.data.chain.eventflow.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 * @Description  
 * @Author  peilizhu 
 */

@Data
@TableName ("EVENT")
public class Event  implements Serializable {
	private static final long serialVersionUID =  4213916960535889215L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty (value ="主键ID")
	private Long id;
    @ApiModelProperty(value = "预警系统唯一标识")
    private String uniqueCode ;
    @ApiModelProperty (value ="业务类型")
	private String businessType;
    @ApiModelProperty (value ="业务级别")
	private String businessLevel;
    @ApiModelProperty (value ="接收方ID")
	private String receiverCode;
    @ApiModelProperty (value ="接收方名称")
	private String receiverName;
    @ApiModelProperty (value ="接收方类型（0-组织；1-个人）")
	private Long receiverType;
    @ApiModelProperty (value ="省")
	private String province;
    @ApiModelProperty (value ="省名称")
	private String provinceNm;
    @ApiModelProperty("执法处编码")
    private String enforcement ;
    @ApiModelProperty("执法处名称")
    private String enforcementName ;
    @ApiModelProperty (value ="市")
	private String city;
    @ApiModelProperty (value ="市名称")
	private String cityNm;
    @ApiModelProperty (value ="区县")
	private String county;
    @ApiModelProperty (value ="区县名称")
	private String countyNm;

    @ApiModelProperty (value ="部门")
	private String department;
    @ApiModelProperty (value ="事件描述")
	private String eventDesc;
    @ApiModelProperty(value = "发生时间")
    private Date occurTime ;
    @ApiModelProperty (value ="事件状态（0-待处置；1-处置中；2-已处置））")
	private Integer status;
    @ApiModelProperty (value ="标识")
	private String eventTips;
    @ApiModelProperty (value ="权限")
	private String access;
    @ApiModelProperty (value ="是否删除（0-否；1-是）")
	private Long isDelete;
    @ApiModelProperty (value ="创建人")
    private String createUser;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty (value ="创建时间")
    private Date createTime;
    @ApiModelProperty (value ="最后修改人")
    private String updateUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty (value ="最后修改时间")
    private Date updateTime;

}