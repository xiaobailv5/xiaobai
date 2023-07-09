package com.example.lv.service;

import com.example.lv.bean.web.user.UserRequest;
import com.example.lv.dao.entity.base.User;

import java.util.List;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description UserService
 * @date 2023/6/17 14:34:48
 */
public interface IUserService {

    /**
     * 查询用户列表
     * @param request
     * @return
     */
    List<User> getUserList(UserRequest request);

    /**
     * 查询用户列表总条数
     * @param request
     * @return
     */
    int getUserCount(UserRequest request);
}
