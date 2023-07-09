package com.example.lv.designPattern.builderpattern;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 食物条目
 * @date 2023/6/28 14:35:30
 */
public interface Item {
    /**
     * 名称
     * @return
     */
    public String name();

    /**
     * 包装物
     * @return
     */
    public Packing packing();

    /**
     * 价格
     * @return
     */
    public float price();

}
