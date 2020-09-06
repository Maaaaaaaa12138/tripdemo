package com.tripdemo.entity;


public class Message {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int fromId;
    private int type; // 消息状态 0-未读，1-已读
    private String fromAvatar;

    public Message() {
    }

    public Message(int id, int userId, String title, String content, int fromId, int type) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.fromId = fromId;
        this.type = type;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
