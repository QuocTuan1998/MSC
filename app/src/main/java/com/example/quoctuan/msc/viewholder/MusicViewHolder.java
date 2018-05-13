package com.example.quoctuan.msc.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by BINH-PC on 2/3/2018.
 */

public class MusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public LinearLayout item_music_off_layout;
    public CircleImageView item_music_off_img_music, item_music_off_more;
    public TextView item_music_off_txt_name, item_music_off_txt_singer;
    private View view;

    public MusicViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;

        addControls();
        addEvents();
    }

    private void addControls() {
        item_music_off_layout    = view.findViewById(R.id.item_music_off_layout);
        item_music_off_img_music = view.findViewById(R.id.item_music_off_img_music);
        item_music_off_more      = view.findViewById(R.id.item_music_off_more);
        item_music_off_txt_name  = view.findViewById(R.id.item_music_off_txt_name);
        item_music_off_txt_singer= view.findViewById(R.id.item_music_off_txt_singer);
    }

    private void addEvents() {
        item_music_off_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_music_off_layout:
//                Common.IS_PLAYED = true;
//                Common.PLAYED_IS_ONLINE = false;
                Common.POSSITION_MUSIC_PLAYED = getAdapterPosition();
                Toast.makeText(view.getContext(), "pos" + Common.POSSITION_MUSIC_PLAYED, Toast.LENGTH_SHORT).show();
//                new PlayMusic().PlayMusic(this.view.getContext(), getAdapterPosition());
//                new ListSongActivity().ShowSmallMediaLayout();
//                Intent iPlay = new Intent(view.getContext(), PlayActivity.class);
//                view.getContext().startActivity(iPlay);

                break;
        }
    }
}
