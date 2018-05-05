package com.example.quoctuan.msc.view.Main.MainFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.quoctuan.msc.Adapter.Main.Online.PlaylistMusicAdapter;
import com.example.quoctuan.msc.Adapter.Main.Online.TopMusicAdapter;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.Connect.DownloadJson;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonMusic;
import com.example.quoctuan.msc.model.PlayLists;
import com.example.quoctuan.msc.model.Songs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.content.Intent.getIntent;


/**
 * A simple {@link Fragment} subclass.
 */
public class OnlineFragment extends Fragment {

    private RecyclerView online_recyvlerview_topfivemusic;
    private RecyclerView online_recyclerview_playlist;
    private LinearLayoutManager linearLayoutManager;
    private TopMusicAdapter topMusicAdapter;
    private PlaylistMusicAdapter playlistMusicAdapter;

    private View view;

    public OnlineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_online, container, false);

        addControls();
        addEvents();

        return view;
    }

    private void addControls() {
        initRecyclerViewTopMusic(); //phương thức tạo recyvlerview top 5 music
        initRecyclerViewPlaylist(); //phương thức tạo recyclerview playlist

    }


    private void addEvents() {

    }

    //phương thức tạo recyvlerview top 5 music
    private void initRecyclerViewTopMusic() {
        online_recyvlerview_topfivemusic = view.findViewById(R.id.online_recyvlerview_topfivemusic);
        //.setHasFixedSize nó sẽ tối ưu hóa dữ liệu của chúng ta không bị ảnh hưởng bởi các nội dung
        online_recyvlerview_topfivemusic.setHasFixedSize(true);
        //trong adapter
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        online_recyvlerview_topfivemusic.setLayoutManager(linearLayoutManager);

        //lấy dữ liệu từ màn hình splash sreen qua
        List<Songs> listSongData = (List<Songs>) getActivity().getIntent().getSerializableExtra("TopFive");

        topMusicAdapter = new TopMusicAdapter(listSongData, getContext());
        online_recyvlerview_topfivemusic.setAdapter(topMusicAdapter);
        topMusicAdapter.notifyDataSetChanged();
    }//end phương thức tạo recyvlerview top 5 music

    private void initRecyclerViewPlaylist() {
        online_recyclerview_playlist = view.findViewById(R.id.online_recyclerview_playlist);
        //.setHasFixedSize nó sẽ tối ưu hóa dữ liệu của chúng ta không bị ảnh hưởng bởi các nội dung
        online_recyclerview_playlist.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        online_recyclerview_playlist.setLayoutManager(linearLayoutManager);
        List<PlayLists> listPlayLlistData = (List<PlayLists>) getActivity().getIntent().getSerializableExtra("Playlist");
        playlistMusicAdapter = new PlaylistMusicAdapter(listPlayLlistData, getContext());
        online_recyclerview_playlist.setAdapter(playlistMusicAdapter);
        playlistMusicAdapter.notifyDataSetChanged();
    }
}
