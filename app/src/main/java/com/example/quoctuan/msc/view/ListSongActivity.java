package com.example.quoctuan.msc.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.quoctuan.msc.R;

public class ListSongActivity extends AppCompatActivity {
    private Toolbar list_song_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

        addControls();
        addEvents();
    }

    private void addControls() {
        list_song_toolbar = findViewById(R.id.list_song_toolbar);

        setSupportActionBar(list_song_toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_song_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);

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
