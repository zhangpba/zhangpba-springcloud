package com.data.chain.cardmanage.entity;

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
@TableName ("MONITOR_RECORD")
public class MonitorRecord  implements Serializable {
	private static final long serialVersionUID =  394510519983102242L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty (value ="主键")
	private Long id;
    @ApiModelProperty (value ="被督导节点明细ID")
	private Long monitoredNodeDtlId;
    @ApiModelProperty (value ="督导节点明细ID")
	private Long monitorNodeDtlId;
    @ApiModelProperty (value ="督导节点卡片ID")
	private Long monitorCardId;
    @ApiModelProperty (value ="督导人ID")
	private String monitorUserId;
    @ApiModelProperty (value ="督导人名称")
	private String monitorUserName;
    @ApiModelProperty (value ="督导时间")
	private Date monitorTime;
    @ApiModelProperty (value ="督导意见")
	private String monitorOpinions;
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
