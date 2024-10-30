package com.example.lv.controller.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: BankSystem
 * @author: dus
 * @description: 银行转账
 * @date: 2024/10/29 13:51
 * @version: 1.0
 */
public class BankSystem {


    private static final Logger LOGGER = LoggerFactory.getLogger(BankSystem.class);
    private static final int THREAD_POOL_SIZE = 10;
    private final ConcurrentHashMap<String, Double> accounts = new ConcurrentHashMap();
    private final ReentrantReadWriteLock rwLock = new  ReentrantReadWriteLock();
    private final Lock writeLock = rwLock.writeLock();
    private final Lock readLock = rwLock.readLock();
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    //初始化账户
    public BankSystem(Map<String, Double> initialBalances) {

        accounts.putAll(initialBalances);
    }

    /**
     * 转账方法
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @return void
     * @author gxjh2
     * @date 2024/10/29 14:00:40
    */
    public void transfer(String fromAccount, String toAccount, double amount) {

        executorService.submit( () -> {
            try {
                writeLock.lock();
                //如果转账金额足够
                if (accounts.getOrDefault(fromAccount, 0.0) > amount) {
                    accounts.put(fromAccount, accounts.get(fromAccount) - amount);
                    accounts.putIfAbsent(toAccount, 0.0);
                    accounts.put(toAccount, amount);
                    logTransfer(fromAccount, toAccount, amount);
                } else {
                    LOGGER.info("转账失败，账户余额不足");
                }
            }catch (Exception e) {
                LOGGER.error("转账失败"+ e.getMessage());
            }finally {
                writeLock.unlock();
            }

        });
    }

    /**
     * 转账日志
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @return void
     * @author gxjh2
     * @date 2024/10/29 14:08:35
    */
    private void logTransfer(String fromAccount, String toAccount, double amount) {

        LOGGER.info("转账成功：" + amount + "from" + fromAccount + "to" + toAccount);
    }

    /**
     * 查询账户余额
     * @param account
     * @return java.lang.Double
     * @author gxjh2
     * @date 2024/10/29 14:11:45
    */
    public Double getBalance(String account) {

        readLock.lock();
        try {
            return accounts.getOrDefault(account, 0.0);
        }finally {
            readLock.unlock();
        }
    }

    /**
     * 关闭线程池
     * @return void
     * @author gxjh2
     * @date 2024/10/29 14:12:20
    */
    public void shutdown() {
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException{
        Map<String, Double> initialBalances = new HashMap<>();
        initialBalances.put("A", 1000.0);
        initialBalances.put("B", 500.0);
        initialBalances.put("C", 200.0);

        BankSystem bankSystem = new BankSystem(initialBalances);


        List<Callable<Void>> transfers = new ArrayList<>();

        transfers.add(() -> {bankSystem.transfer("A", "B", 200.0); return null;});
        transfers.add(() -> {bankSystem.transfer("B", "C", 150.0); return null;});
        transfers.add(() -> {bankSystem.transfer("A", "C", 300.0); return null;});
        transfers.add(() -> {bankSystem.transfer("C", "A", 500.0); return null;});

        List<Future<Void>> futures = bankSystem.executorService.invokeAll(transfers);

        for (Future<Void> future : futures) {
            try {
                future.get();
            }catch (ExecutionException e) {
                LOGGER.error("转账失败....", e.getCause());
            }
        }

        bankSystem.shutdown();

        for (Map.Entry<String, Double> entry : bankSystem.accounts.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
