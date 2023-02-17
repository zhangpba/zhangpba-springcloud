package com.data.chain.cardmanage.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.data.chain.cardmanage.entity.MonitorRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/22
 */
@Data
@ApiModel
public class QueryCardTaskVO {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Long id;
    @ApiModelProperty(value = "节点明细表ID")
    private Long nodeDetailId;
    @ApiModelProperty(value = "处置状态（0-未接受；1-进行中；2-已结束）")
    private Integer operateStatus;
    @ApiModelProperty(value = "可选操作（ACCEPT-接受，HANDLE-完成，REASSIGN-转交）")
    private String operationList;
    @ApiModelProperty(value = "所选操作")
    private String myOption;
    @ApiModelProperty("查看时间")
    private Date viewTime ;
    @ApiModelProperty(value = "接受时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date acceptTime;
    @ApiModelProperty(value = "接收人ID")
    private String userId;
    @ApiModelProperty(value = "接收人名称")
    private String userName;
    @ApiModelProperty(value = "群组ID")
    private String chatId;
    @ApiModelProperty(value = "处置意见")
    private String opinions;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "处置时间")
    private Date handleTime;
    @ApiModelProperty(value = "是否有效（0-有效；1-无效）")
    private Integer isValid;
    @ApiModelProperty(value = "是否删除（0-否；1-是）")
    private Integer isDelete;
    private String createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String updateUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @ApiModelProperty(value = "处置人姓名")
    private String operatorName;

    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("业务类型")
    private String businessType;
    @ApiModelProperty("消息描述")
    private String eventDesc;
    @ApiModelProperty("处置链接")
    private String handleUrl;
    @ApiModelProperty("推荐处置")
    private String handleSuggest;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("发生时间")
    private Date occurTime;
    @ApiModelProperty(value = "事件ID")
    private Long eventId;
    @ApiModelProperty(value = "节点类型（1-处置节点；2-督导节点）")
    private Integer nodeType;
    @ApiModelProperty("督导记录")
    private List<MonitorRecord> monitorRecords;
}
