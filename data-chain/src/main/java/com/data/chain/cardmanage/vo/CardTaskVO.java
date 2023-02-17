package com.data.chain.cardmanage.vo;

import com.data.chain.eventflow.entity.EventParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CardTaskVO implements Serializable {
    private static final long serialVersionUID = -213458502182295282L;
    @ApiModelProperty(value ="主键ID")
    private Long id;
    @ApiModelProperty (value ="节点明细表ID")
    private Long nodeDetailId;
    @ApiModelProperty (value ="处置状态（0-未接受；1-进行中；2-已结束）")
    private Integer operateStatus;
    @ApiModelProperty (value ="所选操作")
    private String myOption;
    @ApiModelProperty("查看时间")
    private Date viewTime ;
    @ApiModelProperty (value ="接受时间")
    private Date acceptTime;
    @ApiModelProperty (value ="接收人ID")
    private String userId;
    @ApiModelProperty (value ="接收人名称")
    private String userName;
    @ApiModelProperty (value ="群组ID")
    private String chatId;
    @ApiModelProperty (value ="处置意见")
    private String opinions;
    @ApiModelProperty (value ="处置时间")
    private Date handleTime;
    @ApiModelProperty (value ="是否有效（0-有效；1-无效）")
    private Integer isValid;
    @ApiModelProperty (value ="是否删除（0-否；1-是）")
    private Integer isDelete;

    @ApiModelProperty(value = "事件ID")
    private Long eventId ;
    @ApiModelProperty(value = "事件描述")
    private String eventDesc ;
    @ApiModelProperty(value = "处置建议")
    private String handleSuggest;
    @ApiModelProperty(value = "事件参数")
    private List<EventParams> eventParams ;
    @ApiModelProperty(value = "上一个节点ID")
    private Long preNodeId ;
    @ApiModelProperty(value = "下一个节点ID")
    private Long nextNodeId ;
    @ApiModelProperty (value ="可选操作code列表，多个用逗号隔开")
    private String operationList;

    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

}
