package com.example.lv.config.mysql;

import java.util.Scanner;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.config.mysql
 * @className: Test
 * @author: dus
 * @description:
 * @date: 2024/11/6 10:22
 * @version: 1.0
 */
public class Test {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            String s1 = longestPalindrome(s);
            System.out.println(s1);
        }
    }

    public static String longestPalindrome(String s) {
        int n = s.length();
        if(n == 0 || n==1){return s;}
        boolean[][] dp = new boolean[n][n];  //定义二位数组存储值，//初始化全部都是false

        int start = 0; //回文串的开始位置
        int max = 1; //回文串的最大长度

        for(int i=0; i<n;i++){
            dp[i][i]=true;  //1个元素的肯定是true，这个回文没有什么太多意义，适合三个元素回文
            //下面两个紧挨的元素相同的也变为true,之后就可以从三个开始找，这个情况适合四个元素的回文
            if(i<n-1 && s.charAt(i) == s.charAt(i+1)){//n-1是为了防止s.charAt(i+1)发生越界异常
                dp[i][i+1] = true;
                start = i; //如果最后没有大于三的，那么最长就是2个元素的
                max=2;
            }
        }
        for (int l=3;l<=n;l++){ //l表示检索的子串长度，等于3表示先检索长度为3的子串
            for(int i =0; i+l-1 < n; i++){
                int j = l+i-1; //终止字符位置
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]==true){
                    dp[i][j] = true;
                    start = i;
                    max = l;
                }
            }
        }
        return s.substring(start,start + max);

    }

}
