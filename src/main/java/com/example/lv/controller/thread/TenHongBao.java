package com.example.lv.controller.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: TenHongBao
 * @author: dus
 * @description: 100元分成10个红包   单个红包大于1元   小于100的30%
 * @date: 2024/10/29 8:42
 * @version: 1.0
 */
public class TenHongBao {


    public static void main(String[] args) {
        double totalAmount = 100.0;
        int totalEnvelopes = 10;
        List<Double> envelopes = new ArrayList<>();

        // Initialize with at least 1 unit each
        for (int i = 0; i < totalEnvelopes; i++) {
            envelopes.add(1.0);
        }

        double remainingAmount = totalAmount - totalEnvelopes;

        Random random = new Random();

        while (remainingAmount > 0) {
            // Randomly choose an envelope to add more money to
            int chosenIndex = random.nextInt(totalEnvelopes);

            // Calculate the maximum amount we can add to this envelope
            double maxAddAmount = Math.min(remainingAmount, totalAmount * 0.3 - envelopes.get(chosenIndex));

            // Calculate a random amount between 0 and maxAddAmount
            double addAmount = (random.nextDouble() * maxAddAmount);

            // Add the amount to the chosen envelope
            envelopes.set(chosenIndex, envelopes.get(chosenIndex) + addAmount);

            // Update the remaining amount
            remainingAmount -= addAmount;
        }

        // Shuffle the envelopes to ensure randomness
        Collections.shuffle(envelopes);

        // Print the results
        for (double amount : envelopes) {
            System.out.printf("%.2f%n", amount);
        }
    }


   /* public static void main(String[] args) {
        double totalAmount = 100.0;
        int totalEnvelopes = 10;
        double minAmount = 1.0;
        double maxAmount = totalAmount * 0.3;

        List<Double> envelopes = new ArrayList<>();

        // 首先确保每个红包至少有1元
        for (int i = 0; i < totalEnvelopes; i++) {
            envelopes.add(minAmount);
        }

        // 计算剩余要分配的总金额
        double remainingAmount = totalAmount - totalEnvelopes * minAmount;

        // 使用随机分配算法分配剩余金额
        Random random = new Random();
        while (remainingAmount > 0) {
            // 随机选择一个红包（除了最后一个，因为我们要避免超出30%的限制）
            int index = random.nextInt(totalEnvelopes - 1);

            // 计算可以添加到该红包的最大金额（不超过30%且不超过剩余金额）
            double maxToAdd = Math.min(maxAmount - envelopes.get(index), remainingAmount);

            // 随机选择一个小于最大可添加金额的值（确保不是0）
            double amountToAdd = minAmount + (random.nextDouble() * (maxToAdd - minAmount));

            // 更新红包金额和剩余金额
            envelopes.set(index, envelopes.get(index) + amountToAdd);
            remainingAmount -= amountToAdd;
        }

        // 打印红包金额
        for (double amount : envelopes) {
            System.out.printf("%.2f\n", amount);
        }
    }*/


}
