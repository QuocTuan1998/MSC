package com.example.quoctuan.msc.PlayMusic;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.example.quoctuan.msc.view.Main.MainActivity;
import com.example.quoctuan.msc.view.Main.MainFragment.OnlineFragment;

import java.io.IOException;

/**
 * Created by Nguyen Van Tung on 5/7/2018.
 */

public class PlayMusic {
    public static MediaPlayer mediaPlayer;

    public PlayMusic() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void PlayMusic(String link){
        String url = "http://192.168.1.8/mvc/public/music/" + link;
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StartMusic(){
        mediaPlayer.start();
    }

    public void PauseMusic(){
        mediaPlayer.pause();
    }
}
