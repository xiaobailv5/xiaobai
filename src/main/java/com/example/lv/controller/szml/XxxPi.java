package com.example.lv.controller.szml;

import java.util.Scanner;

/**
 * @project xiaobai
 * @description XXX匹配编程 正则表达式
 * @author gxjh2
 * @date 2024/10/29 20:19:28
 * @version 1.0
 */
public class XxxPi {

    public static void main(String[] args) {
        String match = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$";
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            boolean result = str.matches(match);
            System.out.println(result);
        }



    }
}
