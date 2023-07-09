package com.example.lv.service.impl;

import com.example.lv.bean.web.user.UserRequest;
import com.example.lv.dao.entity.base.Role;
import com.example.lv.dao.entity.base.User;
import com.example.lv.dao.mapper.base.RoleDao;
import com.example.lv.dao.mapper.base.UserDao;
import com.example.lv.service.IUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 用户服务
 * @date 2023/6/17 14:40:37
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    /**
     * 查询用户列表
     * @param request
     * @return
     */
    @Override
    public List<User> getUserList(UserRequest request) {
        List<User> userList = userDao.getUserList(request);
        if(CollectionUtils.isNotEmpty(userList)){
            //查询角色
            for (User user:userList) {
                Integer userId = user.getUserId();
                List<Role> roles = roleDao.queryRole(userId);
                user.setRoles(roles);
            }
        }
        return userList;
    }

    /**
     * 查询用户列表总记录数
     * @param request
     * @return
     */
    @Override
    public int getUserCount(UserRequest request) {
        return userDao.getUserCount(request);
    }
}
