package com.example.lv.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 线程池工具类
 * @date 2023/7/10 10:31:52
 */
public class TaskProcessUtil {

    private TaskProcessUtil() {
    }

    /**
     *每个任务，都有自己单独的线程池
     */
    private static Map<String, ExecutorService> executors = new ConcurrentHashMap<>();

    /**
     * 初始化一个线程池
     */
    private static ExecutorService init(String poolName, int poolSize) {
        return new ThreadPoolExecutor(poolSize, poolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("Pool-" + poolName).setDaemon(false).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 获取线程池
     */
    public static ExecutorService getOrInitExecutors(String poolName,int poolSize) {
        ExecutorService executorService = executors.get(poolName);
        if (null == executorService) {
            synchronized (TaskProcessUtil.class) {
                executorService = executors.get(poolName);
                if (null == executorService) {
                    executorService = init(poolName, poolSize);
                    executors.put(poolName, executorService);
                }
            }
        }
        return executorService;
    }

    /**
     * 回收线程资源
     */
    public static void releaseExecutors(String poolName) {
        ExecutorService executorService = executors.remove(poolName);
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
