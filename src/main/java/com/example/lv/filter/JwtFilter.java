package com.example.lv.filter;

import com.example.lv.dao.entity.base.LoginUser;
import com.example.lv.exception.MyOwnRuntimeException;
import com.example.lv.util.Constant;
import com.example.lv.util.JwtUtil;
import com.example.lv.util.RedisUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description jwt 过滤器
 * @date 2023/6/20 07:40:08
 */
@Configuration
public class JwtFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        //无token放行
        if(StringUtils.isEmpty(token)){
            filterChain.doFilter(request,response);
            return;
        }
        //解析token 获取loginUser对象
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        }catch (Exception e){
            e.printStackTrace();
            logger.info("token非法。。。。");
            throw new RemoteException("token非法。。。。");
        }


        String redisKey = Constant.LOGIN+userId;
        LoginUser loginUser = redisUtil.getObject(redisKey);
        if(ObjectUtils.isEmpty(loginUser)){
            throw new MyOwnRuntimeException("用户认证失败。。");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
