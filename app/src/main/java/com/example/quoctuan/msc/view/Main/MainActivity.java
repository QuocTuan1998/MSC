package com.example.quoctuan.msc.view.Main;

import android.media.MediaPlayer;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quoctuan.msc.Adapter.Main.MainViewpagerAdapter;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.view.Main.MainFragment.OnlineFragment;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout main_tablayout;
    private ViewPager main_viewpager;
    public static CardView main_small_meida_layout;
    public static CircleImageView content_play_img;
    public static TextView content_name, content_singer;
    public static ImageView content_btn_pause_play;

    public static Animation small_media_animation;

    private MainViewpagerAdapter mainViewpagerAdapter;
    private static PlayMusic playMusic;

    public static boolean IS_SET_MARGIN_BOTTOM = false;
    public static boolean IS_BUTTON_PLAY = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        main_tablayout = findViewById(R.id.main_tablayout);
        //viewpager
        main_viewpager = findViewById(R.id.main_viewpager);
        mainViewpagerAdapter = new MainViewpagerAdapter(getSupportFragmentManager());
        main_viewpager.setAdapter(mainViewpagerAdapter);
        main_tablayout.setupWithViewPager(main_viewpager);

        main_small_meida_layout = findViewById(R.id.main_small_meida_layout);

        //img in small layout
        content_play_img        = findViewById(R.id.content_play_img);
        small_media_animation   = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_img_small_layout);
        content_play_img.startAnimation(small_media_animation);
        content_name            = findViewById(R.id.content_name);
        content_singer          = findViewById(R.id.content_singer);
        content_btn_pause_play  = findViewById(R.id.content_btn_pause_play);

        playMusic = new PlayMusic();
    }

    private void addEvents() {
        content_btn_pause_play.setOnClickListener(this);
    }


    public void ShowSmallMediaLayout(int possition){
        if (!IS_SET_MARGIN_BOTTOM) SetPaddingBottom();
        Picasso.get().load(Common.URL_IMG_SONG + Common.TopFiveMusic.get(possition).getAnh()).into(content_play_img);
        content_name.setText(Common.TopFiveMusic.get(possition).getAnh());
        content_singer.setText(Common.TopFiveMusic.get(possition).getCasi());
        content_btn_pause_play.setImageLevel(0);
    }
    public void SetPaddingBottom(){
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) main_small_meida_layout.getLayoutParams();
        layoutParams.bottomMargin = 8;
        main_small_meida_layout.setLayoutParams(layoutParams);
    }

    public void PressButtonPlayPause(){
        if (!IS_BUTTON_PLAY){
            content_btn_pause_play.setImageLevel(1);
            playMusic.PauseMusic();
            IS_BUTTON_PLAY = !IS_BUTTON_PLAY;
        }else {
            content_btn_pause_play.setImageLevel(0);
            playMusic.StartMusic();
            IS_BUTTON_PLAY = !IS_BUTTON_PLAY;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.content_btn_pause_play :
                PressButtonPlayPause();
                break;
        }
    }
}
