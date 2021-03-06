package com.example.quoctuan.msc.Adapter.Main.Online;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.Songs;
import com.example.quoctuan.msc.viewholder.TopMusicViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nguyen Van Tung on 5/4/2018.
 */

public class TopMusicAdapter extends RecyclerView.Adapter<TopMusicViewHolder> {
    public List<Songs> songData;
    private Context context;

    public TopMusicAdapter(List<Songs> songData, Context context) {
        this.songData = songData;
        this.context = context;
    }

    public TopMusicAdapter() {
    }

    @Override
    public TopMusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_music, parent, false);

        return new TopMusicViewHolder(item);
    }

    @Override
    public void onBindViewHolder(TopMusicViewHolder holder, int position) {

        holder.item_top_txt_rank.setText((position + 1) + "");
        holder.item_top_txt_name.setText(songData.get(position).getTen());
        holder.item_top_txt_singer.setText(songData.get(position).getCasi());

        Picasso.get().load(Common.URL_IMG_SONG + songData.get(position).getAnh()).into(holder.item_top_img_music);

    }

    @Override
    public int getItemCount() {
        return songData.size();
    }

}
