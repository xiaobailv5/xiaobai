package com.example.lv.controller.szml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.szml
 * @className: MaxStrLength
 * @author: dus
 * @description: 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * @date: 2024/11/23 19:02
 * @version: 1.0
 */
public class MaxStrLength {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring2(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>(16);
        int maxLength = 0;
        for (int i = 0, j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            maxLength = Math.max(maxLength, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring2(String s) {

        if (s == null || "".equals(s)) {
            return 0;
        }

        // 哈希集合，记录每个字符是否出现过
        Set<Character> set = new HashSet<>();
        int maxLength = 0;  // 最长子串的长度
        int start = 0;  // 窗口起始位置

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            if (set.contains(currentChar)) {
                // 如果字符已经出现过，则移动窗口的起始位置
                while (set.contains(currentChar)) {
                    set.remove(s.charAt(start));
                    start++;
                }
            }
            // 将字符加入窗口
            set.add(currentChar);
            // 更新最大长度
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

}
