package com.ycb.entity;

public class Machine {
    private Integer id;

    private String name;

    private String adress;

    private String mid;

    private String isuse;

    private String price;

    private String aplces;

    private String laplces;

    private String rplces;

    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse == null ? null : isuse.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getAplces() {
        return aplces;
    }

    public void setAplces(String aplces) {
        this.aplces = aplces == null ? null : aplces.trim();
    }

    public String getLaplces() {
        return laplces;
    }

    public void setLaplces(String laplces) {
        this.laplces = laplces == null ? null : laplces.trim();
    }

    public String getRplces() {
        return rplces;
    }

    public void setRplces(String rplces) {
        this.rplces = rplces == null ? null : rplces.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }
}