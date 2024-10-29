package com.example.lv.controller.thread;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: XxxPi
 * @author: dus
 * @description: XXX-XXX-XXXX 格式匹配   xxx只能是数字  正则表达式
 * @date: 2024/10/29 14:46
 * @version: 1.0
 */
public class XxxPi {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //正则 XXX-XXX-XXXX
        String regex = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$";


        while (scanner.hasNext()) {

            String input = scanner.next();
            Pattern pattern = Pattern.compile(regex);
            boolean matches = pattern.matcher(input).matches();
            System.out.println(matches);

        }
    }
}
