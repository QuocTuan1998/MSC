package com.example.quoctuan.msc.model.ParserJson;


import com.example.quoctuan.msc.model.Users;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nguyen Van Tung on 4/26/2018.
 */

public class ParserJsonLogin {

    public Users PaserJsonLogin(String json){
        Users user = new Users();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonUser = jsonObject.getJSONArray("user");
            JSONObject jsonObjectUser = jsonUser.getJSONObject(0);

            user.setId(Integer.parseInt(jsonObjectUser.getString("id")));
            JSONObject jsonObjectInfor = jsonObjectUser.getJSONObject("infor");
            user.setEmail(jsonObjectInfor.getString("email"));
            user.setPassword(jsonObjectInfor.getString("password"));
            if (jsonObjectUser.getString("role").equals("admin"))
                user.setRole(true);
            else user.setRole(false);

            if (jsonObjectUser.getString("status").equals("visible"))
                user.setStatus(true);
            else user.setStatus(false);

            if (!jsonObjectUser.getString("token").equals("null"))
                user.setToken(jsonObjectUser.getString("token"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
