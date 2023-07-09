package com.example.lv.service;

import com.example.lv.bean.base.Result;
import com.example.lv.bean.web.LoginRequest;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description LoginService
 * @date 2023/6/18 15:06:40
 */
public interface ILoginService {

    /**
     * 登录
     * @param request
     * @return
     */
    Result login(LoginRequest request);

    /**
     * 退出登录
     * @return
     */
    Result logOut();
}
