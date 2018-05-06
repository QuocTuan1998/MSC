package com.example.quoctuan.msc.view.Main.MainFragment;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quoctuan.msc.Adapter.Main.Online.PlaylistMusicAdapter;
import com.example.quoctuan.msc.Adapter.Main.Online.TopMusicAdapter;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.PlayLists;
import com.example.quoctuan.msc.model.Songs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    public MediaPlayer mediaPlayer;
    private static List<Songs> listSongData;

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
        online_recyvlerview_topfivemusic.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                //Toast.makeText(getActivity(), "ádaf" + e, Toast.LENGTH_SHORT).show();
                return false;

            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                //Toast.makeText(getContext(), "sádasdafaw", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                Toast.makeText(getActivity(), "ád" + disallowIntercept, Toast.LENGTH_SHORT).show();
            }
        });
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
        listSongData = (List<Songs>) getActivity().getIntent().getSerializableExtra("TopFive");

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

    public void PlayMusic(int position) {
        String url = "http://192.168.1.8/mvc/public/music/" + listSongData.get(position).getLink();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
