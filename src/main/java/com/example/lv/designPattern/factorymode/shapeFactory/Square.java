package com.example.lv.designPattern.factorymode.shapeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description
 * @date 2023/6/27 15:38:51
 */
public class Square implements Shape{

    private static final Logger LOGGER = LoggerFactory.getLogger(Square.class);

    @Override
    public void draw() {
        LOGGER.info("Inside Square::draw() method.");
    }
}
