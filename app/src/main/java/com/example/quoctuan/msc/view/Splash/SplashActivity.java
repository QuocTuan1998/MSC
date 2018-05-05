package com.example.quoctuan.msc.view.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.quoctuan.msc.Connect.DownloadJson;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonMusic;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonPlaylist;
import com.example.quoctuan.msc.model.PlayLists;
import com.example.quoctuan.msc.model.Songs;
import com.example.quoctuan.msc.view.Home.HomeActivity;
import com.example.quoctuan.msc.view.Main.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SplashActivity extends AppCompatActivity {
    private ImageView image_splash;
    private List<Songs> listSongData;
    private List<PlayLists> listPlayLlistData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        addControls();
        GetTopFiveMusic();
        GetPlayList();
    }


    private void addControls() {
        image_splash = findViewById(R.id.image_splash);

        Animation animation_image = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);
        animation_image.setDuration(2000);
        image_splash.startAnimation(animation_image);
    }



    private void GetTopFiveMusic() {
        listSongData = new ArrayList<>();
        List<HashMap<String, String>> attr = new ArrayList<>();

        HashMap<String, String> HmControler = new HashMap<>();
        HmControler.put("c", "BaiHat");
        HashMap<String, String> HmAction = new HashMap<>();
        HmAction.put("a", "topFiveMusic");
        attr.add(HmControler);
        attr.add(HmAction);

        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute("http://192.168.1.8/mvc/api.php");

        try {
            listSongData = new ParserJsonMusic().ParserJsonMusic(downloadJson.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void GetPlayList() {
        listPlayLlistData = new ArrayList<>();
        List<HashMap<String, String>> attr = new ArrayList<>();

        HashMap<String, String> HmControler = new HashMap<>();
        HmControler.put("c", "playlist");
        attr.add(HmControler);

        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute("http://192.168.1.8/mvc/api.php");

        try {
            listPlayLlistData = new ParserJsonPlaylist().ParserJsonPlaylist(downloadJson.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        NextPage();
    }

    private void NextPage() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent i_home = new Intent(SplashActivity.this, MainActivity.class);
                    i_home.putExtra("TopFive", (Serializable) listSongData);
                    i_home.putExtra("Playlist", (Serializable) listPlayLlistData);
                    startActivity(i_home);
                }
            }
        });
        thread.start();
    }
}
