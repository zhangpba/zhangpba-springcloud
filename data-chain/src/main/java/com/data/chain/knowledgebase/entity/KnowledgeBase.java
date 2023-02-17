package com.data.chain.knowledgebase.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 * @Description  
 * @Author  peilizhu 
 */

@Data
@TableName ("KNOWLEDGE_BASE")
public class KnowledgeBase  implements Serializable {
	private static final long serialVersionUID =  7123634150042904953L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty (value ="主键ID")
	private Long id;
    @ApiModelProperty (value ="接收人类型")
	private String operatorType;
    @ApiModelProperty (value ="组织机构ID")
	private String orgId;
    @ApiModelProperty (value ="组织机构名称")
	private String orgName;
    @ApiModelProperty (value ="电话")
	private String phone;
    @ApiModelProperty (value ="姓名")
	private String name;
    @ApiModelProperty (value ="创建人")
    private String createUser;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty (value ="创建时间")
    private Date createTime;
    @ApiModelProperty (value ="最后修改人")
    private String updateUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty (value ="最后修改时间")
    private Date updateTime;
    @ApiModelProperty (value ="是否删除")
	private Long isDelete;

}
