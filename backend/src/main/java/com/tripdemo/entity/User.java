package com.tripdemo.entity;

/**
 * 用户实体类
 */
public class User {
    private String username;
    private String password;
    private String email;
    private int id;
    private String phoneNumber;
    private String avatar; // 用户头像地址

    public User() {
    }

    public User(String username, String password, String email, int id, String phoneNumber, String avatar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
