package com.example.lv.controller.szml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @project xiaobai
 * @description 将字符串“[[1,5,6,7],6,7,3]”转换为数组，并去除重复元素；
 * @author gxjh2
 * @date 2024/10/29 20:31:02
 * @version 1.0
 */
public class Sz {


    public static void main(String[] args) {
        String str = "[[1,5,6,7],6,7,3]";
        String[] split = str.split(",");
        Set<Object> set = new HashSet<>();
        for (String s : split) {
            String process = process(s);
            set.add(process);
        }

        List objects = new ArrayList<>(set);

        for (int i = 0; i < objects.size(); i++) {
            System.out.println(objects.get(i));
        }
    }

    private static String process(String s) {

        if (s.contains("[")) {
            s.replaceAll("\\[","");
            if (s.contains("]")) {
                s.replaceAll("\\[","");
            }else {
                return s;
            }


        } else if (s.contains("]")) {
            s.replaceAll("]","");
            return s;
        } else {
            return s;
        }
        return s;
    }
}
