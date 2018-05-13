package com.example.quoctuan.msc.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.view.ListSong.ListSongActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by BINH-PC on 2/3/2018.
 */

public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public LinearLayout item_album_off_layout;
    public CircleImageView item_album_off_img_album, item_album_off_more;
    public TextView item_album_off_txt_name, item_album_off_txt_singer;
    private View view;

    public AlbumViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;

        addControls();
        addEvents();
    }

    private void addControls() {
        item_album_off_layout    = view.findViewById(R.id.item_album_off_layout);
        item_album_off_img_album = view.findViewById(R.id.item_album_off_img_album);
        item_album_off_more      = view.findViewById(R.id.item_album_off_more);
        item_album_off_txt_name  = view.findViewById(R.id.item_album_off_txt_name);
        item_album_off_txt_singer= view.findViewById(R.id.item_album_off_txt_singer);
    }

    private void addEvents() {
        item_album_off_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_album_off_layout:
//                Common.IS_PLAYED = true;
//                Common.PLAYED_IS_ONLINE = false;
//                Common.POSSITION_MUSIC_PLAYED = getAdapterPosition();
//                new PlayMusic().PlayMusic(this.view.getContext(), getAdapterPosition());
//                new ListSongActivity().ShowSmallMediaLayout();
                Toast.makeText(view.getContext(), "click!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
