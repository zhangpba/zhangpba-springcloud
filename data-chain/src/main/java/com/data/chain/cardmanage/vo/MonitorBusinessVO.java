package com.data.chain.cardmanage.vo;

import com.data.chain.cardmanage.entity.MonitorRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/24
 */
@Data
@ApiModel
public class MonitorBusinessVO {

    @ApiModelProperty("督导节点卡片id")
    private Long cardTaskId;
    @ApiModelProperty("处置节点id")
    private Long nodeId;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("事件id")
    private Long eventId;
    @ApiModelProperty("事件描述")
    private String eventDesc;
    @ApiModelProperty("督导人")
    private String monitors;
    @ApiModelProperty("处置人")
    private String handlers;
    @ApiModelProperty (value ="处置时间")
    private Date handleTime;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @ApiModelProperty("督导记录")
    private List<MonitorRecord> monitorRecords;


}
