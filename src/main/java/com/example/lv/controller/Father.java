package com.example.lv.controller;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller
 * @className: Father
 * @author: dus
 * @description:
 * @date: 2024/7/17 14:09
 * @version: 1.0
 */
public class Father {

    /**
     * 1、子类重写方法权限修饰符可以大于等于父类方法
     * 2、参数类别不能变更
     * 3、方法名不能变更
     * 4、返回类型相同或者是子类
     * @param id
     * @return java.lang.String
     * @author gxjh2
     * @date 2024/7/17 14:12:45
    */
    protected String getName(String id) {

        return "father-name";
    }
}
