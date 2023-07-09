package com.example.lv.designPattern.prototypepattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 鸟
 * @date 2023/7/7 15:05:29
 */
public class Bird extends Animal{

    private static final Logger LOGGER = LoggerFactory.getLogger(Bird.class);

    public Bird() {
        type="Bird";
    }

    @Override
    public void produce() {
        LOGGER.info("It's a bird");
    }
}
