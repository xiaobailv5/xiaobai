package com.example.lv.thread;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.thread
 * @className: MyThread
 * @author: dus
 * @description: 多线程
 * @date: 2024/8/30 11:11
 * @version: 1.0
 */
public class MyThread extends Thread {

    /**
     * 线程停止标识
     */
    volatile boolean stop = false;

    @Override
    public void run() {

        while (!stop){
            System.out.println(getName() + "is running");

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("week up from block ...");
                e.printStackTrace();
                stop = true;
            }
        }
        System.out.println(getName() + "is exiting...");
    }

}
