package com.study.city.user.entity;

import java.io.Serializable;

/**
 * 用户和角色关联表(SysUserRole)实体类
 *
 * @author zhangpba
 * @since 2023-01-28 21:24:32
 */
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -83925740555146942L;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 角色ID
     */
    private Long roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}

