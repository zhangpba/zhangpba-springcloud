package com.data.chain.cardmanage.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.data.chain.cardmanage.entity.MonitorRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/24
 */
@ApiModel
@Data
public class MonitorRecordDto {
    @ApiModelProperty (value ="主键")
    private Long id;
    @ApiModelProperty (value ="被督导节点明细ID")
    private Long monitoredNodeDtlId;
    @ApiModelProperty(value ="督导节点明细ID")
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

    public MonitorRecord convert() {
        MonitorRecord monitorRecord = new MonitorRecord();
        BeanUtils.copyProperties(this, monitorRecord);
        return monitorRecord;
    }
}
