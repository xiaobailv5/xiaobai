package com.example.lv.controller.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: DeadLockExample
 * @author: dus
 * @description: 死锁
 * @date: 2024/10/31 19:14
 * @version: 1.0
 */
public class DeadLockExample {


    private static class Resource {
        private final String name;

        public Resource(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    private static final Resource resource1 = new Resource("Resource 1");
    private static final Resource resource2 = new Resource("Resource 2");

    public static void main(String[] args) {

        /*死锁场景*/
        /*Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1 is trying to acquire resource 1" + resource1.getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource2) {
                    System.out.println("Thread 1 is trying to acquire resource 2" + resource2.getName());
                }
            }

        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2 is trying to acquire resource 2" + resource2.getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource1) {

                    System.out.println("Thread 2 is trying to acquire resource 1" + resource1.getName());
                }
            }

        });
        thread1.start();
        thread2.start();*/


        /*预防死锁  资源排序*/
        /*synchronized (resource1) {
            synchronized (resource2) {
                System.out.println("Thread 2 acquired both resources" + resource2.getName());
            }
            System.out.println("Thread 1 is trying to acquire resource 1" + resource1.getName());
        }*/

        /* 尝试锁定  tryLock方法*/
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        try {
            if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                try {
                    if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("Thread 2 acquired both resources" + resource2.getName());
                        } finally {
                            lock2.unlock();
                        }
                    }
                    System.out.println("Thread 1 is trying to acquire resource 1" + resource1.getName());
                } finally {
                    lock1.unlock();
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
