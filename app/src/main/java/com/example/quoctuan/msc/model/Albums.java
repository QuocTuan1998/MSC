package com.example.quoctuan.msc.model;

/**
 * Created by BINH-PC on 2/3/2018.
 */

public class Albums {
    private int id;
    private String anh;
    private String ten;
    private int casi_id;
    private int theloai_id;

    public Albums() {
    }

    public Albums(int id, String anh, String ten, int casi_id, int theloai_id) {
        this.id = id;
        this.anh = anh;
        this.ten = ten;
        this.casi_id = casi_id;
        this.theloai_id = theloai_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getCasi_id() {
        return casi_id;
    }

    public void setCasi_id(int casi_id) {
        this.casi_id = casi_id;
    }

    public int getTheloai_id() {
        return theloai_id;
    }

    public void setTheloai_id(int theloai_id) {
        this.theloai_id = theloai_id;
    }
}
