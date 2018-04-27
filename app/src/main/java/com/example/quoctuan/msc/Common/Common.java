package com.example.quoctuan.msc.Common;

import com.example.quoctuan.msc.model.Users;

/**
 * Created by BINH-PC on 2/3/2018.
 */

public class Common {

    // current
    public static Users CURRENT_USER;

    //Local of genymotion 10.0.3.2
    //Local of Android emulator 10.0.2.2
    public static final String SERVER_NAME = "http://10.0.2.2/";
    public static final String URL = SERVER_NAME + "php/mvc/"; // you cna change another location
    public static final String URL_API = URL + "api.php";

    //controller
    public static final String ALBUM = "album";
    public static final String AUTHOR = "tacgia";
    public static final String CATEGORY = "theloai";
    public static final String COMMENT = "binhluan";
    public static final String FAVORITE = "yeuthich";
    public static final String LISTENING = "luotnghe";
    public static final String PLAYLIST = "playlist";
    public static final String PLAYLIST_DETAIL = "chitietplaylist";
    public static final String SINGER = "casi";
    public static final String SONG = "baihat";
    public static final String USER = "user";

    //action
    public static final String INDEX = "index";
    public static final String LOGIN = "login";

    //DEFAULT DATA

}
