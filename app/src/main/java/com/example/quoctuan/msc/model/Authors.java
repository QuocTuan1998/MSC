package com.example.quoctuan.msc.model;

public class Authors {
    private int id;
    private String ten;
    private String anh;
    private String thongtin;

    public Authors() {
    }

    public Authors(int id, String ten, String anh, String thongtin) {
        this.id = id;
        this.ten = ten;
        this.anh = anh;
        this.thongtin = thongtin;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTen() { return ten; }

    public void setTen(String ten) { this.ten = ten; }

    public String getAnh() { return anh; }

    public void setAnh(String anh) { this.anh = anh; }

    public String getThongtin() { return thongtin; }

    public void setThongtin(String thongtin) { this.thongtin = thongtin; }
}

