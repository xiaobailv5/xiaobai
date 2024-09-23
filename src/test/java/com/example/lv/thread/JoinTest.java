package com.example.lv.thread;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.thread
 * @className: JoinTest
 * @author: dus
 * @description: join方法  设置执行顺序
 * @date: 2024/8/30 11:16
 * @version: 1.0
 */
public class JoinTest {

    public static void main(String[] args) {

       final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1");
            }
       });

       final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("t2");
            }
       });

       final Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //执行t2
                    t2.join();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("t3");
            }
       });
       //位置随意
       t3.start();
       t2.start();
       t1.start();


    }
}
