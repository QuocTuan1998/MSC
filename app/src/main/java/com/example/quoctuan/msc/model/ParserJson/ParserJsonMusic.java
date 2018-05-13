package com.example.quoctuan.msc.model.ParserJson;

import android.util.Log;

import com.example.quoctuan.msc.model.Songs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Tung on 5/4/2018.
 */

public class ParserJsonMusic {
    List<Songs> listSong;

    public List<Songs> ParserJsonMusic(String dataJson){
        listSong = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("topfive");
            for (int i = 0; i < jsonArray.length(); i++){
                Songs song = new Songs();
                JSONObject jsonSong = jsonArray.getJSONObject(i);
                song.setId(jsonSong.getInt("id"));
                song.setCasi(jsonSong.getString("casi_id"));
                song.setTen(jsonSong.getString("ten"));
                song.setAnh(jsonSong.getString("anh"));
                song.setLink(jsonSong.getString("link"));
//                Log.d("kiemtra", jsonSong.getString("ten"));
                listSong.add(song);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listSong;
    }

}
