package com.example.lv.designPattern.singletonpattern;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 饿汉模式
 * @date 2023/6/28 11:18:55
 */
public class Singleton2 {

    private static Singleton2 singleton = new Singleton2();

    private Singleton2(){

    }
    public static Singleton2 getSingleton(){

        return singleton;
    }
}
