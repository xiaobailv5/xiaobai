package com.example.lv;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @project xiaobai
 * @description 加密测试类
 * @author gxjh2
 * @date 2024/9/21 16:34:43
 * @version 1.0
 */
public class BCryptPasswordEncoderTest {

    public static void main(String[] args) {
        String pass = "123456";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("123456",hashPass);
        System.out.println(f);

    }

}
