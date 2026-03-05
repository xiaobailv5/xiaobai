package com.example.lv.controller.thread;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: SafeThreadStop
 * @author: dus
 * @description: 安全停止线程
 * @date: 2024/10/31 20:21
 * @version: 1.0
 */
public class SafeThreadStop {

    private static class WorkerThread extends Thread {
        // 使用volatile关键字修饰stopRequested变量，确保在多个线程之间可见
        private volatile boolean running = true;

        @Override
        public void run() {
            System.out.println("WorkerThread is running");
            try {
                while (running && !Thread.currentThread().isInterrupted()) {
                    // do work
                    System.out.println("working");
                    //模拟阻塞操作
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                //如果被中断，则打印日志
                System.out.println("WorkerThread is interrupted");
                Thread.currentThread().interrupt();
            } finally {
                cleanUp();
            }

        }

        //停止线程
        public void stopThread() {
            running = false;
            interrupt();
        }

        //清理工作
        private void cleanUp() {
            System.out.println("Cleaning up resources");
        }

        public static void main(String[] args) throws InterruptedException {
            WorkerThread workerThread = new WorkerThread();
            workerThread.start();
            Thread.sleep(5000);
            workerThread.stopThread();
            workerThread.join();
            System.out.println("Main thread finished");
        }
    }
}
