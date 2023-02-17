package com.data.chain.eventflow.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("事件处置流程节点明细")
public class EventNodeDetailVO implements Serializable {
    @ApiModelProperty(value ="主键")
    private Long id;
    @ApiModelProperty (value ="事件ID")
    private Long eventId;
    @ApiModelProperty (value ="流程模板ID（转交插入的卡片没有模板）")
    private Long flowModelId;
    @ApiModelProperty (value ="处置人类别，对应知识库operator_type字段，多个用逗号隔开")
    private String operatorType;
    @ApiModelProperty (value ="推荐群ID")
    private String chatId;
    @ApiModelProperty (value ="通过方式（1-任一完成通过；2-全部完成通过）")
    private Long passWay;
    @ApiModelProperty (value ="节点类型（1-处置节点；2-督导节点）")
    private Long nodeType;
    @ApiModelProperty (value ="上一个节点ID")
    private Long preNodeDtlId;
    @ApiModelProperty (value ="下一个节点ID")
    private Long nextNodeDtlId;
    @ApiModelProperty (value ="可选操作列表")
    private String operationList;
    @ApiModelProperty (value ="处置时间")
    private Date handleTime;
    @ApiModelProperty (value ="状态（0-待处置；1-已处置）")
    private Integer status;
    @ApiModelProperty (value ="是否有效（0-有效；1-无效）")
    private Long isValid;
    @ApiModelProperty (value ="是否删除（0-否；1-是）")
    private Long isDelete;
    @ApiModelProperty (value ="创建人")
    private String createUser;
    @ApiModelProperty (value ="创建时间")
    private Date createTime;
    @ApiModelProperty (value ="最后修改人")
    private String updateUser;
    @ApiModelProperty (value ="最后修改时间")
    private Date updateTime;
}
