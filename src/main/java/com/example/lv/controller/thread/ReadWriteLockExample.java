package com.example.lv.controller.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: ReadWriteLockExample
 * @author: dus
 * @description: 读写锁
 * @date: 2024/10/31 20:41
 * @version: 1.0
 */
public class ReadWriteLockExample {

    private final Map<String, String> dataSource = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    //读取数据
    public String read(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "is reading...");
            return dataSource.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    //写入数据
    public void write(String key, String value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "is writing...");
            dataSource.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample example = new ReadWriteLockExample();
        //一个线程写入数据
        Thread writerThread = new Thread(() -> {
            example.write("key", "value");
            System.out.println("WriterThread has finished writing.");
        }, "WriterThread");
        //创建多个线程 读取数据
        Thread readerThread1 = new Thread(() -> {
            String value = example.read("key");
            System.out.println("ReaderThread1 got value: " + value);
            try {
                writerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ReaderThread1");

        Thread readerThread2 = new Thread(() -> {
            String value = example.read("key");
            System.out.println("ReaderThread2 got value: " + value);
            try {
                readerThread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ReaderThread2");
        writerThread.start();
        readerThread1.start();
        readerThread2.start();


    }
}
