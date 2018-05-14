package com.example.quoctuan.msc.model.ParserJson;

import com.example.quoctuan.msc.model.PlayLists;
import com.example.quoctuan.msc.model.Songs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Tung on 5/5/2018.
 */

public class ParserJsonPlaylist {
    private List<PlayLists> playListsData;

    public List<PlayLists> ParserJsonPlaylist(String data) {
        playListsData = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PlayLists playLists = new PlayLists(
                      jsonObject.getInt("id"),
                      jsonObject.getInt("user_id"),
                      jsonObject.getString("ten"),
                      jsonObject.getString("anh")
                );
                playListsData.add(playLists);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return playListsData;
    }

    public List<Songs> ParserJsonGetListSongFromIDPlayList(String data){
        List<Songs> songsList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("playlist");
            for (int i = 0; i <jsonArray.length(); i++){
                JSONObject jsonData = jsonArray.getJSONObject(i);
                Songs songs = new Songs(
                        jsonData.getInt("id"),
                        jsonData.getString("casi_id"),
                        0, 0, 0, jsonData.getString("ten"),
                        jsonData.getString("anh"),
                        "",
                        jsonData.getString("link")
                );
                songsList.add(songs);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return songsList;
    }
}
