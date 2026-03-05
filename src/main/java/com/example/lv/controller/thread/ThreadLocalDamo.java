package com.example.lv.controller.thread;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: ThreadLocalDamo
 * @author: dus
 * @description: 父子线程之间如何共享传递数据
 * @date: 2024/10/31 12:43
 * @version: 1.0
 */
public class ThreadLocalDamo {

    //创建一个ThreadLocal对象
    private static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "初始值");

    public static void main(String[] args) throws InterruptedException {

        //主线程设置ThreadLocal的值
        threadLocal.set("主线程的值");

        //创建并启动子线程
        Thread thread = new Thread(() -> {
            //子线程获取ThreadLocal的值
//            String value = threadLocal.get();
            System.out.println("子线程获取到的值：" + threadLocal.get());

            //子线程修改ThreadLocal的值
//            threadLocal.set("子线程修改的值");
        });
        thread.start();
//        thread.join();

        //主线程获取ThreadLocal的值
    }
}
