package com.example.lv.service.impl.thread;

import com.example.lv.service.thread.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @project xiaobai
 * @description 异步线程实现类
 * @author gxjh2
 * @date 2024/10/19 15:57:53
 * @version 1.0
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {

        logger.info("start executeAsync");

        System.out.println("异步线程要做的事情");
        System.out.println("可以在这里执行批量插入等耗时的事情");

        logger.info("end executeAsync");

    }


    /**
     * 发送短信
     */
    @Override
    @Async(value = "asyncServiceExecutor")
    public void sendSms(String mobile, String content) {

        try {

            Thread.sleep(5000);
            // xxxHandle.sendSms(mobile, content)
            logger.info("发送短信至 {} 成功，短信内容：{}", mobile, content);
        } catch (Exception e) {

            logger.error("发送短信至 {} 失败，异常信息：{}", mobile, e);
        }
    }

    /**
     * 发送邮件
     */
    @Override
    @Async(value = "asyncServiceExecutor")
    public void sendEmail(String email, String content) {

        try {

            Thread.sleep(5000);
            // xxxHandle.sendEmail(email, content)
            logger.info("发送邮件至 {} 成功，邮件内容：{}", email, content);
        } catch (Exception e) {

            logger.error("发送邮件至 {} 失败，异常信息：{}", email, e);
        }
    }

    /**
     * 发送验证码
     */
    @Override
    @Async(value = "asyncServiceExecutor")
    public Future<String> sendCode(String mobile) throws InterruptedException {

        Thread.sleep(3000);
        logger.info("尊敬的开发者，Thread: [{}], 为您服务...", Thread.currentThread().getName());
        return new AsyncResult<>("发送验证码至 " + mobile + " 成功");
    }

    @Async("asyncServiceExecutor")
    public void syncTasks() {

        // 构建一个有100000个任务的列表 [Task-1 ~ Task-10000]
        List<String> taskList = new ArrayList<>();
        for (int i = 0; i < 10001; i++) {

            taskList.add("Task-" + (i + 1));
        }
        // 每个区块可容纳1000条任务
        int blockSize = 1000;
        // 区块数量 10
        int blockSum = taskList.size() % blockSize == 0 ? taskList.size() / blockSize : taskList.size() / blockSize + 1;
        // 以区块分段的任务列表
        List<List<String>> targetTaskList = new ArrayList<>();
        for (int i = 0; i < blockSum - 1; i++) {
            // 10 - 1 = 9
            // 0 [Task-1 ~ Task-1000]
            // ...
            // 8 [Task-8001 ~ Task-9000]
            targetTaskList.add(i, taskList.subList(i * blockSize, blockSize * (i + 1)));
        }
        // 9 [Task-9001 ~ Task-10000]
        targetTaskList.add(blockSum - 1, taskList.subList((blockSum - 1) * blockSize, taskList.size()));
        logger.info("start==========="+targetTaskList);
        batchSave(targetTaskList);

    }

    private void batchSave(List<List<String>> targetTaskList) {

        // 主线程中创建一个CountDownLatch计数器，数值为9，然后将其传递给多个子线程
        CountDownLatch countDownLatch = new CountDownLatch(targetTaskList.size());
        try {

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < targetTaskList.size(); i++) {

                List<String> blockTaskList = targetTaskList.get(i);
                //
                asyncSaveTask(blockTaskList, countDownLatch);
            }

            // 主线程在需要等待子线程执行完毕后再继续执行时调用 await() 方法
            countDownLatch.await();
            long endTime = System.currentTimeMillis();
            logger.info("batchSaveTask -> 线程名：{}，所有任务都执行完毕，运行时长：{} ms", Thread.currentThread().getName(), endTime - startTime); // 1016 ms
        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    /**
     * 异步
     * @param tasks
     * @param countDownLatch
     */
    @Async("asyncServiceExecutor")
    public void asyncSaveTask(List<String> tasks, CountDownLatch countDownLatch) {

        try {

            Thread.sleep(1000);
            logger.info("asyncSaveTask -> 线程名：{}，保存数量为{}的任务成功", Thread.currentThread().getName(), tasks.size());
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            // 子线程在执行完任务后调用countDown()方法，将计数器减1
            countDownLatch.countDown();
        }
    }


}
