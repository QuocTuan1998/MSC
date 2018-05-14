package com.example.quoctuan.msc.view.PlayMusic;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.quoctuan.msc.Adapter.Main.MainViewpagerAdapter;
import com.example.quoctuan.msc.Adapter.Play.PlayViewpagerAdapter;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayActivity extends AppCompatActivity{

    private ViewPager play_viewpager;
    private PlayViewpagerAdapter playViewpagerAdapter;
    private TextView play_toolbar_title, play_singer;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        addControls();
        addData();
        addEvents();
    }

    private void addControls() {

        //viewpager
        play_viewpager = findViewById(R.id.play_viewpager);
        playViewpagerAdapter = new PlayViewpagerAdapter(getSupportFragmentManager());
        play_viewpager.setAdapter(playViewpagerAdapter);
        play_toolbar_title = findViewById(R.id.play_toolbar_title);
        play_toolbar_title.setText(Common.SONGPLAYED.getTen());
        play_singer         = findViewById(R.id.play_singer);
        play_singer.setText(Common.SONGPLAYED.getCasi());
        toolbar = findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);



        play_viewpager.setCurrentItem(1);
    }


    private void addData() {

    }

    private void addEvents() {

    }

}
