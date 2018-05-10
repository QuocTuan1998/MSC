package com.example.quoctuan.msc.PlayMusic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.view.Main.MainActivity;
import com.example.quoctuan.msc.view.Main.MainFragment.OnlineFragment;

import java.io.IOException;

/**
 * Created by Nguyen Van Tung on 5/7/2018.
 */

public class PlayMusic {

    public PlayMusic() {
        Common.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void PlayMusic(int possition){
        String url = Common.URL_LINK_SONG + Common.TopFiveMusic.get(possition).getLink();
        try {
            Common.mediaPlayer.stop();
            Common.mediaPlayer = new MediaPlayer();
            Common.mediaPlayer.setDataSource(url);
            Common.mediaPlayer.prepare();
            Common.mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PlayMusic(Context context, int possition){
        Uri uri = Uri.parse(Common.MusicOfflines.get(possition).getLink());
        try {
            Common.mediaPlayer.stop();
            Common.mediaPlayer = new MediaPlayer();
            Common.mediaPlayer.setDataSource(context, uri);
            Common.mediaPlayer.prepare();
            Common.mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void StartMusic(){
        Common.mediaPlayer.start();
    }

    public void PauseMusic(){
        Common.mediaPlayer.pause();
    }
}
