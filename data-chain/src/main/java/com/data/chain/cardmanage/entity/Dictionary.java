package com.data.chain.cardmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @Description  
 * @Author  peilizhu 
 */

@Data
@TableName ("dictionary")
public class Dictionary  implements Serializable {
	private static final long serialVersionUID =  7655618439834316725L;

    @TableId(type = IdType.AUTO)
	private Long id;
    @ApiModelProperty (value ="key")
	private String keyword;
    @ApiModelProperty (value ="字典名称")
	private String name;
    @ApiModelProperty (value ="类型")
	private String type;
    @ApiModelProperty (value ="类型名称")
	private String typeName;
    @ApiModelProperty (value ="排序")
	private Integer sort;
    @ApiModelProperty (value ="默认0正常，-1禁用")
	private Integer status;

}
