package com.example.lv.bean.web.user;

import java.io.Serializable;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 用户请求入参
 * @date 2023/6/17 14:21:52
 */
public class UserRequest implements Serializable {

    private static final long serialVersionUID = -1877494828877603408L;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 开始记录数
     */
    private Integer start;
    /**
     * 每页条数
     */
    private Integer limit;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", phone='" + phone + '\'' +
                ", start=" + start +
                ", limit=" + limit +
                '}';
    }
}
