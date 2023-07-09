package com.example.lv.exception;

import com.alibaba.fastjson.JSON;
import com.example.lv.bean.base.Result;
import com.example.lv.util.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 认证异常
 * @date 2023/6/22 14:37:31
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 401
        Result result = new Result(HttpStatus.UNAUTHORIZED.value(),"用户认证失败，请重新登录");
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtil.renderString(response,json);

    }
}
