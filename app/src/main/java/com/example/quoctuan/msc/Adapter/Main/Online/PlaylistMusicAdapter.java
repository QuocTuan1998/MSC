package com.example.quoctuan.msc.Adapter.Main.Online;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.PlayLists;
import com.example.quoctuan.msc.viewholder.PlaylistViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nguyen Van Tung on 5/5/2018.
 */

public class PlaylistMusicAdapter extends RecyclerView.Adapter<PlaylistViewHolder> {
    private List<PlayLists> listPlaylistData;
    private Context context;

    public PlaylistMusicAdapter(List<PlayLists> listPlaylistData, Context context) {
        this.listPlaylistData = listPlaylistData;
        this.context = context;
    }

    @Override
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        return new PlaylistViewHolder(item);
    }

    @Override
    public void onBindViewHolder(PlaylistViewHolder holder, int position) {
        Picasso.get()
                .load(Common.URL_IMG_PLAYLIST + listPlaylistData.get(position).getAnh())
                .into(holder.item_playlist_img);
        holder.item_playlist_name.setText(listPlaylistData.get(position).getTen());
    }

    @Override
    public int getItemCount() {
        return listPlaylistData.size();
    }
}
