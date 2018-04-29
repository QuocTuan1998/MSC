package com.example.quoctuan.msc.view.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.view.Home.HomeActivity;
import com.example.quoctuan.msc.view.Main.MainActivity;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView image_splash = findViewById(R.id.image_splash);

        Animation animation_image = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);
        animation_image.setDuration(2000);
        image_splash.startAnimation(animation_image);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent login = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(login);
                }
            }
        });
        thread.start();



    }
}
