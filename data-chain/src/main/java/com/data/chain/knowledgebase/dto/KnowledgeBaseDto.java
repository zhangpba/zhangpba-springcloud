package com.data.chain.knowledgebase.dto;

import com.data.chain.knowledgebase.entity.KnowledgeBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/15
 */
@Data
@ApiModel
public class KnowledgeBaseDto {
    @ApiModelProperty (value ="主键ID 新增时不传，修改时传")
    private Long id;
    @ApiModelProperty(value ="接收人类型")
    private String operatorType;
    @ApiModelProperty (value ="组织机构ID")
    private String orgId;
    @ApiModelProperty (value ="组织机构名称")
    private String orgName;
    @ApiModelProperty (value ="电话")
    private String phone;
    @ApiModelProperty (value ="姓名")
    private String name;

    public KnowledgeBase convert() {
        KnowledgeBase model = new KnowledgeBase();
        BeanUtils.copyProperties(this, model);
        return model;
    }
}
