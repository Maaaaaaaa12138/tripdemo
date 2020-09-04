package com.tripdemo.entity;

/**
 * 订单实体类
 */

public class Indent {
    private int userId; // foreign key reference(user.id)
    private int itemId; // foreign key reference(item.id)
    private int type; // 订单状态，1-未支付，2-已支付， 3-已完成
    private String indentId; // 订单号，每单唯一识别码

    public Indent(){

    }
    public Indent(int userId, int itemId, int type, String indentId) {
        this.userId = userId;
        this.itemId = itemId;
        this.type = type;
        this.indentId = indentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }
}
