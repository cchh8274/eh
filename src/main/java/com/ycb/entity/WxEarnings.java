package com.ycb.entity;

import java.util.Date;

public class WxEarnings {
    private Integer id;

    private String openid;

    private String earingmoney;

    private Date estarttime;

    private String earingmonth;

    private String isStatus;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getEaringmoney() {
        return earingmoney;
    }

    public void setEaringmoney(String earingmoney) {
        this.earingmoney = earingmoney == null ? null : earingmoney.trim();
    }

    public Date getEstarttime() {
        return estarttime;
    }

    public void setEstarttime(Date estarttime) {
        this.estarttime = estarttime;
    }

    public String getEaringmonth() {
        return earingmonth;
    }

    public void setEaringmonth(String earingmonth) {
        this.earingmonth = earingmonth == null ? null : earingmonth.trim();
    }

    public String getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(String isStatus) {
        this.isStatus = isStatus == null ? null : isStatus.trim();
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
        WxEarnings other = (WxEarnings) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getEaringmoney() == null ? other.getEaringmoney() == null : this.getEaringmoney().equals(other.getEaringmoney()))
            && (this.getEstarttime() == null ? other.getEstarttime() == null : this.getEstarttime().equals(other.getEstarttime()))
            && (this.getEaringmonth() == null ? other.getEaringmonth() == null : this.getEaringmonth().equals(other.getEaringmonth()))
            && (this.getIsStatus() == null ? other.getIsStatus() == null : this.getIsStatus().equals(other.getIsStatus()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getEaringmoney() == null) ? 0 : getEaringmoney().hashCode());
        result = prime * result + ((getEstarttime() == null) ? 0 : getEstarttime().hashCode());
        result = prime * result + ((getEaringmonth() == null) ? 0 : getEaringmonth().hashCode());
        result = prime * result + ((getIsStatus() == null) ? 0 : getIsStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }
}