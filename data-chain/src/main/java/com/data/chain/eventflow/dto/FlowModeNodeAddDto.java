package com.data.chain.eventflow.dto;

import com.data.chain.eventflow.entity.FlowModelNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/20
 */
@Data
@ApiModel
public class FlowModeNodeAddDto {
    @ApiModelProperty(value ="修改编辑时传")
    private Long id;
    @ApiModelProperty (value ="关联模板主表")
    private Long modelId;
    @ApiModelProperty (value ="接收人类型，对应知识库operator_type字段，可选多个，用逗号隔开")
    private String operatorTypes;
    @ApiModelProperty (value ="推荐群组ID")
    private String chatId;
    @ApiModelProperty (value ="通过方式（1-任一完成通过；2-全部完成通过）")
    private Integer passWay;
    @ApiModelProperty (value ="序号")
    private Long orderNumber;
    @ApiModelProperty (value ="节点类型（1-处置节点；2-督导节点）")
    private Integer nodeType;
    @ApiModelProperty (value ="督导该节点的节点ID，多个用逗号隔开")
    private String monitorNodes;
    @ApiModelProperty(value = "处置建议")
    private String handleSuggest ;
    @ApiModelProperty (value ="前一个节点ID")
    private Long preNodeId;
    @ApiModelProperty (value ="下一个节点ID")
    private Long nextNodeId;
    @ApiModelProperty (value ="可选操作code列表，多个用逗号隔开")
    private String operationList;

    public FlowModelNode convert() {
        FlowModelNode model = new FlowModelNode();
        BeanUtils.copyProperties(this, model);
        return model;
    }
}
