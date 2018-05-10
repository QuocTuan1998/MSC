package com.example.quoctuan.msc.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.view.Main.MainFragment.OnlineFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Van Tung on 5/4/2018.
 */

public class TopMusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView item_top_txt_rank, item_top_txt_name, item_top_txt_singer;
    public CircleImageView item_top_img_music;
    public ImageView item_top_more;
    public LinearLayout item_top_layout;

    private View itemView;

    public TopMusicViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;

        addControls();
        addEvents();
    }

    private void addControls() {
        item_top_txt_rank   = itemView.findViewById(R.id.item_top_txt_rank);
        item_top_txt_name   = itemView.findViewById(R.id.item_top_txt_name);
        item_top_txt_singer = itemView.findViewById(R.id.item_top_txt_singer);
        item_top_img_music  = itemView.findViewById(R.id.item_top_img_music);
        item_top_more       = itemView.findViewById(R.id.item_top_more);
        item_top_layout     = itemView.findViewById(R.id.item_top_layout);
    }

    private void addEvents() {
        item_top_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_top_layout:
                Common.IS_PLAYED = true;
                Common.PLAYED_IS_ONLINE = true;
                Common.POSSITION_MUSIC_PLAYED = getAdapterPosition();
                new PlayMusic().PlayMusic(getAdapterPosition());
                new OnlineFragment().SetLayoutWhenPlaying();
                break;
        }
    }
}
