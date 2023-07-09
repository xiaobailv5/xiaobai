package com.example.lv.designPattern.factorymode.shapeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description
 * @date 2023/6/27 15:40:39
 */
public class Circle implements Shape{

    private static final Logger LOGGER = LoggerFactory.getLogger(Circle.class);

    @Override
    public void draw() {
        LOGGER.info("Inside Circle::draw() method.");
    }
}
