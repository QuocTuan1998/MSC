package com.example.quoctuan.msc.view.Main;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quoctuan.msc.Adapter.Main.MainViewpagerAdapter;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout main_tablayout;
    private ViewPager main_viewpager;
    public static CardView main_small_meida_layout;
    public static CircleImageView content_play_img;
    public static TextView content_name, content_singer;
    public static ImageView content_btn_pause_play;
    private static ImageView content_btn_next, content_btn_prev;

    public static Animation small_media_animation;

    private MainViewpagerAdapter mainViewpagerAdapter;
    private MediaMetadataRetriever mediaMetadataRetriever;
    private static PlayMusic playMusic;
    private static Context context;

    public static boolean IS_SET_MARGIN_BOTTOM = false;
    public static boolean IS_BUTTON_PLAY = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
        CheckPlayed();
    }

    public void CheckPlayed() {
        if (Common.IS_PLAYED){
            if (Common.PLAYED_IS_ONLINE){
                ShowSmallLayoutOnline();
            }else {
                ShowSmallLayoutOffline();
            }
        }

        if (Common.MEDIAPLAYER.isPlaying()) {
            content_btn_pause_play.setImageLevel(0);
            IS_BUTTON_PLAY = false;
        }
        else{
            content_btn_pause_play.setImageLevel(1);
            IS_BUTTON_PLAY = true;
        }
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
        content_btn_next        = findViewById(R.id.content_btn_next);
        content_btn_prev        = findViewById(R.id.content_btn_prev);

        playMusic = new PlayMusic();
        context = this;
    }

    private void addEvents() {
        content_btn_pause_play.setOnClickListener(this);
        main_small_meida_layout.setOnClickListener(this);
        content_btn_next.setOnClickListener(this);
        content_btn_prev.setOnClickListener(this);
    }


    public void ShowSmallMediaLayout(){
        if (Common.PLAYED_IS_ONLINE){
            ShowSmallLayoutOnline();
        }else {
            ShowSmallLayoutOffline();
        }
    }

    public void ShowSmallLayoutOffline(){
        if (!IS_SET_MARGIN_BOTTOM) SetPaddingBottom();
//        mediaMetadataRetriever = new MediaMetadataRetriever();
//        mediaMetadataRetriever.setDataSource(Common.MusicOfflines.get(Common.POSSITION_MUSIC_PLAYED).getLink());
//        byte [] data = mediaMetadataRetriever.getEmbeddedPicture();
//        if (data != null){
//            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//            content_play_img.setImageBitmap(bitmap);
//        }else {
//                content_play_img.setImageResource(R.drawable.music);
//        }
        content_name.setText(Common.MusicOfflines.get(Common.POSSITION_MUSIC_PLAYED).getTen());
        content_singer.setText(Common.MusicOfflines.get(Common.POSSITION_MUSIC_PLAYED).getCasi());
        content_btn_pause_play.setImageLevel(0);
    }

    public void ShowSmallLayoutOnline(){
        if (!IS_SET_MARGIN_BOTTOM) SetPaddingBottom();
        Picasso.get().load(Common.URL_IMG_SONG + Common.TopFiveMusic.get(Common.POSSITION_MUSIC_PLAYED).getAnh()).into(content_play_img);
        content_name.setText(Common.TopFiveMusic.get(Common.POSSITION_MUSIC_PLAYED).getTen());
        content_singer.setText(Common.TopFiveMusic.get(Common.POSSITION_MUSIC_PLAYED).getCasi());
        content_btn_pause_play.setImageLevel(0);
    }

    public void SetPaddingBottom(){
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) main_small_meida_layout.getLayoutParams();
        layoutParams.bottomMargin = 8;
        main_small_meida_layout.setLayoutParams(layoutParams);
    }

    public void PressButtonPlayPause(){
        if (!Common.MEDIAPLAYER.isPlaying()){
            content_btn_pause_play.setImageLevel(1);
            playMusic.PauseMusic();
        }else {
            content_btn_pause_play.setImageLevel(0);
            playMusic.StartMusic();
        }
    }

    private void NextMusic() {
        if (!Common.PLAYED_IS_ONLINE){
            new PlayMusic().PlayMusic(this, Common.POSSITION_MUSIC_PLAYED ++,Common.MusicOfflines);
            Common.POSSITION_MUSIC_PLAYED++;
        }else Common.MEDIAPLAYER.seekTo(0);
    }

    private void PrevMusic() {
        if (!Common.PLAYED_IS_ONLINE){

        }else Common.MEDIAPLAYER.seekTo(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.content_btn_pause_play :
                PressButtonPlayPause();
                break;
            case R.id.main_small_meida_layout:
                break;
            case R.id.content_btn_next:
                NextMusic();
                break;
            case R.id.content_btn_prev:
                PrevMusic();
                break;
        }
    }

}
