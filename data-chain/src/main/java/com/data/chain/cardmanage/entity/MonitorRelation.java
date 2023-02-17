package com.data.chain.cardmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  peilizhu 
 */

@Data
@TableName ("MONITOR_RELATION")
@NoArgsConstructor
public class MonitorRelation  implements Serializable {
	private static final long serialVersionUID =  7381444659610889548L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty (value ="主键")
	private Long id;
    @ApiModelProperty (value ="被督导节点明细ID")
	private Long monitoredNodeId;
    @ApiModelProperty (value ="督导节点明细ID")
	private Long monitorNodeId;

    public MonitorRelation(Long monitoredNodeId, Long monitorNodeId) {
        this.monitoredNodeId = monitoredNodeId;
        this.monitorNodeId = monitorNodeId;
    }
}
