package com.data.chain.eventflow.entity;

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
@TableName ("FLOW_MODEL")
public class FlowModel  implements Serializable {
	private static final long serialVersionUID =  6116966446674110605L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty (value ="自增主键")
	private Long id;
    @ApiModelProperty (value ="模板名称")
	private String modelName;
    @ApiModelProperty (value ="业务类型")
	private String businessType;
    @ApiModelProperty (value ="业务级别")
	private Integer businessLevel;
    @ApiModelProperty (value ="是否有效（0-有效；1-无效）")
	private Integer isValid;
    @ApiModelProperty (value ="是否删除（0-否；1-是）")
	private Integer isDelete;
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



}
