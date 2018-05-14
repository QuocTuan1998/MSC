package com.example.quoctuan.msc.model.ParserJson;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nguyen Van Tung on 5/13/2018.
 */

public class ParserJsonISFav {

    public boolean PaserJsonFav(String data){
        boolean is_fav = false;
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("is_yeuthich");
            JSONObject jsondata = jsonArray.getJSONObject(0);
            Log.d("kiemtra", jsondata.getString("message"));
            if (jsondata.getString("message").equals("true"))
                is_fav = true;
            else
                is_fav =  false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("kiemtra", is_fav + "");
        return is_fav;
    }

    public boolean PaserJsonSaveFAV(String data){
        boolean saved = false;

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("saveYeuThich");
            JSONObject jsondata = jsonArray.getJSONObject(0);
            Log.d("kiemtra", jsondata.getString("message"));
            if (jsondata.getString("message").equals("true"))
                saved = true;
            else
                saved =  false;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return saved;
    }

    public boolean ParserJsonUnFAV(String data){
        boolean unfav = false;

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("boyeuThich");
            JSONObject jsondata = jsonArray.getJSONObject(0);
            Log.d("kiemtra", jsondata.getString("message"));
            if (jsondata.getString("message").equals("true"))
                unfav = true;
            else
                unfav =  false;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return unfav;
    }
}
