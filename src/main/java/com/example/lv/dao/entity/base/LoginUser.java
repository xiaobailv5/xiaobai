package com.example.lv.dao.entity.base;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description loginUser
 * @date 2023/6/18 11:26:55
 */
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = -7841530689946331323L;

    public LoginUser(User user,List<GrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    private User user;

    List<GrantedAuthority> authorities;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginUser(User user) {
        this.user = user;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {

        return user.getPassWord();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "user=" + user +
                ", authorities=" + authorities +
                '}';
    }
}
