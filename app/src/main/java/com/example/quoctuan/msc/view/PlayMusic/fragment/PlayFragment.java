package com.example.quoctuan.msc.view.PlayMusic.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class PlayFragment extends Fragment implements View.OnClickListener {

    private View view;

    private PlayMusic playMusic;
    private static CircleImageView play_imgSong;
    private static TextView play_timeSong;
    private static ImageView play_btn_shuffle, play_btn_prev, play_btn_play, play_btn_next, play_btn_repeat, play_img_play;
    private static CircularSeekBar play_seekbar;

    private MediaMetadataRetriever mediaMetadataRetriever;

    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_play, container, false);

        addControls();
        playMusic();
        addData();
        addEvents();
        return view;
    }

    private void addControls() {

        playMusic = new PlayMusic();
        play_imgSong = view.findViewById(R.id.play_imgSong);
        play_timeSong =view.findViewById(R.id.play_timeSong);
        //play_btn_shuffle = view.findViewById(R.id.play_btn_shuffle);
        play_btn_prev = view.findViewById(R.id.play_btn_prev);
        play_btn_play = view.findViewById(R.id.play_btn_play);
        play_btn_next = view.findViewById(R.id.play_btn_next);
        //play_btn_repeat = view.findViewById(R.id.play_btn_repeat);
        play_seekbar    = view.findViewById(R.id.play_seekbar);
        play_img_play   = view.findViewById(R.id.play_img_play);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_img_small_layout);
        play_imgSong.startAnimation(animation);

    }

    public void addData(){
        if (Common.MEDIAPLAYER.isPlaying())
            play_btn_play.setImageLevel(0);
        else
            play_btn_play.setImageLevel(1);
//            Common.TopFiveMusic.get(Common.POSSITION_MUSIC_PLAYED).getTen();

        if (Common.PLAYED_IS_ONLINE){
            Picasso.get().load(Common.URL_IMG_SONG + Common.SONGPLAYED.getAnh()).into(play_imgSong);
        }else {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(Common.SONGPLAYED.getLink());
            byte [] data = mediaMetadataRetriever.getEmbeddedPicture();
            if (data != null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                play_imgSong.setImageBitmap(bitmap);
            }else {
                play_imgSong.setImageResource(R.drawable.song1);
            }
        }

        play_seekbar.setMax(Common.MEDIAPLAYER.getDuration());
        UpdateTimeMusic();
    }

    private void addEvents() {
        play_seekbar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {
                Common.MEDIAPLAYER.seekTo((int) play_seekbar.getProgress());
            }
        });
        play_btn_play.setOnClickListener(this);
        play_btn_prev.setOnClickListener(this);
        play_btn_next.setOnClickListener(this);
    }

    private void UpdateTimeMusic(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                play_seekbar.setProgress(Common.MEDIAPLAYER.getCurrentPosition());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//                play_timeSong.setText(simpleDateFormat.format(Common.MEDIAPLAYER.getDuration()
//                        - Common.MEDIAPLAYER.getCurrentPosition()));
                play_timeSong.setText(simpleDateFormat.format(Common.MEDIAPLAYER.getCurrentPosition()));
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void PausePlayPress() {
        if (Common.MEDIAPLAYER.isPlaying()){
            new PlayMusic().PauseMusic();
            play_img_play.setImageLevel(1);
        }else {
            new PlayMusic().StartMusic();
            play_img_play.setImageLevel(0);
        }
    }

    private void playMusic() {
        Common.IS_PLAYED = true;
//        Common.PLAYED_IS_ONLINE = false;
//        playMusic.PlayMusic(view.getContext(), Common.POSSITION_MUSIC_PLAYED);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play_btn_play:
                PausePlayPress();
                break;
            case R.id.play_btn_prev:
                new PlayMusic().PreHandle(getContext());
                break;
            case R.id.play_btn_next:
                new PlayMusic().NextHandle(getContext());
                break;
        }
    }
}
