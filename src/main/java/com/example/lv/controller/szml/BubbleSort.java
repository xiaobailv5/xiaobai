package com.example.lv.controller.szml;

/**
 * @program: xiaobai
 * @ClassName BubbleSort
 * @description: 冒泡排序
 * @author: gxjh
 * @create: 2024-11-17 18:25
 * @Version 1.0
 **/
public class BubbleSort {

    public static void main(String[] args) {

//        int[] arr = new int[] {64, 34, 25, 12, 22, 11, 90};
        int[] arr = new int[] {64, 34, 25, 12, 22, 90, 11};

        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {

                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(String.valueOf(arr[i])+",");
        }

    }
}