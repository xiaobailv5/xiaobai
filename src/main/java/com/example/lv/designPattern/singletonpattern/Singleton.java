package com.example.lv.designPattern.singletonpattern;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 懒汉模式
 * @date 2023/6/28 11:07:48
 */
public class Singleton {

    private static Singleton singleton;
    private Singleton(){

    }
    public static synchronized Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
