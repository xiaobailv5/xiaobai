package com.example.lv.designPattern.prototypepattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 狗狗
 * @date 2023/7/7 14:51:49
 */
public class Dog extends Animal{

    private static final Logger LOGGER = LoggerFactory.getLogger(Dog.class);

    public Dog(){
        type = "Dog";
    }

    @Override
    public void produce() {
        LOGGER.info("It's a dog");
    }
}
