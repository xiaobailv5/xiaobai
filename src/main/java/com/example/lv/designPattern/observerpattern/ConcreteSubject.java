package com.example.lv.designPattern.observerpattern;

import java.util.ArrayList;
import java.util.List;

// 具体主题
class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }


    //测试一下
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserverA(subject);
        Observer observer2 = new ConcreteObserverB(subject);
        subject.attach(observer1);
        subject.attach(observer2);
        subject.setState("new state");
    }
}
