package com.example.lv.controller.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: BlockingQueueExample
 * @author: dus
 * @description: 使用消息队列实现父子线程之间传递数据
 * @date: 2024/10/31 12:57
 * @version: 1.0
 */
public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 10; i++) {
            queue.put(i);
        }

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Integer take = queue.take();
                    System.out.println(take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }

            }
        }).start();
    }
}
