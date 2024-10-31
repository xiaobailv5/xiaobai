package com.example.lv.controller.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: ConcurrentCollectionExample
 * @author: dus
 * @description: 父子线程共享数据
 * @date: 2024/10/31 12:54
 * @version: 1.0
 */
public class ConcurrentCollectionExample {

    public static void main(String[] args) {
        Map<String, String> shareMap = new ConcurrentHashMap<>();
        shareMap.put("key", "value");
        new Thread(() -> {
            while (!shareMap.containsKey("key")) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(shareMap.get("key"));
        }).start();
    }
}
