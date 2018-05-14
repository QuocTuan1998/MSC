package com.example.quoctuan.msc.view.ListSong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quoctuan.msc.Adapter.ListSong.ListSongViewPagerAdapter;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.view.ListSong.Fragment.MusicFragment;
import com.example.quoctuan.msc.view.Main.MainActivity;
import com.example.quoctuan.msc.view.PlayMusic.PlayActivity;
import com.example.quoctuan.msc.view.PlayMusic.fragment.PlayFragment;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListSongActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar list_song_toolbar;
    private ViewPager list_song_viewpager;
    private TabLayout list_song_tablayout;
    private static CardView list_song_small_layout;
    private static CircleImageView list_song_small_img;
    private static TextView list_song_small_txt_name, list_song_small_txt_singer;
    private static ImageView list_song_small_btn_pre, list_song_small_btn_play, list_song_small_btn_next;

    private ListSongViewPagerAdapter listSongViewPagerAdapter;
    private boolean IS_SET_MARGIN_BOTTOM = false;
    public static boolean IS_BUTTON_PLAY = false; //false: dang nghe nhac, true: dang tat nhac
    private MediaMetadataRetriever mediaMetadataRetriever;
    private Animation small_layout_animation_img;

    private PlayMusic playMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

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
            list_song_small_btn_play.setImageLevel(0);
            IS_BUTTON_PLAY = false;
        }
        else{
            list_song_small_btn_play.setImageLevel(1);
            IS_BUTTON_PLAY = true;
        }
    }

    private void addControls() {
        playMusic = new PlayMusic();

        list_song_toolbar          = findViewById(R.id.list_song_toolbar);
        list_song_viewpager        = findViewById(R.id.list_song_viewpager);
        list_song_tablayout        = findViewById(R.id.list_song_tablayout);
        list_song_small_layout     = findViewById(R.id.list_song_small_layout);
        list_song_small_img        = findViewById(R.id.list_song_small_img);
        list_song_small_txt_name   = findViewById(R.id.list_song_small_txt_name);
        list_song_small_txt_singer = findViewById(R.id.list_song_small_txt_singer);
        list_song_small_btn_pre    = findViewById(R.id.list_song_small_btn_pre);
        list_song_small_btn_play   = findViewById(R.id.list_song_small_btn_play);
        list_song_small_btn_next   = findViewById(R.id.list_song_small_btn_next);

        setSupportActionBar(list_song_toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_song_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);

        listSongViewPagerAdapter = new ListSongViewPagerAdapter(getSupportFragmentManager());
        list_song_viewpager.setAdapter(listSongViewPagerAdapter);
        list_song_tablayout.setupWithViewPager(list_song_viewpager);

        small_layout_animation_img = AnimationUtils.loadAnimation(ListSongActivity.this, R.anim.rotate_img_small_layout);
        list_song_small_img.startAnimation(small_layout_animation_img);
    }

    private void addEvents() {
        list_song_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                new MainActivity().CheckPlayed();
            }
        });
        list_song_small_btn_pre.setOnClickListener(this);
        list_song_small_btn_play.setOnClickListener(this);
        list_song_small_btn_next.setOnClickListener(this);
        list_song_small_layout.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_music, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void ShowSmallMediaLayout(){
        if (Common.PLAYED_IS_ONLINE){
            ShowSmallLayoutOnline();
        }else {
            ShowSmallLayoutOffline();
        }
    }

    private void ShowSmallLayoutOffline() {
        if (!IS_SET_MARGIN_BOTTOM) SetPaddingBottom();
        mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(Common.MusicOfflines.get(Common.POSSITION_MUSIC_PLAYED).getLink());
        byte [] data = mediaMetadataRetriever.getEmbeddedPicture();
        if (data != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            list_song_small_img.setImageBitmap(bitmap);
        }else {
            list_song_small_img.setImageResource(R.drawable.music);
        }
        list_song_small_txt_name.setText(Common.MusicOfflines.get(Common.POSSITION_MUSIC_PLAYED).getTen());
        list_song_small_txt_singer.setText(Common.MusicOfflines.get(Common.POSSITION_MUSIC_PLAYED).getCasi());
        list_song_small_btn_play.setImageLevel(0);

    }

    private void ShowSmallLayoutOnline() {
        if (!IS_SET_MARGIN_BOTTOM) SetPaddingBottom();
        Picasso.get().load(Common.URL_IMG_SONG + Common.TopFiveMusic.get(Common.POSSITION_MUSIC_PLAYED).getAnh()).into(list_song_small_img);
        list_song_small_txt_name.setText(Common.TopFiveMusic.get(Common.POSSITION_MUSIC_PLAYED).getAnh());
        list_song_small_txt_singer.setText(Common.TopFiveMusic.get(Common.POSSITION_MUSIC_PLAYED).getCasi());
        list_song_small_btn_play.setImageLevel(0);
    }

    private void SetPaddingBottom() {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) list_song_small_layout.getLayoutParams();
        layoutParams.bottomMargin = 8;
        list_song_small_layout.setLayoutParams(layoutParams);
        //new MusicFragment().SetLayoutWhenPlaying();
    }

    public void PressButtonPlayPause(){
        if (Common.MEDIAPLAYER.isPlaying()){
            list_song_small_btn_play.setImageLevel(1);
            playMusic.PauseMusic();
            IS_BUTTON_PLAY = !IS_BUTTON_PLAY;
        }else {
            list_song_small_btn_play.setImageLevel(0);
            playMusic.StartMusic();
            IS_BUTTON_PLAY = !IS_BUTTON_PLAY;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MainActivity().CheckPlayed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.list_song_small_btn_pre:
                if (!Common.PLAYED_IS_ONLINE){
                    new PlayMusic().PreHandle(this);
                    CheckPlayed();
                }else {
                    Common.MEDIAPLAYER.seekTo(0);
                }
                break;

            case R.id.list_song_small_btn_play:
                PressButtonPlayPause();
                break;

            case R.id.list_song_small_btn_next:
                new PlayMusic().NextHandle(this);
                CheckPlayed();
                break;
            case R.id.list_song_small_layout:
//                Toast.makeText(this, "click!!", Toast.LENGTH_SHORT).show();
                Intent iPlay = new Intent(ListSongActivity.this, PlayActivity.class);
                startActivity(iPlay);
                break;
        }
    }

}
