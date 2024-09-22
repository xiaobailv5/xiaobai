package com.example.lv.designPattern.factorymode.colorFactory;

import com.example.lv.util.Constant;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 颜色工厂
 * @date 2023/6/28 09:35:26
 */
public class ColorFactory {

    /**
     * 使用 getColor 方法获取颜色的对象
     */
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

}
