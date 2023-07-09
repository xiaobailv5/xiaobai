package com.example.lv.designPattern.prototypepattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 猫
 * @date 2023/7/7 15:01:25
 */
public class Cat extends Animal{

    private static final Logger LOGGER = LoggerFactory.getLogger(Cat.class);

    public Cat() {
        type = "Cat";
    }

    @Override
    public void produce() {
        LOGGER.info("It's a cat");
    }

}
