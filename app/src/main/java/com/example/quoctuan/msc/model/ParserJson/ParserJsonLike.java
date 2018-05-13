package com.example.quoctuan.msc.model.ParserJson;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nguyen Van Tung on 5/13/2018.
 */

public class ParserJsonLike {

    public int PaserKsonLikeFromIDMusic(String data){
        int count_like = 0;

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("luotthich");
            JSONObject jsonData = jsonArray.getJSONObject(0);
            count_like = jsonData.getInt("luotthich");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return count_like;
    }
}
