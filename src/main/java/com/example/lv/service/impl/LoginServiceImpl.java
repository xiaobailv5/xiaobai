package com.example.lv.service.impl;

import com.example.lv.bean.base.Result;
import com.example.lv.bean.base.ResultGenerator;
import com.example.lv.bean.web.LoginRequest;
import com.example.lv.dao.entity.base.LoginUser;
import com.example.lv.service.ILoginService;
import com.example.lv.util.Constant;
import com.example.lv.util.JwtUtil;
import com.example.lv.util.RedisUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 登录实现类
 * @date 2023/6/18 15:09:31
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Result login(LoginRequest request) {
        //用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证不通过
        if(ObjectUtils.isEmpty(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //认证通过 用userId生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String userId = String.valueOf(loginUser.getUser().getUserId());
        String jwt = JwtUtil.createJWT(userId);
        //user信息存入redis 5分钟
        redisUtil.setObject(Constant.LOGIN+userId,loginUser,300, TimeUnit.SECONDS);
        Map<String,Object> map = new HashMap<>(2);
        map.put("token",jwt);
        return ResultGenerator.getSuccessBeanResult(map);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public Result logOut() {
        //获取SecurityContextHolder中的 用户id
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = loginUser.getUser().getUserId();
        //删除redis中的用户
        String redisKey = Constant.LOGIN+userId;
        redisUtil.deleteObject(redisKey);

        return ResultGenerator.getSuccessResult();
    }


}
