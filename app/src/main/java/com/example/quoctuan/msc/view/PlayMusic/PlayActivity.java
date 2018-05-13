package com.example.quoctuan.msc.view.PlayMusic;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quoctuan.msc.Adapter.Main.MainViewpagerAdapter;
import com.example.quoctuan.msc.Adapter.Play.PlayViewpagerAdapter;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayActivity extends AppCompatActivity {

    private TabLayout play_tablayout;
    private ViewPager play_viewpager;
    private PlayViewpagerAdapter playViewpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        addControls();
        addEvents();
    }



    private void addControls() {

        play_tablayout = findViewById(R.id.play_tablayout);
        //viewpager
        play_viewpager = findViewById(R.id.play_viewpager);
        playViewpagerAdapter = new PlayViewpagerAdapter(getSupportFragmentManager());
        play_viewpager.setAdapter(playViewpagerAdapter);
        play_tablayout.setupWithViewPager(play_viewpager);




    }

    private void addEvents() {

    }
}
