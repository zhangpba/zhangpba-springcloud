package com.study.city.user.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表(SysRole)实体类
 *
 * @author zhangpba
 * @since 2023-01-12 19:16:57
 */
@ApiModel
@Setter
@Getter
public class SysRoleListRequest implements Serializable {
    private static final long serialVersionUID = 922597341120293189L;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 角色权限字符串
     */
    @ApiModelProperty(value = "角色权限字符串")
    private String roleKey;
    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序")
    private Integer roleSort;
    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @ApiModelProperty(value = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private String dataScope;
    /**
     * 菜单树选择项是否关联显示
     */
    @ApiModelProperty(value = "菜单树选择项是否关联显示")
    private Integer menuCheckStrictly;
    /**
     * 部门树选择项是否关联显示
     */
    @ApiModelProperty(value = "部门树选择项是否关联显示")
    private Integer deptCheckStrictly;
    /**
     * 角色状态（0正常 1停用）
     */
    @ApiModelProperty(value = "角色状态（0正常 1停用）")
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "页码")
    @Min(message = "页码不能小于0", value = 0)
    private int pageNum;

    @ApiModelProperty(value = "页面大小")
    @Min(message = "页面不能小于0", value = 0)
    private int pageSize;

    @ApiModelProperty(value = "总数")
    private int total;
}

