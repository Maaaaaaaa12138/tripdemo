package com.tripdemo.entity;

/**
 * Token实体类
 */
public class MyToken {
    private String content;
    private int userId;
    private long createTime;

    public MyToken() {
    }

    public MyToken(int userId, String content, long createTime) {
        this.content = content;
        this.userId = userId;
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
