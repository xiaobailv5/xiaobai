package com.example.lv.controller.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @project xiaobai
 * @description 判断线程池任务执行完成的方式
 * @author gxjh2
 * @date 2024/10/19 20:21:51
 * @version 1.0
 */
public class IsShutdownThreadPoolDemo {

    /**
     * 创建一个线程池 核心线程数10个，最大线程数15个
     */
    public static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            10,
            15,
            0L,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(10));

    /**
     * 随机休眠0到10秒
     * @param index
     */
    private static void sleepMethod(int index) {

        try {
            long sleepTime = new Double(Math.random() * 10000).longValue();
            Thread.sleep(sleepTime);
            System.out.println("当前线程执行结束：" + index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //方法四使用
    private static int taskNum = 0;//计数器

    /**
     * 判断线程池任务执行完成的方式
     * 方法一：isTerminated()方法，在执行shutdown()方法后，判断isTerminated()是否为true，如果为true，则表示线程池已经停止。
     * 方法二：ThreadPoolExecutor的getCompletedTaskCount()方法，判断线程池中已经完成的任务数量是否等于线程池中提交的任务数量。
     * 方法三：CountDownLatch，使用闭锁计数来判断是否全部完成。
     * 方法四：手动维护一个公共计数，原理和闭锁计数类型，更加灵活。
     * 方法五： Future，使用Future来判断是否执行完成,submit向线程池提交任务。
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        /*for (int i = 0; i < 10; i++) {
            int index = i;
            pool.execute(() -> sleepMethod(index));
        }*/
        /*
        * 方法一
        * shutdown() ：对线程池进行有序关闭。调用该方法后，线程池将不再接受新的任务，但会继续执行已提交的任务。如果线程池已经处于关闭状态，则对该方法的调用没有额外的作用。
        * isTerminated() ：判断线程池中的所有任务是否在关闭后完成。只有在调用了shutdown()或shutdownNow()方法后，所有任务执行完毕，才会返回true。
        * 需要注意的是，在调用shutdown()之前调用isTerminated()方法始终返回false。
        * 优点：操作简单。
        * 缺点：需要关闭线程池。并且日常使用是将线程池注入到Spring容器，然后各个组件中统一用同一个线程池，不能直接关闭线程池。
        */
       /* pool.shutdown();
        while (!pool.isTerminated()) {

            Thread.sleep(1000);
            System.out.println("还没停止。。。。");

        }
        System.out.println("线程池已经停止了");

        System.out.println("=======================================================================");*/

        /*
         * 方法二：当线程池完成的线程数等于线程池中的总线程数
         * getTaskCount() ：返回计划执行的任务总数。由于任务和线程的状态可能在计算过程中动态变化，返回的值只是一个近似值。这个方法返回的是线程池提交的任务总数，包
         * 括已经完成和正在执行中的任务。
         * getCompletedTaskCount() ：返回已经完成执行的任务的大致总数。由于任务和线程的状态可能在计算过程中动态改变，返回的值只是一个近似值，并且在连续的调用中不会减少。
         * 这个方法返回的是已经完成执行的任务数量，不包括正在执行中的任务。
         * 优点 ：不必关闭线程池，避免了创建和销毁带来的损耗。
         * 缺点 ：使用这种判断存在很大的限制条件；必须确定在循环判断过程中没有新的任务产生。
         */
        /*while (!(pool.getTaskCount() == pool.getCompletedTaskCount())) {
            System.out.println("任务总数:" + pool.getTaskCount() + "； 已经完成任务数：" + pool.getCompletedTaskCount());

            Thread.sleep(1000);
            System.out.println("还没停止。。。");

        }
        System.out.println("全部执行完毕");*/

        /*
         * 方法三：计数器，判断线程是否执行结束
         * 优点：代码优雅，不需要对线程池进行操作。
         * 缺点 ：需要提前知道线程数量；性能较差；还需要在线程代码块内加上异常判断，否则在 countDown之前发生异常而没有处理，就会导致主线程永远阻塞在 await。
        */
        /*CountDownLatch taskLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int index = i;
            pool.execute(() -> {
                sleepMethod(index);
                taskLatch.countDown();
                System.out.println("当前计数器数量：" + taskLatch.getCount());
            });
        }
        //当前线程阻塞，等待计数器置为0
        taskLatch.await();
        System.out.println("全部执行完毕");*/

        /*
         * 方法四：公共计数  通过加锁计数，然后循环判断。
         * 优点 ：手动维护方式更加灵活，对于一些特殊场景可以手动处理。
         * 缺点 ：和CountDownLatch相比，一样需要知道线程数目，但是代码实现比较麻烦。
         */
        /*Lock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            int index = i;
            pool.execute(() -> {
                sleepMethod(index);
                lock.lock();
                taskNum++;
                lock.unlock();
            });
        }
        while(taskNum < 10) {
            Thread.sleep(1000);
            System.out.println("还没停止。。。当前完成任务数：" + taskNum);
        }
        System.out.println("全部执行完毕");*/

        /*
         * 方法五：Future
         * 优点：使用简单，不需要关闭线程池。
         * 缺点：每个提交给线程池的任务都会关联一个Future对象，这可能会引入额外的内存开销。如果需要处理大量的任务，可能会占用较多的内存。
         */
        for (int i = 0; i < 10; i++) {
            int index = i;
            Future future = pool.submit(() -> sleepMethod(index));
            while (!future.isDone()) {
                Thread.sleep(1000);
                System.out.println("还没停止呢。。。。");
            }
        }

        System.out.println("执行完毕");
    }
}
