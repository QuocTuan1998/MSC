package com.example.quoctuan.msc.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quoctuan.msc.R;

/**
 * Created by Nguyen Van Tung on 5/5/2018.
 */

public class PlaylistViewHolder extends RecyclerView.ViewHolder {
    public ImageView item_playlist_img;
    public TextView item_playlist_name;
    public LinearLayout item_playlist_layout;
    private View view;

    public PlaylistViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;

        addControls();
        addEvent();

    }

    private void addControls() {
        item_playlist_img    = view.findViewById(R.id.item_playlist_img);
        item_playlist_name   = view.findViewById(R.id.item_playlist_name);
        item_playlist_layout = view.findViewById(R.id.item_playlist_layout);
    }

    private void addEvent() {

    }

}
