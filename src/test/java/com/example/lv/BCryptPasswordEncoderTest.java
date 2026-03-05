package com.example.lv;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author gxjh2
 * @version 1.0
 * @project xiaobai
 * @description 加密测试类
 * @date 2024/9/21 16:34:43
 */
public class BCryptPasswordEncoderTest {

    public static void main(String[] args) {
        String pass = "123456";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("123456", hashPass);
        System.out.println(f);

    }

}
