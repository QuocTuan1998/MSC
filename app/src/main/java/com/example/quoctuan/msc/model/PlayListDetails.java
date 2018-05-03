package com.example.quoctuan.msc.model;

public class PlayListDetails {
    private int id;
    private int playlist_id;
    private int baihat_id;

    public PlayListDetails() {
    }

    public PlayListDetails(int id, int playlist_id, int baihat_id) {
        this.id = id;
        this.playlist_id = playlist_id;
        this.baihat_id = baihat_id;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getPlaylist_id() { return playlist_id; }

    public void setPlaylist_id(int playlist_id) { this.playlist_id = playlist_id; }

    public int getBaihat_id() { return baihat_id; }

    public void setBaihat_id(int baihat_id) { this.baihat_id = baihat_id; }
}
