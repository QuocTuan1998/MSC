package com.example.quoctuan.msc.view.ListSong.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quoctuan.msc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {
    private View view;

    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_music, container, false);

        addControls();
        addEvents();
        return view;
    }

    private void addControls() {

    }

    private void addEvents() {

    }

}
