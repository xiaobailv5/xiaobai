package com.example.lv.designPattern.factorymode.colorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 红
 * @date 2023/6/28 09:27:31
 */
public class Red implements Color{

    private static final Logger LOGGER = LoggerFactory.getLogger(Red.class);

    @Override
    public void fill() {
        LOGGER.info("Inside Red::fill() method.");
    }
}
