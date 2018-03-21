package com.ycb.entity;

import java.util.Date;

public class TotalAmount {
    private Integer id;

    private Integer money;

    private String openid;

    private Integer leftmoney;

    private Integer txmoney;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getLeftmoney() {
        return leftmoney;
    }

    public void setLeftmoney(Integer leftmoney) {
        this.leftmoney = leftmoney;
    }

    public Integer getTxmoney() {
        return txmoney;
    }

    public void setTxmoney(Integer txmoney) {
        this.txmoney = txmoney;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TotalAmount other = (TotalAmount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getLeftmoney() == null ? other.getLeftmoney() == null : this.getLeftmoney().equals(other.getLeftmoney()))
            && (this.getTxmoney() == null ? other.getTxmoney() == null : this.getTxmoney().equals(other.getTxmoney()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getLeftmoney() == null) ? 0 : getLeftmoney().hashCode());
        result = prime * result + ((getTxmoney() == null) ? 0 : getTxmoney().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }
}