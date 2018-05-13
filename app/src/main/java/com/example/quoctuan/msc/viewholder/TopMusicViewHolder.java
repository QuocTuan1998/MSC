package com.example.quoctuan.msc.viewholder;


import android.content.Intent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quoctuan.msc.Adapter.Main.Online.TopMusicAdapter;
import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.view.PlayMusic.PlayActivity;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.Connect.DownloadJson;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.PlayMusic.PlusMusic;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonLike;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonLitening;
import com.example.quoctuan.msc.view.InfoMusic.InfoMusicOnline;
import com.example.quoctuan.msc.view.Main.MainFragment.OnlineFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    private int count_listenning = 0;
    private int count_like = 0;
    private SharedPreferences sharedPreferences;

    public TopMusicViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;

        sharedPreferences = itemView.getContext().getSharedPreferences("InforUser", Context.MODE_PRIVATE);
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
        item_top_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_top_layout:
                Common.IS_PLAYED = true;
                Common.PLAYED_IS_ONLINE = true;
                new PlusMusic().PlusMusic(sharedPreferences.getInt("id", 0),
                        Common.TopFiveMusic.get(getAdapterPosition()).getId());
                Common.IS_PLAYED = true;
                Common.PLAYED_IS_ONLINE = true;
                Common.POSSITION_MUSIC_PLAYED = getAdapterPosition();
                new PlayMusic().PlayMusic(Common.TopFiveMusic.get(getAdapterPosition()));
                new OnlineFragment().SetLayoutWhenPlaying();

                Intent iPlay = new Intent(view.getContext(), PlayActivity.class);
                view.getContext().startActivity(iPlay);
                break;
            case R.id.item_top_more:
                //TopMusicPressIconMore();
                Intent intent = new Intent(itemView.getContext(), InfoMusicOnline.class);
                intent.putExtra("song", Common.TopFiveMusic.get(getAdapterPosition()));
                itemView.getContext().startActivity(intent);
                break;
        }
    }

}
