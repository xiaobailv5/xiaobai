package com.example.lv.controller.thread;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: StopThread
 * @author: dus
 * @description: 优雅的停止线程
 * @date: 2024/10/22 10:52
 * @version: 1.0
 */
public class StopThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread( () -> {
            for (int i = 0; i < 1000000; i++) {
                if (Thread.currentThread().isInterrupted() && i > 500000) {
                    break;
                }
                System.out.println(i);
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

}
