package com.data.chain.eventflow.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("流程模板节点")
public class FlowModelNodeVO implements Serializable {
    private static final long serialVersionUID = -3480425602738025834L;
    @ApiModelProperty(value ="主键ID")
    private Long id;
    @ApiModelProperty (value ="关联模板主表")
    private Long modelId;
    @ApiModelProperty (value ="接收人类型，对应知识库operator_type字段，可选多个，用逗号隔开")
    private String operatorTypes;
    @ApiModelProperty (value ="推荐群组ID")
    private String chatId;
    @ApiModelProperty (value ="通过方式（1-任一完成通过；2-全部完成通过）")
    private Integer passWay;
    @ApiModelProperty (value ="节点类型（1-处置节点；2-督导节点）")
    private Integer nodeType;
    @ApiModelProperty (value ="督导该节点的节点ID，多个用逗号隔开")
    private String monitorNodes;
    @ApiModelProperty (value ="前一个节点ID")
    private Long preNodeId;
    @ApiModelProperty (value ="下一个节点ID")
    private Long nextNodeId;
    @ApiModelProperty (value ="可选操作code列表，多个用逗号隔开")
    private String operationList;
    @ApiModelProperty(value = "处置建议")
    private String handleSuggest ;
    @ApiModelProperty (value ="是否删除（0-否；1-是）")
    private Integer isDelete;
    @ApiModelProperty (value ="创建人")
    private String createUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty (value ="创建时间")
    private Date createTime;
    @ApiModelProperty (value ="最后修改人")
    private String updateUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty (value ="最后修改时间")
    private Date updateTime;
}
