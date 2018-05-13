package com.example.quoctuan.msc.view.ListSong.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quoctuan.msc.Adapter.ListSong.Fragment.AlbumAdapter;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {
    private View view;

    private RecyclerView fragment_album_recyclerview;
    private AlbumAdapter albumAdapter;
    private LinearLayoutManager linearLayoutManager;

    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_album, container, false);

        addControls();
        addEvents();

        return view;

    }

    private void addControls() {
        fragment_album_recyclerview = view.findViewById(R.id.fragment_album_recyclerview);
        fragment_album_recyclerview.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        fragment_album_recyclerview.setLayoutManager(linearLayoutManager);
        albumAdapter = new AlbumAdapter(Common.AlbumOfflines, getContext());
        fragment_album_recyclerview.setAdapter(albumAdapter);
        albumAdapter.notifyDataSetChanged();
    }

    private void addEvents() {
    }

}
