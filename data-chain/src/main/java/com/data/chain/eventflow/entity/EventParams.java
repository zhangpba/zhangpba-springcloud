package com.data.chain.eventflow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @Description  
 * @Author  peilizhu 
 */

@Data
@TableName ("EVENT_PARAMS")
public class EventParams  implements Serializable {
	private static final long serialVersionUID =  4353391300464518044L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty (value ="主键")
	private Long id;
    @ApiModelProperty (value ="事件ID")
	private Long eventId;
    @ApiModelProperty (value ="参数名")
	private String paramName;
    @ApiModelProperty (value ="参数值")
	private String paramValue;
    @ApiModelProperty (value ="是否删除（0-否；1-是）")
	private Long isDelete;

}
