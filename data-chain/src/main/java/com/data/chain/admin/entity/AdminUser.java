package com.data.chain.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 * @Description  
 * @Author  peilizhu 
 */

@Data
@TableName ("admin_user")
public class AdminUser  implements Serializable {
	private static final long serialVersionUID =  3175203556014043451L;

    @TableId(type = IdType.AUTO)
	private String id;
    @ApiModelProperty (value ="用户名")
	private String username;
    @ApiModelProperty (value ="加密后的密码")
	private String password;
    @ApiModelProperty (value ="姓名")
	private String name;
    @ApiModelProperty (value ="手机号")
	private String phone;
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
