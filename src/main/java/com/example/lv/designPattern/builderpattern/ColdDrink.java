package com.example.lv.designPattern.builderpattern;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 冷饮
 * @date 2023/6/28 14:51:23
 */
public abstract class ColdDrink implements Item{

    @Override
    public Packing packing() {
        return new Bottle();
    }

    /**
     * 价格
     * @return
     */
    @Override
    public abstract float price();

}
