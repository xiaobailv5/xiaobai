package com.example.lv.controller;

import com.example.lv.bean.base.Result;
import com.example.lv.bean.base.ResultGenerator;
import com.example.lv.bean.web.LoginRequest;
import com.example.lv.service.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 登录 控制层
 * @date 2023/6/18 15:05:44
 */
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private ILoginService loginService;

    @PostMapping("/user/login")
    public Result login(@RequestBody LoginRequest request){
        LOGGER.info("登录======开始"+request);
        return loginService.login(request);
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("/user/logOut")
    public Result logOut(){

        Result result;
        try {
            return loginService.logOut();
        }catch (Exception e){
            e.printStackTrace();
            result = ResultGenerator.getFailResult(e.getMessage());
        }
        return result;
    }
}
