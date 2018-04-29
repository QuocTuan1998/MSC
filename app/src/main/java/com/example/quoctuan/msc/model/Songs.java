package com.example.quoctuan.msc.model;

/**
 * Created by BINH-PC on 2/3/2018.
 */

public class Songs {
    private int id;
    private int casi_id;
    private int album_id;
    private int theloai_id;
    private int tacgia_id;
    private String ten;
    private String anh;
    private String loi_bai_hat;
    private String link;

    public Songs() {
    }

    public Songs(int id, int casi_id, int album_id, int theloai_id, int tacgia_id, String ten, String anh, String loi_bai_hat, String link) {
        this.id = id;
        this.casi_id = casi_id;
        this.album_id = album_id;
        this.theloai_id = theloai_id;
        this.tacgia_id = tacgia_id;
        this.ten = ten;
        this.anh = anh;
        this.loi_bai_hat = loi_bai_hat;
        this.link = link;
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

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public int getTheloai_id() {
        return theloai_id;
    }

    public void setTheloai_id(int theloai_id) {
        this.theloai_id = theloai_id;
    }

    public int getTacgia_id() {
        return tacgia_id;
    }

    public void setTacgia_id(int tacgia_id) {
        this.tacgia_id = tacgia_id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getLoi_bai_hat() {
        return loi_bai_hat;
    }

    public void setLoi_bai_hat(String loi_bai_hat) {
        this.loi_bai_hat = loi_bai_hat;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
