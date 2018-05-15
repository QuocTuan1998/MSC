package com.example.quoctuan.msc.model;

import android.net.Uri;

import java.util.Date;

/**
 * Created by BINH-PC on 2/3/2018.
 */

public class Albums {
    private int id;
    private int casi_id;
    private int theloai_id;
    private String anh;
    private Uri anh_album;
    private String ten;
    private Date ngay;
    private String casi;
    private int soBai;


    public Albums() {
    }

    public Albums(int id, int casi_id, int theloai_id, String anh, String ten, Date ngay) {
        this.id = id;
        this.casi_id = casi_id;
        this.theloai_id = theloai_id;
        this.anh = anh;
        this.ten = ten;
        this.ngay = ngay;
    }

    public Albums(int id, String casi, String ten, Uri anh_album, int soBai) {
        this.id = id;
        this.casi = casi;
        this.ten = ten;
        this.anh_album = anh_album;
        this.soBai = soBai;
    }

    public Uri getAnh_album() {
        return anh_album;
    }

    public void setAnh_album(Uri anh_album) {
        this.anh_album = anh_album;
    }

    public String getCasi() {
        return casi;
    }

    public void setCasi(String casi) {
        this.casi = casi;
    }

    public int getSoBai() {
        return soBai;
    }

    public void setSoBai(int soBai) {
        this.soBai = soBai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
}
