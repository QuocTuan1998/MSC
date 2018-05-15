package com.example.quoctuan.msc.Adapter.ListSong.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.Songs;
import com.example.quoctuan.msc.viewholder.MusicViewHolder;

import java.util.List;

/**
 * Created by Nguyen Van Tung on 5/10/2018.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicViewHolder> {
    private List<Songs> listMusicOffline;
    private Context context;

    private MediaMetadataRetriever mediaMetadataRetriever; //dung de lay anh tu file mp3

    public MusicAdapter(List<Songs> listMusicOffline, Context context){
        this.listMusicOffline = listMusicOffline;
        this.context = context;
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicViewHolder holder, int position) {
        //set image
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            Log.d("kiemtra", listMusicOffline.get(position).getLink());
            mediaMetadataRetriever.setDataSource(listMusicOffline.get(position).getLink());
            byte [] data = mediaMetadataRetriever.getEmbeddedPicture();
            if (data != null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                holder.item_music_off_img_music.setImageBitmap(bitmap);
            }else {
                holder.item_music_off_img_music.setImageResource(R.drawable.music);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        holder.item_music_off_txt_name.setText(listMusicOffline.get(position).getTen());
        holder.item_music_off_txt_singer.setText(listMusicOffline.get(position).getCasi());

    }

    @Override
    public int getItemCount() {
        return listMusicOffline.size();
    }
}
