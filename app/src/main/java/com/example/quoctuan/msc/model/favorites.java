package com.example.quoctuan.msc.model;

public class favorites {
    private int id;
    private int baihat_id;
    private int user_id;
    private String ngay;

    public favorites() {
    }

    public favorites(int id, int baihat_id, int user_id, String ngay) {
        this.id = id;
        this.baihat_id = baihat_id;
        this.user_id = user_id;
        this.ngay = ngay;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getBaihat_id() { return baihat_id; }

    public void setBaihat_id(int baihat_id) { this.baihat_id = baihat_id; }

    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getNgay() { return ngay; }

    public void setNgay(String ngay) { this.ngay = ngay; }
}
