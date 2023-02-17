package com.data.chain.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  peilizhu 
 */

@Data
@ApiModel
public class AdminUserVO implements Serializable {
	private static final long serialVersionUID =  3175203556014043451L;

    @TableId(type = IdType.AUTO)
	private String id;
    @ApiModelProperty (value ="用户名")
	private String username;
    @ApiModelProperty (value ="姓名")
	private String name;
    @ApiModelProperty (value ="手机号")
	private String phone;
    @ApiModelProperty("token")
	private String token;

}
