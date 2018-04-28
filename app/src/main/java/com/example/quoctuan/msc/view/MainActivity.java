package com.example.quoctuan.msc.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.quoctuan.msc.Adapter.MainViewpagerAdapter;
import com.example.quoctuan.msc.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout main_tablayout;
    private ViewPager main_viewpager;

    private MainViewpagerAdapter mainViewpagerAdapter;

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


    }

    private void addEvents() {

    }
}
