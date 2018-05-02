package com.example.quoctuan.msc.view.ListSong;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.quoctuan.msc.Adapter.ListSong.ListSongViewPagerAdapter;
import com.example.quoctuan.msc.R;

public class ListSongActivity extends AppCompatActivity {
    private Toolbar list_song_toolbar;
    private ViewPager list_song_viewpager;
    private TabLayout list_song_tablayout;

    private ListSongViewPagerAdapter listSongViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

        addControls();
        addEvents();
    }

    private void addControls() {
        list_song_toolbar    = findViewById(R.id.list_song_toolbar);
        list_song_viewpager  = findViewById(R.id.list_song_viewpager);
        list_song_tablayout  = findViewById(R.id.list_song_tablayout);

        setSupportActionBar(list_song_toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_song_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);

        listSongViewPagerAdapter = new ListSongViewPagerAdapter(getSupportFragmentManager());
        list_song_viewpager.setAdapter(listSongViewPagerAdapter);
        list_song_tablayout.setupWithViewPager(list_song_viewpager);
    }

    private void addEvents() {
        list_song_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_music, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
