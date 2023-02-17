package com.data.chain.eventflow.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("流程模板节点")
public class FlowModelVO implements Serializable {
    private static final long serialVersionUID = 2549599271558482290L;
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
    @ApiModelProperty (value ="创建时间")
    private Date createTime;
    @ApiModelProperty (value ="最后修改人")
    private String updateUser;
    @ApiModelProperty (value ="最后修改时间")
    private Date updateTime;

    @ApiModelProperty (value ="流程所有节点列表")
    private List<FlowModelNodeVO> flowModelNodeVOList ;
    @ApiModelProperty (value ="督导节点列表")
    private List<FlowModelNodeVO> supervisionNodes;
    @ApiModelProperty (value ="处置节点列表")
    private List<List<FlowModelNodeVO>> dealNodes;
}
