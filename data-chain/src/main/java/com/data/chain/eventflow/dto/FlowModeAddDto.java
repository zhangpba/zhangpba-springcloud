package com.data.chain.eventflow.dto;

import com.data.chain.eventflow.entity.FlowModel;
import com.data.chain.eventflow.entity.FlowModelNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/19
 */
@ApiModel
@Data
public class FlowModeAddDto {
    @ApiModelProperty (value ="修改时传")
    private Long id;
    @ApiModelProperty(value ="模板名称")
    private String modelName;
    @ApiModelProperty (value ="业务类型")
    private String businessType;
    @ApiModelProperty (value ="是否有效（0-有效；1-无效）")
    private Integer isValid;
//    @ApiModelProperty("处置节点")
//    private List<FlowModelNode> dealNodes;
//    @ApiModelProperty("督导节点")
//    private List<FlowModelNode> supervisionNodes;

    public FlowModel convert() {
        FlowModel model = new FlowModel();
        BeanUtils.copyProperties(this, model);
        return model;
    }
}
