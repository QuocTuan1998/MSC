package com.example.quoctuan.msc.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.Songs;
import com.example.quoctuan.msc.viewholder.ShowMusicOnlineViewholder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nguyen Van Tung on 5/14/2018.
 */

public class ShowMusicOnlineAdapter extends RecyclerView.Adapter<ShowMusicOnlineViewholder> {
    private List<Songs> listSong;
    private Context context;

    public ShowMusicOnlineAdapter(Context context, List<Songs> listSong){
        this.listSong = listSong;
        this.context = context;
    }

    @Override
    public ShowMusicOnlineViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        return new ShowMusicOnlineViewholder(view);
    }

    @Override
    public void onBindViewHolder(ShowMusicOnlineViewholder holder, int position) {
        holder.item_music_off_txt_name.setText(listSong.get(position).getTen());
        holder.item_music_off_txt_singer.setText(listSong.get(position).getTen());
        Picasso.get().load(Common.URL_IMG_SONG + listSong.get(position).getAnh()).into(holder.item_music_off_img_music);
    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }
}
