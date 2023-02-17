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
@TableName ("EVENT_NODE_DETAIL")
public class EventNodeDetail  implements Serializable {
	private static final long serialVersionUID =  8942642955939440537L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty (value ="主键")
	private Long id;
    @ApiModelProperty (value ="事件ID")
	private Long eventId;
    @ApiModelProperty (value ="流程模板ID（转交插入的卡片没有模板）")
	private Long flowModelId;
    @ApiModelProperty (value ="处置人手机号，来自于知识库")
	private String operators;
    @ApiModelProperty (value ="推荐群ID")
	private String chatId;
    @ApiModelProperty (value ="通过方式（1-任一完成通过；2-全部完成通过）")
	private Integer passWay;
    @ApiModelProperty (value ="节点类型（1-处置节点；2-督导节点）")
	private Integer nodeType;
    @ApiModelProperty (value ="处置建议")
	private String handleSuggest;
    @ApiModelProperty (value ="上一个节点ID")
	private Long preNodeDtlId;
    @ApiModelProperty (value ="下一个节点ID")
	private Long nextNodeDtlId;
    @ApiModelProperty (value ="可选操作列表")
	private String operationList;
    @ApiModelProperty (value ="处置时间")
    private Date handleTime;
    @ApiModelProperty (value ="状态（0-待处置；1-处置中；2-已处置）")
	private Integer status;
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
