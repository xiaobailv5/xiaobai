package com.example.lv.designPattern.builderpattern;

import org.elasticsearch.client.RequestOptions;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 测试
 * @date 2023/6/28 15:05:25
 */
public class BuilderPatternDemo {


    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " +vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("Non-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());


    }
}
