package com.example.lv.bean.web;

import java.io.Serializable;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 登录 入参
 * @date 2023/6/18 15:14:58
 */
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -8411105573251348045L;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
