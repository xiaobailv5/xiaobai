package com.example.lv.exception;

import com.alibaba.fastjson.JSON;
import com.example.lv.bean.base.Result;
import com.example.lv.util.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 授权失败
 * @date 2023/6/22 14:45:34
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 403
        Result result = new Result(HttpStatus.FORBIDDEN.value(),"权限不足");
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtil.renderString(response,json);
    }
}
