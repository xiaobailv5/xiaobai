package com.example.lv.designPattern.builderpattern;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 套餐创造者
 * @date 2023/6/28 15:04:15
 */
public class MealBuilder {
    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
