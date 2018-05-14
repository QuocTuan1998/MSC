package com.example.quoctuan.msc.PlayMusic;

import android.util.Log;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.Connect.DownloadJson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Nguyen Van Tung on 5/13/2018.
 */

public class PlusMusic {

    public void PlusMusic(int user_id, int music_id){
        List<HashMap<String, String>> attr = new ArrayList<>();

        HashMap<String, String> HmController = new HashMap<>();
        HmController.put("c", Common.LISTENING);
        HashMap<String, String> HmAcction = new HashMap<>();
        HmAcction.put("a", Common.PlusMusic);
        HashMap<String, String> HmUserID = new HashMap<>();
        HmUserID.put("user_id", String.valueOf(user_id));
        HashMap<String, String> HmMusicID = new HashMap<>();
        HmMusicID.put("baihat_id", String.valueOf(music_id));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        HashMap<String, String> HmDate = new HashMap<>();
        HmDate.put("ngay", "'" + dateFormat.format(date) + "'");

        attr.add(HmController);
        attr.add(HmAcction);
        attr.add(HmUserID);
        attr.add(HmMusicID);
        attr.add(HmDate);

        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute(Common.URL_API);
        try {
            Log.d("kiemtra", downloadJson.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
