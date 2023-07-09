package com.example.lv.controller;

import com.example.lv.aspect.OperationAnnotation;
import com.example.lv.bean.base.Result;
import com.example.lv.bean.base.ResultGenerator;
import com.example.lv.bean.web.user.UserRequest;
import com.example.lv.dao.entity.base.User;
import com.example.lv.service.IUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 用户控制层
 * @date 2023/6/17 14:03:07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    /**
     * @PreAuthorize("@ex.hasAuthority('test')")  自定义权限校验方法
     * @PreAuthorize("hasAuthority('test')")  框架默认方法
     * @param request
     * @return
     */
    @PostMapping(value = "/getUserList")
    @OperationAnnotation(action = "getUserList",content = "查询用户列表")
    @PreAuthorize("@ex.hasAuthority('test')")
    public Result getUserList(@RequestBody UserRequest request){
        LOGGER.info("getUserList入参"+request);
        Result result;
        Integer start = request.getStart();
        if(ObjectUtils.isEmpty(start)){
            start = 0;
            request.setStart(start);
        }
        Integer limit = request.getLimit();
        if(ObjectUtils.isEmpty(limit)){
            limit = 10;
            request.setLimit(limit);
        }
        try {
            List<User> userList = userService.getUserList(request);
            //查询总条数
            int count = userService.getUserCount(request);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result = ResultGenerator.getSuccessResult(userList,map);
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return ResultGenerator.getFailResult(e.getMessage());
        }
        return result;
    }
}
