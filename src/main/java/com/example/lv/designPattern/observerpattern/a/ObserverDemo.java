package com.example.lv.designPattern.observerpattern.a;

// 使用
public class ObserverDemo {
    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();
        NewsReader reader1 = new NewsReader("读者A");
        NewsReader reader2 = new NewsReader("读者B");

        newsAgency.addObserver(reader1);
        newsAgency.addObserver(reader2);

        newsAgency.setNews("Java 23 发布！");
    }

}
