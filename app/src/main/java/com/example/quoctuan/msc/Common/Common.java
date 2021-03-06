package com.example.quoctuan.msc.Common;

import android.media.MediaPlayer;

import com.example.quoctuan.msc.model.Albums;
import com.example.quoctuan.msc.model.PlayLists;
import com.example.quoctuan.msc.model.Songs;
import com.example.quoctuan.msc.model.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BINH-PC on 2/3/2018.
 */

public class Common {

    // current
    public static Users CURRENT_USER;
    //10.15.215.36
    //Local of genymotion 10.0.3.2
    //Local of Android emulator 10.0.2.2
    public static final String SERVER_NAME = "http://10.0.2.2/";
    public static final String URL = SERVER_NAME + "php/mvc/"; // you cna change another location

    public static final String URL_API = URL + "api.php";
    public static final String URL_IMG_SONG = URL + "public/img/songs/";
    public static final String URL_LINK_SONG = URL + "public/music/";
    public static final String URL_IMG_PLAYLIST = URL + "public/img/playlists/";

    public static boolean IS_PLAYED = false;
    public static boolean PLAYED_IS_ONLINE = false;
    public static int POSSITION_MUSIC_PLAYED = 0;
    public static Songs SONGPLAYED;


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
    public static final String LISTENINGTOIDMUSIC = "LuotNgheTheoIDBaiHat";
    public static final String COUNFAV = "CountYeuThich";
    public static final String CHECKISFAV = "check_yeuthich_exists";
    public static final String SAVEFAV = "saveYeuThich";
    public static final String UNFAV = "boyeuThich";
    public static final String NEWPASSWORD = "newPassword";
    public static final String PlusMusic = "PlusMusic";
    public static final String INFOMUSIC = "InfoMusic";
    public static final String FINDSONGFROMIDPLAYLIST = "findSongsFromPlaylist";


    //DEFAULT DATA
    public static List<Songs> TopFiveMusic;
    public static MediaPlayer MEDIAPLAYER;
    public static List<Songs> MusicOfflines;
    public static List<Albums> AlbumOfflines;
    public static List<PlayLists> LISTPLAYLIST;
    public static List<Songs> MusicOnlines;
    public static List<Songs> LISTSONGOFPLAYLIST;


}
