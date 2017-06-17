package com.example.bz0209.happyidiom.entity;

/**
 * Created by Administrator on 2017/5/23.
 */

public class Category {
    private String name;//类别名称
    private int imageId;//类别对应的图片
    public Category(String name, int imageId) {
        super();
        this.name = name;
        this.imageId = imageId;
    }
    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;
    }

}
