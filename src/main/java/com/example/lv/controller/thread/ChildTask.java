package com.example.lv.controller.thread;

import com.example.lv.designPattern.prototypepattern.Cat;
import com.example.lv.util.TaskProcessUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description
 * @date 2023/7/16 17:39:31
 */
public class ChildTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChildTask.class);

    /**
     * 线程池大小
     */
    private static final int POOL_SIZE = 3;
    /**
     *  数据拆分大小
     */
    private static final int SPLIT_SIZE = 4;
    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 接收jvm关闭信号，实现优雅停机
     */
    protected volatile boolean terminal = false;

    public ChildTask(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 程序执行入口
     */
    public void doExecute() {
        int i = 0;
        while(true) {
            LOGGER.info(taskName + ":Cycle-" + i + "-Begin");
            // 获取数据
            List<Cat> datas = queryData();
            // 处理数据
            taskExecute(datas);
            LOGGER.info(taskName + ":Cycle-" + i + "-End");
            if (terminal) {
                // 只有应用关闭，才会走到这里，用于实现优雅的下线
                break;
            }
            i++;
        }
        // 回收线程池资源
        TaskProcessUtil.releaseExecutors(taskName);
    }

    /**
     * 优雅停机
     */
    public void terminal() {
        // 关机
        terminal = true;
        LOGGER.info(taskName + " shut down");
    }

    /**
     * 处理数据
     */
    private void doProcessData(List<Cat> data, CountDownLatch latch) {
        try {
            for (Cat cat : data) {
                LOGGER.info(taskName + ":" + cat.toString() + ",ThreadName:" + Thread.currentThread().getName());
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            //重新标记一下线程中断状态
            Thread.currentThread().interrupt();
        }catch (Exception e) {
            e.getStackTrace();
            LOGGER.info(e.getMessage());
        } finally {
            if (latch != null) {
                latch.countDown();
            }
        }
    }

    /**
     * 处理单个任务数据
     */
    private void taskExecute(List<Cat> sourceDatas) {
        if (CollectionUtils.isEmpty(sourceDatas)) {
            return;
        }
        // 将数据拆成4份
        List<List<Cat>> splitData = Lists.partition(sourceDatas, SPLIT_SIZE);
        final CountDownLatch latch = new CountDownLatch(splitData.size());

        // 并发处理拆分的数据，共用一个线程池
        for (final List<Cat> data : splitData) {
            ExecutorService executorService = TaskProcessUtil.getOrInitExecutors(taskName, POOL_SIZE);
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    doProcessData(data, latch);
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e){
            //重新标记一下线程中断状态
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            LOGGER.info("异常",e.getStackTrace());
        }
    }

    /**
     * 获取永动任务数据
     */
    private List<Cat> queryData() {
        //暂时循环5次
        int num = 5;
        List<Cat> datas = new ArrayList<>();
        for (int i = 0; i < num; i ++) {
            datas.add(new Cat().setCatName("小白" + i));
        }
        return datas;
    }


}
