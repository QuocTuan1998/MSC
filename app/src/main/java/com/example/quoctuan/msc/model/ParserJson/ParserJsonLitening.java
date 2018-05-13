package com.example.quoctuan.msc.model.ParserJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nguyen Van Tung on 5/13/2018.
 */

public class ParserJsonLitening {

    public int PasrserJsonCountListening(String json){
        int count = 0;

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("luotnghe");
            JSONObject jsondata = jsonArray.getJSONObject(0);
            count = jsondata.getInt("luotnghe");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return count;
    }
}
