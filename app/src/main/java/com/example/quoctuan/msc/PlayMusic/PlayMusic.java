package com.example.quoctuan.msc.PlayMusic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.model.Songs;
import com.example.quoctuan.msc.view.ListSong.ListSongActivity;
import com.example.quoctuan.msc.view.Main.MainActivity;
import com.example.quoctuan.msc.view.Main.MainFragment.OnlineFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Tung on 5/7/2018.
 */

public class PlayMusic {
    private int c_pre = 0, c_next= 0;
    public PlayMusic() {
        Common.MEDIAPLAYER.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void PlayMusic(int possition,List<Songs> music){
        String url = Common.URL_LINK_SONG + music.get(possition).getLink();
        try {
            Common.MEDIAPLAYER.stop();
            Common.MEDIAPLAYER = new MediaPlayer();
            Common.MEDIAPLAYER.setDataSource(url);
            Common.MEDIAPLAYER.prepare();
            Common.MEDIAPLAYER.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PlayMusic(Context context, int possition, List<Songs> music){
        Uri uri = Uri.parse(music.get(possition).getLink());
        try {
            Common.MEDIAPLAYER.stop();
            Common.MEDIAPLAYER = new MediaPlayer();
            Common.MEDIAPLAYER.setDataSource(context, uri);
            Common.MEDIAPLAYER.prepare();
            Common.MEDIAPLAYER.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void StartMusic(){
        Common.MEDIAPLAYER.start();
    }

    public void PauseMusic(){
        Common.MEDIAPLAYER.pause();
    }

    public void PreHandle(View view, List<Songs> music) {
        if (c_pre != 0) {

            if (Common.POSSITION_MUSIC_PLAYED == 0) {
                Common.POSSITION_MUSIC_PLAYED = Common.MusicOfflines.size()-1;
            }else {
                Common.POSSITION_MUSIC_PLAYED --;
            }
            PlayMusic(view.getContext(), Common.POSSITION_MUSIC_PLAYED, music);
            new ListSongActivity().CheckPlayed();
            c_pre = 0;
        }else {

            Common.MEDIAPLAYER.seekTo(0);
            c_pre ++;
        }

    }

    public void NextHandle(View view, List<Songs> music) {
        if (c_next != 0) {

            if (Common.POSSITION_MUSIC_PLAYED == Common.MusicOfflines.size()-1) {
                Common.POSSITION_MUSIC_PLAYED = 0;
            }else {
                Common.POSSITION_MUSIC_PLAYED ++;
            }
            PlayMusic(view.getContext(), Common.POSSITION_MUSIC_PLAYED,music);
            new ListSongActivity().CheckPlayed();
            c_next = 0;
        }else {
            Common.MEDIAPLAYER.seekTo(0);
            c_next ++;
        }
    }
}
