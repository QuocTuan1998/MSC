package com.example.quoctuan.msc.view.Main.MainFragment;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.GetData.GetListMusic;
import com.example.quoctuan.msc.model.Songs;
import com.example.quoctuan.msc.view.ListSong.ListSongActivity;
import com.example.quoctuan.msc.view.Login.LoginActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfflineFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView offline_count_list_song, offline_count_music, offline_txt_user;
    private LinearLayout offline_layout_user;
    private RelativeLayout offline_layout_music;

    private ArrayList<Songs> arrayList;
    private SharedPreferences sharedPreferences;
    private GetListMusic getListMusic;

    private final static int REQUES_CODE_PERMISSION_READ_STORAGE = 0;
    private boolean AllowPermissionReadStorage = false;

    public OfflineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_offline, container, false);
        getListMusic = new GetListMusic(getActivity());
        sharedPreferences = getContext().getSharedPreferences("InforUser", Context.MODE_PRIVATE);
        CheckPermissionReadExternalStorage();
        addControls();
        addEvents();
        return view;
    }

    private void addControls() {
        offline_count_list_song = view.findViewById(R.id.offline_count_list_song);
        offline_layout_user     = view.findViewById(R.id.offline_layout_user);
        offline_count_music     = view.findViewById(R.id.offline_count_music);
        offline_layout_music    = view.findViewById(R.id.offline_layout_music);
        offline_txt_user        = view.findViewById(R.id.offline_txt_user);

        offline_count_list_song.setText(getContext().getResources().getString(R.string.countSong) + " " + arrayList.size());
        offline_count_music.setText(arrayList.size() + "");
        if (!sharedPreferences.getString("email", "").equals("")){
            offline_txt_user.setText(sharedPreferences.getString("email", ""));
        }

    }

    private void addEvents() {
        offline_layout_user.setOnClickListener(this);
        offline_layout_music.setOnClickListener(this);
    }

//
//    private void getListMusic() {
//            arrayList = new ArrayList<>();
//            ContentResolver contentResolver = getActivity().getContentResolver();
//            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//            String selection = MediaStore.Audio.Media.IS_MUSIC + " !=0";
//            String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
//            Cursor cursor = contentResolver.query(uri, null, selection, null , sortOrder);
//
//            if (cursor != null){
//                int count = cursor.getCount();
//                Log.d("count", count + "");
//                if (count > 0){
//                    while (cursor.moveToNext()){
//                        int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
//                        String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
//                        String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
//                        String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//                        arrayList.add(new Songs(id,artist,0,0,0,title,"","",data));
//                    }
//                }
//            }
//    }

    private void CheckPermissionReadExternalStorage(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUES_CODE_PERMISSION_READ_STORAGE);
            }else arrayList = (ArrayList<Songs>) getListMusic.GetListMusic();
        }else {
            arrayList = (ArrayList<Songs>) getListMusic.GetListMusic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            AllowPermissionReadStorage = true;
            arrayList = (ArrayList<Songs>) getListMusic.GetListMusic();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.offline_layout_user:
                Intent ilogin = new Intent(getContext(), LoginActivity.class);
                startActivity(ilogin);
                break;
            case R.id.offline_layout_music:
                Intent iMusic = new Intent(getContext(), ListSongActivity.class);
                startActivity(iMusic);
                break;
        }
    }
}
