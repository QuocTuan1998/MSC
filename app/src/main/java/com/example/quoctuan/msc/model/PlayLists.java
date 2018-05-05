package com.example.quoctuan.msc.model;

import java.io.Serializable;

public class PlayLists implements Serializable {
    private int id;
    private int user_id;
    private String ten;
    private String anh;

    public PlayLists() {
    }

    public PlayLists(int id, int user_id, String ten, String anh) {
        this.id = id;
        this.user_id = user_id;
        this.ten = ten;
        this.anh = anh;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getTen() { return ten; }

    public void setTen(String ten) { this.ten = ten; }

    public String getAnh() { return anh; }

    public void setAnh(String anh) { this.anh = anh; }
}
