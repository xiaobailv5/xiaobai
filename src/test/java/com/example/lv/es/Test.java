package com.example.lv.es;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.es
 * @className: Test
 * @author: dus
 * @description: 浅拷贝
 * @date: 2024/11/13 17:25
 * @version: 1.0
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {

        UserTest person = new UserTest("张三", 20, "北京");

        Object clone = person.clone();

        System.out.println(person);
        System.out.println(clone);

    }
}
