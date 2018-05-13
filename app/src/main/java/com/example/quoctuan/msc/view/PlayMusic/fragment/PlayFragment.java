package com.example.quoctuan.msc.view.PlayMusic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayFragment extends Fragment {

    private View view;

    private PlayMusic playMusic;
    private CircleImageView play_imgSong;
    private TextView play_timeSong;
    private ImageView play_btn_shuffle, play_btn_prev, play_btn_play, play_btn_next, play_btn_repeat;


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
        addEvents();
        return view;
    }

    private void addControls() {


        playMusic = new PlayMusic();
        play_imgSong = view.findViewById(R.id.play_imgSong);
        play_timeSong =view.findViewById(R.id.play_timeSong);
        play_btn_shuffle = view.findViewById(R.id.play_btn_shuffle);
        play_btn_prev = view.findViewById(R.id.play_btn_prev);
        play_btn_play = view.findViewById(R.id.play_btn_play);
        play_btn_next = view.findViewById(R.id.play_btn_next);
        play_btn_repeat = view.findViewById(R.id.play_btn_repeat);

    }

    private void addEvents() {



    }

    private void playMusic() {
        Common.IS_PLAYED = true;
//        Common.PLAYED_IS_ONLINE = false;
//        playMusic.PlayMusic(view.getContext(), Common.POSSITION_MUSIC_PLAYED);


    }
}
