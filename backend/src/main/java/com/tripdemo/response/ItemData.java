package com.tripdemo.response;

public class ItemData {
    private String userInfo;
    private String itemInfo;
    private String indentNo;

    public ItemData() {
    }

    public ItemData(String userInfo, String itemInfo, String indentNo) {
        this.userInfo = userInfo;
        this.itemInfo = itemInfo;
        this.indentNo = indentNo;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo;
    }
}
