package com.example.quoctuan.msc.model;

public class Comments {
    private int id;
    private int baihat_id;
    private int user_id;
    private String noi_dung;

    public Comments() {
    }

    public Comments(int id, int baihat_id, int user_id, String noi_dung) {
        this.id = id;
        this.baihat_id = baihat_id;
        this.user_id = user_id;
        this.noi_dung = noi_dung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaihat_id() {
        return baihat_id;
    }

    public void setBaihat_id(int baihat_id) {
        this.baihat_id = baihat_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }
}
