package com.example.lv.designPattern.absfactpattern;

import com.example.lv.designPattern.factorymode.colorFactory.Blue;
import com.example.lv.designPattern.factorymode.colorFactory.Color;
import com.example.lv.designPattern.factorymode.colorFactory.Green;
import com.example.lv.designPattern.factorymode.colorFactory.Red;
import com.example.lv.designPattern.factorymode.shapeFactory.Shape;
import com.example.lv.util.Constant;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 颜色工厂
 * @date 2023/6/28 09:35:26
 */
public class ColorFactory2 extends AbstractFactory{

    /**
     * 使用 getColor 方法获取颜色的对象
     */
    @Override
    public Color getColor(String colorType){
        if(StringUtils.isEmpty(colorType)){
            return null;
        }
        if(Constant.RED.equalsIgnoreCase(colorType)){
            return new Red();
        } else if(Constant.GREEN.equalsIgnoreCase(colorType)){
            return new Green();
        } else if(Constant.BLUE.equalsIgnoreCase(colorType)){
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }

}
