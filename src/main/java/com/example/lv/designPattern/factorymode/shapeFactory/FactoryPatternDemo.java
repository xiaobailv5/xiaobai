package com.example.lv.designPattern.factorymode.shapeFactory;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description
 * @date 2023/6/27 16:19:20
 */
public class FactoryPatternDemo {

    public static void main(String[] args) {
        com.example.lv.designPattern.factorymode.shapeFactory.ShapeFactory2 shapeFactory = new com.example.lv.designPattern.factorymode.shapeFactory.ShapeFactory2();

        //获取 Circle 的对象，并调用它的 draw 方法
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //调用 Circle 的 draw 方法
        shape1.draw();

        //获取 Rectangle 的对象，并调用它的 draw 方法
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //调用 Rectangle 的 draw 方法
        shape2.draw();

        //获取 Square 的对象，并调用它的 draw 方法
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //调用 Square 的 draw 方法
        shape3.draw();
    }
}
