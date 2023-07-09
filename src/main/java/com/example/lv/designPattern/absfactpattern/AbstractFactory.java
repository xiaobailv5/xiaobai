package com.example.lv.designPattern.absfactpattern;

import com.example.lv.designPattern.factorymode.colorFactory.Color;
import com.example.lv.designPattern.factorymode.shapeFactory.Shape;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 抽象工厂
 * @date 2023/6/28 09:32:53
 */
public abstract class AbstractFactory {
    /**
     * 颜色
     * @param color
     * @return
     */
    public abstract Color getColor(String color);

    /**
     * 形状
     * @param shape
     * @return
     */
    public abstract Shape getShape(String shape);

}
