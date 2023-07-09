package com.example.lv.dao.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * 权限表
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 7248949644906963344L;
    /**
     * 权限id
     */
    private Integer perId;
    /**
     * 权限名称
     */
    private String perName;
    /**
     * 权限编码
     */
    private String perCode;
    /**
     * 权限描述
     */
    private String perDesc;
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
     * 角色id
     */
    private Integer roleId;

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getPerCode() {
        return perCode;
    }

    public void setPerCode(String perCode) {
        this.perCode = perCode;
    }

    public String getPerDesc() {
        return perDesc;
    }

    public void setPerDesc(String perDesc) {
        this.perDesc = perDesc;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "perId=" + perId +
                ", perName='" + perName + '\'' +
                ", perCode='" + perCode + '\'' +
                ", perDesc='" + perDesc + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", createBy='" + createBy + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
