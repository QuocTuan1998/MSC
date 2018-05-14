package com.example.quoctuan.msc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaMetadataRetriever;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quoctuan.msc.Adapter.ShowMusicOnlineAdapter;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.model.Songs;
import com.example.quoctuan.msc.view.Main.MainFragment.OfflineFragment;
import com.example.quoctuan.msc.view.Main.MainFragment.OnlineFragment;
import com.example.quoctuan.msc.view.PlayMusic.PlayActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowMusicOnlineActivity extends AppCompatActivity implements View.OnClickListener {
    private static RecyclerView show_music_recuclerview;
    private Toolbar show_music_toolbar;
    private ShowMusicOnlineAdapter showMusicOnlineAdapter;
    private static CircleImageView show_play_img;
    private static TextView show_name, show_singer;
    private static ImageView show_btn_prev,show_btn_pause_play, show_btn_next;
    private boolean IS_SET_MARGIN_BOTTOM = false;
    private static CardView show_small_meida_layout;
    private MediaMetadataRetriever mediaMetadataRetriever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_music);


        addControls();
        addEvents();
        CheckPlayed();
    }

    private void addControls() {
        show_music_recuclerview = findViewById(R.id.show_music_recuclerview);
        show_music_toolbar = findViewById(R.id.show_music_toolbar);
        show_music_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        show_play_img = findViewById(R.id.show_play_img);
        show_name      = findViewById(R.id.show_name);
        show_singer    = findViewById(R.id.show_singer);
        show_btn_prev = findViewById(R.id.show_btn_prev);
        show_btn_pause_play = findViewById(R.id.show_btn_pause_play);
        show_btn_next       = findViewById(R.id.show_btn_next);
        show_small_meida_layout = findViewById(R.id.show_small_meida_layout);

        initRecyclerView();
    }

    public void CheckPlayed() {
        if (Common.IS_PLAYED){
            if (Common.PLAYED_IS_ONLINE){
                ShowSmallLayoutOnline();
            }else {
                ShowSmallLayoutOffline();
            }
            new OnlineFragment().SetLayoutWhenPlaying();
            new OfflineFragment().SetLayoutWhenPlaying();
        }

        if (Common.MEDIAPLAYER.isPlaying()) {
            show_btn_pause_play.setImageLevel(0);
        }
        else{
            show_btn_pause_play.setImageLevel(1);
        }
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
        mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(Common.MusicOfflines.get(Common.POSSITION_MUSIC_PLAYED).getLink());
        byte [] data = mediaMetadataRetriever.getEmbeddedPicture();
        if (data != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            show_play_img.setImageBitmap(bitmap);
        }else {
            show_play_img.setImageResource(R.drawable.music);
        }
        show_name.setText(Common.MusicOfflines.get(Common.POSSITION_MUSIC_PLAYED).getTen());
        show_singer.setText(Common.MusicOfflines.get(Common.POSSITION_MUSIC_PLAYED).getCasi());
        show_btn_pause_play.setImageLevel(0);
    }

    public void ShowSmallLayoutOnline(){
        if (!IS_SET_MARGIN_BOTTOM) SetPaddingBottom();
        Picasso.get().load(Common.URL_IMG_SONG + Common.LISTSONGOFPLAYLIST.get
                (Common.POSSITION_MUSIC_PLAYED).getAnh()).into(show_play_img);
        show_name.setText(Common.LISTSONGOFPLAYLIST.get(Common.POSSITION_MUSIC_PLAYED).getTen());
        show_singer.setText(Common.LISTSONGOFPLAYLIST.get(Common.POSSITION_MUSIC_PLAYED).getCasi());
        show_btn_pause_play.setImageLevel(0);
    }

    public void SetPaddingBottom(){
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) show_small_meida_layout.getLayoutParams();
        layoutParams.bottomMargin = 8;
        show_small_meida_layout.setLayoutParams(layoutParams);
    }

    public void PressButtonPlayPause(){
        if (Common.MEDIAPLAYER.isPlaying()){
            show_btn_pause_play.setImageLevel(1);
            new PlayMusic().PauseMusic();
        }else {
            show_btn_pause_play.setImageLevel(0);
            new PlayMusic().StartMusic();
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this,LinearLayout.VERTICAL, false);
        show_music_recuclerview.setLayoutManager(linearLayout);
        show_music_recuclerview.setHasFixedSize(true);
        showMusicOnlineAdapter = new ShowMusicOnlineAdapter(this, Common.LISTSONGOFPLAYLIST);
        show_music_recuclerview.setAdapter(showMusicOnlineAdapter);
        showMusicOnlineAdapter.notifyDataSetChanged();
    }

    private void addEvents() {
        show_music_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        show_btn_prev.setOnClickListener(this);
        show_btn_pause_play.setOnClickListener(this);
        show_btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_btn_pause_play :
                PressButtonPlayPause();
                break;
            case R.id.show_small_meida_layout:
                Intent iPlay = new Intent(this, PlayActivity.class);
                startActivity(iPlay);
                break;
            case R.id.show_btn_next:
                new PlayMusic().NextHandle(this);
                CheckPlayed();
                break;
            case R.id.show_btn_prev:
                new PlayMusic().PreHandle(this);
                CheckPlayed();
                break;
        }
    }
}
