/*******************    💫 Codegeex Inline Diff    *******************/
package com.example.lv.controller.szml;

/**
 * 两个线程打印奇偶数
 */
public class TwoThreadService {
    private static int count = 1;
    private static final int MAX = 100;
    private static final Object lock = new Object();


    public static void main(String[] args) {
        // Thread for printing odd numbers
        Thread oddThread = new Thread(() -> {
            while (count <= MAX) {
                synchronized (lock) {
                    if (count % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + count);
                        count++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "OddThread");

        // Thread for printing even numbers
        Thread evenThread = new Thread(() -> {
            while (count <= MAX) {
                synchronized (lock) {
                    if (count % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + count);
                        count++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "EvenThread");

        oddThread.start();
        evenThread.start();
    }
}
/****************  aaaaba68aa9c47ff8d5af4511f1eef42  ****************/
