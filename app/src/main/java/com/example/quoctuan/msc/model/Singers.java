package com.example.quoctuan.msc.model;

/**
 * Created by BINH-PC on 2/3/2018.
 */

public class Singers {
    private int id;
    private String ten;
    private String thong_tin;
    private String anh;

    public Singers() {
    }

    public Singers(int id, String ten, String thong_tin, String anh) {
        this.id = id;
        this.ten = ten;
        this.thong_tin = thong_tin;
        this.anh = anh;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTen() { return ten; }

    public void setTen(String ten) { this.ten = ten; }

    public String getThong_tin() { return thong_tin; }

    public void setThong_tin(String thong_tin) { this.thong_tin = thong_tin; }

    public String getAnh() { return anh; }

    public void setAnh(String anh) { this.anh = anh; }
}
