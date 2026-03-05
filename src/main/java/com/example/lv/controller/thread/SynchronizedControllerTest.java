package com.example.lv.controller.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller.thread
 * @className: SynchronizedControllerTest
 * @author: dus
 * @description: 加锁关键字
 * @date: 2024/10/28 11:31
 * @version: 1.0
 */
@RestController
@RequestMapping("synchronized")
public class SynchronizedControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizedControllerTest.class);

    @RequestMapping("/saving")
    public String saving(String school) {

        synchronized (school) {

            LOGGER.info(school + "学生交卷");

            save(school);

            LOGGER.info("lock" + "学生交卷完成");

            return "ok";
        }
    }

    private void save(String school) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static Map<String, Integer> values = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        String str = "你好";

        String intern = str.intern();

        System.out.println(intern);


    }
}
