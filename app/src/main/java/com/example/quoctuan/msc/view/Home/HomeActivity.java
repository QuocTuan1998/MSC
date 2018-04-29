package com.example.quoctuan.msc.view.Home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.view.Login.LoginActivity;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.my_music);

        final DrawerLayout drawer = findViewById(R.id.draw_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.draw_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.mnu_item_login) {
            Intent login = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(login);
        }else if (id == R.id.mnu_item_setting) {
//            Intent setting = new Intent(HomeActivity.this, LoginActivity.class);
//            startActivity(setting);
        }else if (id == R.id.mnu_item_about) {
//            Intent about = new Intent(HomeActivity.this, LoginActivity.class);
//            startActivity(about);
        }else if (id == R.id.mnu_item_music) {
//            Intent music = new Intent(HomeActivity.this, LoginActivity.class);
//            startActivity(music);
        }else if (id == R.id.mnu_item_album) {
//            Intent album = new Intent(HomeActivity.this, LoginActivity.class);
//            startActivity(album);
        }else if (id == R.id.mnu_item_type) {
//            Intent type = new Intent(HomeActivity.this, LoginActivity.class);
//            startActivity(type);
        }else if (id == R.id.mnu_item_charts) {
//            Intent charts = new Intent(HomeActivity.this, LoginActivity.class);
//            startActivity(charts);
        }
            DrawerLayout drawer = findViewById(R.id.draw_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


}
