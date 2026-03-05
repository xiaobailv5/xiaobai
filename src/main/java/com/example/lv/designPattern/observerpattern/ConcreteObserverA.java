package com.example.lv.designPattern.observerpattern;


// 具体观察者
class ConcreteObserverA implements Observer {
    public ConcreteObserverA(ConcreteSubject subject) {
//        subject.attach(this);

        System.out.println("ObserverA 观察者A已经订阅了主题");

    }

    @Override
    public void update(Subject subject) {
        System.out.println("ObserverA 收到通知，状态: " + ((ConcreteSubject) subject).getState());
    }

}
