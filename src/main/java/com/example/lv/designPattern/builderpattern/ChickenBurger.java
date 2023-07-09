package com.example.lv.designPattern.builderpattern;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 鸡肉汉堡
 * @date 2023/6/28 14:54:33
 */
public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
