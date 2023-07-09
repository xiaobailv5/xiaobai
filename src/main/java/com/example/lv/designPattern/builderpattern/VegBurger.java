package com.example.lv.designPattern.builderpattern;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 素食汉堡
 * @date 2023/6/28 14:53:12
 */
public class VegBurger extends Burger{

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 15.0f;
    }
}
