package com.example.lv.service.thread;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @project xiaobai
 * @description 异步线程接口  异步方法
 * @author gxjh2
 * @date 2024/10/19 15:57:03
 * @version 1.0
 */
public interface AsyncService {

    /**
     * 执行异步任务
     * 可以根据需求，自己设置参数
     * 执行异步插入表数据
     */
    void executeAsync();

    /**
     * 发送短信
     * @param mobile
     * @param content
     */
    void sendSms(String mobile, String content);

    /**
     * 发送邮件
     * @param email
     * @param content
     */
    void sendEmail(String email, String content);

    /**
     * 发送验证码
     * @param mobile
     * @return
     * @throws InterruptedException
     */
    Future<String> sendCode(String mobile) throws InterruptedException;

    /**
     * 同步任务
     */
    void syncTasks();

    /**
     * 异步保存任务
     * @param blockTaskList
     * @param countDownLatch
     */
    void asyncSaveTask(List<String> blockTaskList, CountDownLatch countDownLatch);


}
