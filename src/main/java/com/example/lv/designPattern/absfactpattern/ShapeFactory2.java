package com.example.lv.designPattern.factorymode.shapeFactory;

import com.example.lv.designPattern.absfactpattern.AbstractFactory;
import com.example.lv.designPattern.factorymode.colorFactory.Color;
import com.example.lv.util.Constant;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description
 * @date 2023/6/27 16:12:46
 */
public class ShapeFactory2 extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        return null;
    }

    /**
     * 使用 getShape 方法获取形状类型的对象
     */
    @Override
    public Shape getShape(String shapeType){
        if(StringUtils.isEmpty(shapeType)){
            return null;
        }
        if(Constant.CIRCLE.equalsIgnoreCase(shapeType)){
            return new Circle();
        } else if(Constant.RECTANGLE.equalsIgnoreCase(shapeType)){
            return new Rectangle();
        } else if(Constant.SQUARE.equalsIgnoreCase(shapeType)){
            return new Square();
        }
        return null;
    }

}
