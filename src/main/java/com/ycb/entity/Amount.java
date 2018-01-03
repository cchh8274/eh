package com.ycb.entity;

public class Amount {
    private Integer id;

    private String money;

    private String uid;

    private String ken;

    private String dlen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getKen() {
        return ken;
    }

    public void setKen(String ken) {
        this.ken = ken == null ? null : ken.trim();
    }

    public String getDlen() {
        return dlen;
    }

    public void setDlen(String dlen) {
        this.dlen = dlen == null ? null : dlen.trim();
    }
}