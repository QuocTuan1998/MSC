package com.example.quoctuan.msc.model.GetData;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.model.Albums;
import com.example.quoctuan.msc.model.Songs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Van Tung on 4/29/2018.
 */

public class GetListOffline {
    private Activity activity;
    private Cursor cursor;

    public GetListOffline(Activity activity){
        this.activity = activity;
        ContentResolver contentResolver = activity.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " !=0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        cursor = contentResolver.query(uri, null, selection, null , sortOrder);
    }

    public void GetListAlbum(Activity activity){

        this.activity = activity;
        ContentResolver contentResolver = activity.getContentResolver();
        String[] projection = new String[] { MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.ALBUM_ART,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS };

        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        String selection = null;
        String sortOrder = MediaStore.Audio.Media.ALBUM + " ASC";
        cursor = contentResolver.query(uri, projection, selection, null , sortOrder);
    }

    public void GetListMusicOffline(){
        Common.MusicOfflines = new ArrayList<>();
        if (cursor != null){
            int count = cursor.getCount();
            if (count > 0){
                while (cursor.moveToNext()){
                    int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                    String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    Common.MusicOfflines.add(new Songs(id,artist,0,0,0,title,"","",data));
                }
            }
        }
    }


    public void GetListAlbumOffline() {
        Common.AlbumOfflines = new ArrayList<>();
        if (cursor != null) {
            int count = cursor.getCount();
            Log.d("cusor",count+"");
            if (count > 0) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Albums._ID));

                    String casi = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST));

                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM));

                    String anh = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));

                    int album_song = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS));

                    Common.AlbumOfflines.add(new Albums(id,casi,title,anh,album_song));
                }
            }
        }

    }


}
