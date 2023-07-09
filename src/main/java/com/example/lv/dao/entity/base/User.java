package com.example.lv.dao.entity.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * 用户
 */
public class User implements Serializable {

    private static final long serialVersionUID = 8321051006102482348L;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户账号
     */
    private String userName;
    /**
     * 用户密码
     */
    private String passWord;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 部门id
     */
    private String deptId;
    /**
     * 锁定标识
     */
    private String lockFlag;
    /**
     * 账号状态
     */
    private String delFlag;
    /**
     * 添加用户人员账号
     */
    private String createBy;
    /**
     *编辑用户人员账号
     */
    private String updateBy;
    /**
     * 添加账号时间
     */
    private LocalDateTime createTime;
    /**
     * 更新账号时间
     */
    private LocalDateTime updateTime;
    /**
     * 用户角色
     */
    private List<Role> roles;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", deptId='" + deptId + '\'' +
                ", lockFlag='" + lockFlag + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", roles=" + roles +
                '}';
    }
}
