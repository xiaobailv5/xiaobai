package com.example.lv.designPattern.factorymode.colorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 蓝
 * @date 2023/6/28 09:30:55
 */
public class Blue implements Color{

    private static final Logger LOGGER = LoggerFactory.getLogger(Blue.class);

    @Override
    public void fill() {
        LOGGER.info("Inside Blue::fill() method.");
    }
}
