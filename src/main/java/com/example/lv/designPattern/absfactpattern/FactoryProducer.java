package com.example.lv.designPattern.absfactpattern;

import com.example.lv.designPattern.factorymode.shapeFactory.ShapeFactory2;
import com.example.lv.util.Constant;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 工厂创造器
 * @date 2023/6/28 09:55:28
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice){
        if(Constant.SHAPE.equalsIgnoreCase(choice)){
            return new ShapeFactory2();
        } else if(Constant.COLOR.equalsIgnoreCase(choice)){
            return new ColorFactory2();
        }
        return null;
    }

}
