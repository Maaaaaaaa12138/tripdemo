package com.tripdemo.entity;

public class LoginData {
    private int id; // 用户id
    private String token; // 登录时生成的token

    public LoginData() {
    }

    public LoginData(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
