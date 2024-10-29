package com.example.lv.controller.szml;

/**
 * @project xiaobai
 * @description 数组反转
 * @author gxjh2
 * @date 2024/10/29 20:24:14
 * @version 1.0
 */
public class CharRev {


    public static void main(String[] args) {
        String str = "hell0 world";

        //字符串反转
        StringBuffer sb = new StringBuffer(str);
        System.out.println(sb.reverse());

        //字符数组反转
        char[] chars = str.toCharArray();
        reverse(chars);
        System.out.println(chars);
    }

    private static void reverse(char[] chars) {

        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
    }
}
