package com.example.quoctuan.msc.view.Splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.Connect.DownloadJson;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.GetData.GetListOffline;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonMusic;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonPlaylist;
import com.example.quoctuan.msc.model.PlayLists;
import com.example.quoctuan.msc.model.Songs;
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
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (checkPermissionREAD_EXTERNAL_STORAGE(this)){
            addControls();
            GetListOffline();
            GetTopFiveMusic();
            GetPlayList();
            GetAllMusic();
            Common.MEDIAPLAYER = new MediaPlayer();
        }


    }


    private void addControls() {
        image_splash = findViewById(R.id.image_splash);

        Animation animation_image = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);
        animation_image.setDuration(2000);
        image_splash.startAnimation(animation_image);
    }

    private void  GetListOffline() {
        GetListOffline getListMusic = new GetListOffline(this);
        getListMusic.GetListMusicOffline();
        getListMusic.GetListAlbum(this);
        getListMusic.GetListAlbumOffline();
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
        downloadJson.execute(Common.URL_API);

        try {
            Common.TopFiveMusic = new ParserJsonMusic().ParserJsonListMusic(downloadJson.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void GetAllMusic() {
        Common.MusicOnlines = new ArrayList<>();
        List<HashMap<String, String>> attr = new ArrayList<>();

        HashMap<String, String> HmControler = new HashMap<>();
        HmControler.put("c", "BaiHat");
        HashMap<String, String> HmAction = new HashMap<>();
        HmAction.put("a", "AllMusic");
        attr.add(HmControler);
        attr.add(HmAction);

        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute(Common.URL_API);

        try {
            Common.MusicOnlines = new ParserJsonMusic().ParserJsonListMusic(downloadJson.get());
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
        downloadJson.execute(Common.URL_API);

        try {
            Common.LISTPLAYLIST = new ParserJsonPlaylist().ParserJsonPlaylist(downloadJson.get());
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

                    startActivity(i_home);
                    finish();
                }
            }
        });
        thread.start();
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }
}
