package com.example.lv.designPattern.factorymode.shapeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description
 * @date 2023/6/26 17:47:29
 */
public class Rectangle implements Shape{
    private static final Logger LOGGER = LoggerFactory.getLogger(Rectangle.class);
    @Override
    public void draw() {
        LOGGER.info("Inside Rectangle::draw() method.");
    }
}
