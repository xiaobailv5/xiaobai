package com.example.lv.controller.thread;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: SynchronizedTest
 * @author: dus
 * @description: synchronized关键字 测试
 * @date: 2024/10/31 20:07
 * @version: 1.0
 */
public class SynchronizedTest {

    /*
    * 性能 使用synchronized关键字，可能导致线程阻塞，从而降低性能。尽量将synchronized锁的范围缩小，以减少锁竞争。
    * 死锁风险 使用synchronized关键字，可能导致死锁。 例如，当多个线程同时访问同一个对象的synchronized方法时，可能会导致死锁。
    * 原子性和可见性 使用synchronized关键字，不仅保证代码块的原子性，还保证进入同步代码块之前对变量的修改，对于其他线程是可见的。
    * 公平性 synchronized关键字不保证锁的公平性。当多个线程同时请求锁时，锁的获取顺序是随机的，而不是按照请求顺序获取。
    *
    * */

    // 锁方法
    /*private static int count = 0;

    public static synchronized void increment() {
        count++;
    }

    private static synchronized int getCount() {
        return count;
    }
    public static void main(String[] args) {

        int i = 0;
        while (i < 5) {
            increment();
            System.out.println(getCount());
            i++;
        }


    }*/
    /*锁 代码块*/
    private static int count = 0;
    private final Object lock = new Object();
    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    private int getCount() {
        synchronized (lock) {
            return count;
        }

    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        int i = 0;
        while (i < 5) {
            test.increment();
            System.out.println(test.getCount());
            i++;
        }
    }

}
