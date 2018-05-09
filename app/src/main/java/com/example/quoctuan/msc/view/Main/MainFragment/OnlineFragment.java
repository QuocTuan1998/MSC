package com.example.quoctuan.msc.view.Main.MainFragment;


import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quoctuan.msc.Adapter.Main.Online.PlaylistMusicAdapter;
import com.example.quoctuan.msc.Adapter.Main.Online.TopMusicAdapter;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.PlayLists;
import com.example.quoctuan.msc.model.Songs;
import com.example.quoctuan.msc.view.Main.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.quoctuan.msc.R.string.Waiting;


/**
 * A simple {@link Fragment} subclass.
 */
public class OnlineFragment extends Fragment {

    private static RecyclerView online_recyvlerview_topfivemusic;
    private RecyclerView online_recyclerview_playlist;
    private static LinearLayout main_layout_main;
    private LinearLayoutManager linearLayoutManager;
    private TopMusicAdapter topMusicAdapter;
    private PlaylistMusicAdapter playlistMusicAdapter;

    private View view;
    public static MediaPlayer mediaPlayer;
    private static List<Songs> listSongData;
    public static PlayMusic playMusic;
    public static ProgressDialog dialog;

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
        main_layout_main = view.findViewById(R.id.main_layout_main);

        playMusic = new PlayMusic();
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
        playMusic.PlayMusic(listSongData.get(position).getLink());
        new MainActivity().ShowSmallMediaLayout(listSongData.get(position).getAnh()
                ,listSongData.get(position).getTen(), listSongData.get(position).getCasi());
        if (main_layout_main.getPaddingBottom() == 0){
            SetPadding();
        }
    }

    public void SetPadding() {
        main_layout_main.setPadding(0, 0 , 0 , 140);
    }
}
