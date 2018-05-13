package com.example.quoctuan.msc.view.PlayMusic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quoctuan.msc.R;

public class LyricFragment extends Fragment {

    private View view;

    public LyricFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lyric, container, false);
        addControls();
        addEvents();
        return view;
    }

    private void addControls() {
    }

    private void addEvents() {
    }
}
