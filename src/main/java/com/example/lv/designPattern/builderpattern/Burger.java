package com.example.lv.designPattern.builderpattern;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 汉堡
 * @date 2023/6/28 14:48:31
 */
public abstract class Burger implements Item{

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    /**
     * 价格
     * @return
     */
    @Override
    public abstract float price();

}
