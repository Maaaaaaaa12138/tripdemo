package com.tripdemo.entity;

public class Favour {
    private int id;
    private int item;
    private int user;
    private int createTime;

    public Favour() {
    }

    public Favour(int id, int item, int user, int createTime) {
        this.id = id;
        this.item = item;
        this.user = user;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }
}
