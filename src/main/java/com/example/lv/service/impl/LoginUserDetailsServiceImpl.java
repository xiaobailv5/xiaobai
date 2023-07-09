package com.example.lv.service.impl;

import com.example.lv.dao.entity.base.LoginUser;
import com.example.lv.dao.entity.base.Permission;
import com.example.lv.dao.entity.base.Role;
import com.example.lv.dao.entity.base.User;
import com.example.lv.dao.mapper.base.PermissionDao;
import com.example.lv.dao.mapper.base.RoleDao;
import com.example.lv.dao.mapper.base.UserDao;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */

@Service
public class LoginUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findUserByUserName(username);
        if (ObjectUtils.isEmpty(user)){
            throw new RuntimeException("not found user");
        }
        //定义权限列表.
        List<GrantedAuthority> authorities = new ArrayList<>();

        Integer userId = user.getUserId();
        // 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
        if(ObjectUtils.isNotEmpty(userId)){
            List<Role> roles = roleDao.queryRole(userId);
            if(CollectionUtils.isNotEmpty(roles)){
                for (Role role : roles) {
                    authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
                    //查询权限列表
                    Integer roleId = role.getRoleId();
                    List<Permission> permissions = permissionDao.queryPermission(roleId);
                    if (CollectionUtils.isNotEmpty(permissions)){
                        for (Permission permission : permissions) {
                            authorities.add(new SimpleGrantedAuthority(permission.getPerCode()));
                        }
                    }

                }
            }


        }

        return new LoginUser(user,authorities);
    }
}
