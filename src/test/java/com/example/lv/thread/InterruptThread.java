package com.example.lv.thread;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.thread
 * @className: InterruptThread
 * @author: dus
 * @description: 测试线程终止方法
 * @date: 2024/8/30 11:12
 * @version: 1.0
 */
public class InterruptThread {

    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        System.out.println("main thread is running");
        myThread.start();
        Thread.sleep(3000);
        System.out.println("Interrupt thread ..." + myThread.getName());
        myThread.stop = true;
        myThread.interrupt();
        Thread.sleep(3000);
        System.out.println("Stopping application...");


    }

}
