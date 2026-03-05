package com.example.lv.designPattern.observerpattern;

class ConcreteObserverB implements Observer {
    public ConcreteObserverB(ConcreteSubject subject) {
//        subject.attach(this);
        System.out.println("ObserverB 观察了主题");
    }

    @Override
    public void update(Subject subject) {
        System.out.println("ObserverB 收到通知，状态: " + ((ConcreteSubject) subject).getState());
    }


}
