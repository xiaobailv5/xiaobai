package com.example.lv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller
 * @className: LoginController
 * @author: dus
 * @description:
 * @date: 2024/11/22 13:03
 * @version: 1.0
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // 返回登录页面的视图名
    }


}
