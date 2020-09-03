package com.tripdemo.entity;

/**
 * 项目实体类
 */
public class Item {
    private int id;
    private String name;
    private String type; // 项目类型，自营、团购等
    private String imageUrl; // 项目图片url
    private int originalCost; //项目原价
    private int currentCost; // 项目现价
    private String detail; // 项目细节介绍
    private String location; // 项目地点（省）

    public Item() {
    }

    public Item(int id, String name, String type, String imageUrl, int originalCost, int currentCost, String detail, String location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageUrl = imageUrl;
        this.originalCost = originalCost;
        this.currentCost = currentCost;
        this.detail = detail;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(int originalCost) {
        this.originalCost = originalCost;
    }

    public int getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(int currentCost) {
        this.currentCost = currentCost;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
