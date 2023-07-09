package com.example.lv.dao.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lmh
 */
public class SysLog implements Serializable {
    private static final long serialVersionUID = -7813355676838685701L;

    private Long id;
    /**
     * 操作人
     */
    private String operationUser;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 方法执行时间
     */
    private String time;
    /**
     * 方法入参
     */
    private String parameter;
    /**
     * 操作方法
     */
    private String method;
    /**
     * 方法描述
     */
    private String remark;
    /**
     * 系统类型
     */
    private String sysType;
    /**
     * 操作类型
     */
    private String opType;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
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

    public SysLog(String operationUser, String path, String time, String parameter, String method, String remark, String sysType, String opType, Date createTime, Date updateTime) {
        super();
        this.operationUser = operationUser;
        this.path = path;
        this.time = time;
        this.parameter = parameter;
        this.method = method;
        this.remark = remark;
        this.sysType = sysType;
        this.opType = opType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", operationUser='" + operationUser + '\'' +
                ", path='" + path + '\'' +
                ", time='" + time + '\'' +
                ", parameter='" + parameter + '\'' +
                ", method='" + method + '\'' +
                ", remark='" + remark + '\'' +
                ", sysType='" + sysType + '\'' +
                ", opType='" + opType + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}