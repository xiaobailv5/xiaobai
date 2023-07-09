package com.example.lv.designPattern.builderpattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 套餐
 * @date 2023/6/28 14:58:56
 */
public class Meal {

    private static final Logger LOGGER = LoggerFactory.getLogger(Meal.class);

    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems(){
        for (Item item : items) {
            LOGGER.info("Item : "+item.name());
            LOGGER.info(", Packing : "+item.packing().pack());
            LOGGER.info(", Price : "+item.price());
        }
    }

}
