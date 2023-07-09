package com.example.lv.designPattern.prototypepattern;

import java.util.Hashtable;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 缓存
 * @date 2023/7/7 15:08:17
 */
public class AnimalCache {

    private static Hashtable<String, Animal> animalMap
            = new Hashtable<String, Animal>();

    public static Animal getAnimal(String animalId) {

        Animal cachedAnimal = animalMap.get(animalId);

        return (Animal) cachedAnimal.clone();
    }

    /**
     * 对每种动物都运行数据库查询，创建该动物
     */
    public static void loadCache() {
        Dog dog = new Dog();
        dog.setId("1");
        animalMap.put(dog.getId(),dog);

        Cat cat = new Cat();
        cat.setId("2");
        animalMap.put(cat.getId(),cat);

        Bird bird = new Bird();
        bird.setId("3");
        animalMap.put(bird.getId(),bird);
    }
}
