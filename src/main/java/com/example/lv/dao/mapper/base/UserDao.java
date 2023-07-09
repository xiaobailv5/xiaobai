package com.example.lv.dao.mapper.base;

import com.example.lv.bean.web.user.UserRequest;
import com.example.lv.dao.entity.base.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User findUserByUserName(String username);

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
