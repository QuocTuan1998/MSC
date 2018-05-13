package com.example.quoctuan.msc.view.InfoMusic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.Connect.DownloadJson;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonISFav;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonLike;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonLitening;
import com.example.quoctuan.msc.model.Songs;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Van Tung on 5/13/2018.
 */

public class InfoMusicOnline extends AppCompatActivity implements View.OnClickListener {
    private int possition;
    private boolean fav = false;

    private CircleImageView dialog_info_song_img;
    private TextView dialog_info_listening, dialog_info_txt_count_like, dialog_info_song_name, dialog_info_singer;
    private ImageView dialog_info_fav;
    private ImageView dialog_info_img_background;
    private Toolbar dialog_info_toolbar;
    private LinearLayout dialog_info_layout_fav;
    private Songs song;

    private SharedPreferences sharedPreferences;
    private List<HashMap<String, String>> attr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_infor_music);

        Intent intent = getIntent();
        song = (Songs) intent.getSerializableExtra("song");

        sharedPreferences = getSharedPreferences("InforUser", MODE_PRIVATE);
        addControls();
        addEvents();
    }

    private void addControls() {
//        dialog_info_song_img = findViewById(R.id.dialog_info_song_img);
        dialog_info_listening = findViewById(R.id.dialog_info_listening);
        dialog_info_txt_count_like = findViewById(R.id.dialog_info_txt_count_like);
        dialog_info_song_name      = findViewById(R.id.dialog_info_song_name);
        dialog_info_singer         = findViewById(R.id.dialog_info_singer);
        dialog_info_fav            = findViewById(R.id.dialog_info_fav);
        dialog_info_img_background = findViewById(R.id.dialog_info_img_background);
        dialog_info_toolbar        = findViewById(R.id.dialog_info_toolbar);
        dialog_info_toolbar.setTitle("");
        dialog_info_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        dialog_info_layout_fav     = findViewById(R.id.dialog_info_layout_fav);

        dialog_info_song_name.setText(song.getTen());
        dialog_info_singer.setText(song.getCasi());
        Picasso.get().load(Common.URL_IMG_SONG + song.getAnh()).into(dialog_info_img_background);

        SetCountLike();
        setCountListening();
        SetIconFAV();
    }

    private void setCountListening() {
        attr = new ArrayList<>();
        HashMap<String, String> HmController = new HashMap<>();
        HmController.put("c" , Common.LISTENING);
        HashMap<String, String> HmAction = new HashMap<>();
        HmAction.put("a", Common.LISTENINGTOIDMUSIC);
        HashMap<String, String> HmID = new HashMap<>();
        HmID.put("id", song.getId() + "");

        attr.add(HmController);
        attr.add(HmAction);
        attr.add(HmID);
        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute(Common.URL_API);
        try {
            dialog_info_listening.setText(new ParserJsonLitening().PasrserJsonCountListening(downloadJson.get()) + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void SetCountLike() {
        attr = new ArrayList<>();
        HashMap<String, String> HmController = new HashMap<>();
        HmController.put("c", Common.FAVORITE);
        HashMap<String, String> HmAction = new HashMap<>();
        HmAction.put("a", Common.COUNFAV);
        HashMap<String, String> HmID = new HashMap<>();
        HmID.put("id", String.valueOf(song.getId()));

        attr.add(HmController);
        attr.add(HmAction);
        attr.add(HmID);

        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute(Common.URL_API);
        try {
            dialog_info_txt_count_like.setText(new ParserJsonLike().PaserKsonLikeFromIDMusic(downloadJson.get()) + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    private void SetIconFAV() {
        attr = new ArrayList<>();
        HashMap<String, String> HmController = new HashMap<>();
        HmController.put("c", Common.FAVORITE);
        HashMap<String, String> HmAction = new HashMap<>();
        HmAction.put("a", Common.CHECKISFAV);
        HashMap<String, String> HmIDMusic = new HashMap<>();
        HmIDMusic.put("baihat_id", String.valueOf(song.getId()));
        //HmIDMusic.put("baihat_id", "26");
        HashMap<String, String> HmIDUser = new HashMap<>();
        HmIDUser.put("user_id", String.valueOf(sharedPreferences.getInt("id", 0)));
        Log.d("kiemtra", String.valueOf(sharedPreferences.getInt("id", 0)) + "---" + String.valueOf(song.getId()));

        //HmIDUser.put("user_id", "17");


        attr.add(HmController);
        attr.add(HmAction);
        attr.add(HmIDMusic);
        attr.add(HmIDUser);
        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute(Common.URL_API);

        try {
            if (new ParserJsonISFav().PaserJsonFav(downloadJson.get())) {
                dialog_info_fav.setImageLevel(0);
                fav = true;
            }
            else{
                dialog_info_fav.setImageLevel(1);
                fav = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void addEvents() {
        dialog_info_layout_fav.setOnClickListener(this);
        dialog_info_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void FavPress() {
        if (fav){
            UnFav();
        }else {
            Fav();
        }
    }

    private void UnFav() {
        attr = new ArrayList<>();
        HashMap<String, String> HmController = new HashMap<>();
        HmController.put("c", Common.FAVORITE);
        HashMap<String, String> HmAction = new HashMap<>();
        HmAction.put("a", Common.UNFAV);
        HashMap<String, String> HmIDMusic = new HashMap<>();
        HmIDMusic.put("baihat_id", song.getId() + "");
        HashMap<String, String> HmIDFAV = new HashMap<>();
        HmIDFAV.put("user_id", String.valueOf(sharedPreferences.getInt("id", 0)));
//        HmIDFAV.put("user_id", "17");


        attr.add(HmController);
        attr.add(HmAction);
        attr.add(HmIDMusic);
        attr.add(HmIDFAV);

        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute(Common.URL_API);

        try {
            if (!(new ParserJsonISFav().PaserJsonSaveFAV(downloadJson.get())))
            {
                Toast.makeText(this, "Bo save", Toast.LENGTH_SHORT).show();
                dialog_info_fav.setImageLevel(1);
                dialog_info_txt_count_like.setText(
                        (Integer.parseInt(dialog_info_txt_count_like.getText().toString()) - 1) + "");
                fav = false;
            }else {
                Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void Fav() {
        attr = new ArrayList<>();
        HashMap<String, String> HmController = new HashMap<>();
        HmController.put("c", Common.FAVORITE);
        HashMap<String, String> HmAction = new HashMap<>();
        HmAction.put("a", Common.SAVEFAV);
        HashMap<String, String> HmIDMusic = new HashMap<>();
        HmIDMusic.put("baihat_id", song.getId() + "");
        HashMap<String, String> HmIDFAV = new HashMap<>();
        HmIDFAV.put("user_id", String.valueOf(sharedPreferences.getInt("id", 0)));
//        HmIDFAV.put("user_id", "17");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        HashMap<String, String> HmDate = new HashMap<>();
        HmDate.put("ngay", dateFormat.format(date));

        attr.add(HmController);
        attr.add(HmAction);
        attr.add(HmIDMusic);
        attr.add(HmIDFAV);
        attr.add(HmDate);

        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute(Common.URL_API);

        try {
            if (new ParserJsonISFav().PaserJsonSaveFAV(downloadJson.get()))
            {
                Toast.makeText(this, "Save Yeu thich", Toast.LENGTH_SHORT).show();
                dialog_info_fav.setImageLevel(0);
                dialog_info_txt_count_like.setText(
                        (Integer.parseInt(dialog_info_txt_count_like.getText().toString()) + 1) + "");
                fav = true;
            }else {
                Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dialog_info_layout_fav:
                FavPress();
                break;
        }
    }

}
