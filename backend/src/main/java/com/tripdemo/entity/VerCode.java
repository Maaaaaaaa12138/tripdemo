package com.tripdemo.entity;

/**
 * 验证码实体类
 */
public class VerCode {
    private String content;
    private String email;
    private long createTime; // 创建时间， 单位：秒

    public VerCode() {
    }

    public VerCode(String content, String email, long createTime) {
        this.content = content;
        this.email = email;
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
