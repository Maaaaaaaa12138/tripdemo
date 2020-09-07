package com.tripdemo.entity;

public class Commit {
    private int id;
    private int user;
    private int item;
    private int createTime;
    private String content;
    private String userAvatar;

    public Commit() {
    }

    public Commit(int id, int user, int item, int createTime, String content, String userAvatar) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.createTime = createTime;
        this.content = content;
        this.userAvatar = userAvatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
