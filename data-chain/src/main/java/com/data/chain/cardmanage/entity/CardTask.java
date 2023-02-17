package com.data.chain.cardmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 * @Description  
 * @Author  peilizhu 
 */

@Data
@TableName ("CARD_TASK")
public class CardTask  implements Serializable {
	private static final long serialVersionUID =  2615912978639475862L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty (value ="主键ID")
	private Long id;
    @ApiModelProperty (value ="节点明细表ID")
	private Long nodeDetailId;
    @ApiModelProperty (value ="处置状态（0-未接受；1-进行中；2-已结束）")
	private Integer operateStatus;
    @ApiModelProperty (value ="所选操作")
	private String myOption;
	@ApiModelProperty("查看时间")
	private Date viewTime ;
    @ApiModelProperty (value ="接受时间")
	private Date acceptTime;
    @ApiModelProperty (value ="接收人ID")
	private String userId;
    @ApiModelProperty (value ="接收人名称")
	private String userName;
    @ApiModelProperty (value ="群组ID")
	private String chatId;
    @ApiModelProperty (value ="处置意见")
	private String opinions;
    @ApiModelProperty (value ="处置时间")
	private Date handleTime;
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
