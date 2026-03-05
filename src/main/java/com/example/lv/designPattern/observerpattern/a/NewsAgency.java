package com.example.lv.designPattern.observerpattern.a;

import java.util.Observable;

// 具体主题（继承 Observable）
class NewsAgency extends Observable {
    private String news;

    public void setNews(String news) {
        this.news = news;
        setChanged(); // 标记状态已改变
        notifyObservers(news); // 通知所有观察者
    }

}
