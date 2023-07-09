package com.example.lv.expression;

import com.example.lv.dao.entity.base.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 自定义权限认证
 * @date 2023/6/22 15:55:17
 */
@Component("ex")
public class ExpressionRoot {

    public boolean hasAuthority(String authority){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        //判断用户权限集合中是否存在authority
        for (GrantedAuthority grantedAuthority: authorities) {
            String auth = grantedAuthority.getAuthority();
            if(auth.equals(authority)){
                return true;
            }
        }

        return false;
    }


}
