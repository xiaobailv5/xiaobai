package com.example.lv.designPattern.observerpattern.a;

import java.util.Observable;
import java.util.Observer;

// 具体观察者（实现 Observer 接口）
class NewsReader implements Observer {
    private String name;

    public NewsReader(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name + " 收到新闻: " + arg);
    }

//    @Override
//    public void update(Subject subject) {
//        System.out.println(name + " 收到新闻: " + subject);
//    }


}