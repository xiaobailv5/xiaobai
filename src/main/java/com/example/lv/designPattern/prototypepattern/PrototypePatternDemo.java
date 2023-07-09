package com.example.lv.designPattern.prototypepattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 原型测试
 * @date 2023/7/7 15:19:49
 */
public class PrototypePatternDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrototypePatternDemo.class);

    public static void main(String[] args) {

        AnimalCache.loadCache();

        Animal clonedAnimal = AnimalCache.getAnimal("1");
        LOGGER.info("Animal1 : " + clonedAnimal.getType());

        Animal clonedAnimal2 = AnimalCache.getAnimal("2");
        LOGGER.info("Animal2 : " + clonedAnimal2.getType());

        Animal clonedAnimal3 = AnimalCache.getAnimal("3");
        LOGGER.info("Animal3 : " + clonedAnimal3.getType());
    }
}
