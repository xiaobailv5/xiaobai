package com.example.lv.dao.entity.base;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Administrator
 * 角色表
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 2357263152015124872L;

    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 角色描述
     */
    private String roleDesc;
    /**
     * 删除标识（0-正常,1-删除）
     */
    private String delFlag;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改人
     */
    private String createBy;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 权限列表
     */
    private Set<Permission> permissionList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Set<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", createBy='" + createBy + '\'' +
                ", userId=" + userId +
                ", permissionList=" + permissionList +
                '}';
    }
}
