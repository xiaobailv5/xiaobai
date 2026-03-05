package com.example.lv.designPattern.observerpattern;

// 主题接口
interface Subject {


    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();

}
