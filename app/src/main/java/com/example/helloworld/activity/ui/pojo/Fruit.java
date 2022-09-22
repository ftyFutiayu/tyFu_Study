package com.example.helloworld.activity.ui.pojo;

/**
 * @Author: Tianyu.Fu
 * @CreateDate: 2022/8/11
 * @Description: This is Fruit
 **/
public class Fruit {
    private String fruitName;
    private int fruitImage;

    public Fruit(String fruitName, int fruitImage) {
        this.fruitName = fruitName;
        this.fruitImage = fruitImage;
    }

    public String getFruitName() {
        return fruitName;
    }

    public int getFruitImage() {
        return fruitImage;
    }
}