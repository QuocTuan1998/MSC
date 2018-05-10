package com.example.quoctuan.msc.view.ListSong.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.quoctuan.msc.Adapter.ListSong.Fragment.MusicAdapter;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {
    private View view;

    private RecyclerView fragment_music_recyclerview;
    private MusicAdapter musicAdapter;
    private LinearLayoutManager linearLayoutManager;

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
        fragment_music_recyclerview = view.findViewById(R.id.fragment_music_recyclerview);
        fragment_music_recyclerview.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        fragment_music_recyclerview.setLayoutManager(linearLayoutManager);
        musicAdapter = new MusicAdapter(Common.MusicOfflines, getContext());
        fragment_music_recyclerview.setAdapter(musicAdapter);
        musicAdapter.notifyDataSetChanged();
    }

    private void addEvents() {

    }

}
