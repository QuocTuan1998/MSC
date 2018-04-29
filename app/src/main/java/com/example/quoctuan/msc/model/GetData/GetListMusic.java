package com.example.quoctuan.msc.model.GetData;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.quoctuan.msc.model.Songs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Tung on 4/29/2018.
 */

public class GetListMusic {
    private List<Songs> arrayList;
    private Activity activity;

    public GetListMusic(Activity activity){
        this.activity = activity;
    }

    public List<Songs> GetListMusic(){
        arrayList = new ArrayList<>();
        ContentResolver contentResolver = activity.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " !=0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = contentResolver.query(uri, null, selection, null , sortOrder);

        if (cursor != null){
            int count = cursor.getCount();
            Log.d("count", count + "");
            if (count > 0){
                while (cursor.moveToNext()){
                    int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                    String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    arrayList.add(new Songs(id,artist,0,0,0,title,"","",data));
                }
            }
        }

        return arrayList;
    }
}
