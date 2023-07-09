package com.example.lv.designPattern.factorymode.colorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 绿
 * @date 2023/6/28 09:29:39
 */
public class Green implements Color{

    private static final Logger LOGGER = LoggerFactory.getLogger(Green.class);

    @Override
    public void fill() {
        LOGGER.info("Inside Green::fill() method.");
    }
}
