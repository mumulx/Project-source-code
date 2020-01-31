package com.yanqun.entity;

public class Studentcard {
    private Short cardid;

    private String cardinfo;

    public Short getCardid() {
        return cardid;
    }

    public void setCardid(Short cardid) {
        this.cardid = cardid;
    }

    public String getCardinfo() {
        return cardinfo;
    }

    public void setCardinfo(String cardinfo) {
        this.cardinfo = cardinfo == null ? null : cardinfo.trim();
    }
}