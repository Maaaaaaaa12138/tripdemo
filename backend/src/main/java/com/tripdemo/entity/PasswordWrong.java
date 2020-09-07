package com.tripdemo.entity;

public class PasswordWrong {
    private int id;
    private int user;
    private int wrongTimes;
    private int updateTime;

    public PasswordWrong() {
    }

    public PasswordWrong(int id, int user, int wrongTimes, int updateTime) {
        this.id = id;
        this.user = user;
        this.wrongTimes = wrongTimes;
        this.updateTime = updateTime;
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

    public int getWrongTimes() {
        return wrongTimes;
    }

    public void setWrongTimes(int wrongTimes) {
        this.wrongTimes = wrongTimes;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }
}
