package com.example.quoctuan.msc.model.GetData;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.Connect.DownloadJson;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonMusic;
import com.example.quoctuan.msc.model.Songs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Nguyen Van Tung on 5/14/2018.
 */

public class GetMusicFromID {

    public Songs GetMusicFromID(int ID){
        Songs songs = null;

        List<HashMap<String, String>> attr = new ArrayList<>();
        HashMap<String, String> HmController = new HashMap<>();
        HmController.put("c", Common.SONG);
        HashMap<String, String> HmAction = new HashMap<>();
        HmAction.put("a", Common.INFOMUSIC);
        HashMap<String, String> HmID = new HashMap<>();
        HmID.put("id", String.valueOf(ID));

        attr.add(HmController);
        attr.add(HmAction);
        attr.add(HmID);
        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute(Common.URL_API);
        try {
            songs = new ParserJsonMusic().ParserJsonOneMusic(downloadJson.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return songs;
    }
}
