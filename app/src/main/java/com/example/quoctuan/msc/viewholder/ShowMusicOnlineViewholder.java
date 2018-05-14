package com.example.quoctuan.msc.viewholder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.PlayMusic.PlayMusic;
import com.example.quoctuan.msc.PlayMusic.PlusMusic;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.ShowMusicOnlineActivity;
import com.example.quoctuan.msc.view.InfoMusic.InfoMusicOnline;
import com.example.quoctuan.msc.view.Main.MainFragment.OfflineFragment;
import com.example.quoctuan.msc.view.Main.MainFragment.OnlineFragment;
import com.example.quoctuan.msc.view.PlayMusic.PlayActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Van Tung on 5/14/2018.
 */

public class ShowMusicOnlineViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public CircleImageView item_music_off_img_music, item_music_off_more;
    public TextView item_music_off_txt_name, item_music_off_txt_singer;
    private View view;
    private LinearLayout item_music_off_layout;
    private SharedPreferences sharedPreferences;

    public ShowMusicOnlineViewholder(View itemView) {
        super(itemView);
        this.view = itemView;

        sharedPreferences = view.getContext().getSharedPreferences("InforUser", Context.MODE_PRIVATE);
        addControls();
        addEvents();
    }

    private void addControls() {
        item_music_off_img_music = view.findViewById(R.id.item_music_off_img_music);
        item_music_off_txt_name  = view.findViewById(R.id.item_music_off_txt_name);
        item_music_off_txt_singer= view.findViewById(R.id.item_music_off_txt_singer);
        item_music_off_more      = view.findViewById(R.id.item_music_off_more);
        item_music_off_layout    = view.findViewById(R.id.item_music_off_layout);
    }

    private void addEvents() {
        item_music_off_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_music_off_layout:
                Common.IS_PLAYED = true;
                Common.PLAYED_IS_ONLINE = true;
                new PlusMusic().PlusMusic(sharedPreferences.getInt("id", 0),
                        Common.TopFiveMusic.get(getAdapterPosition()).getId());
                Common.IS_PLAYED = true;
                Common.PLAYED_IS_ONLINE = true;
                Common.POSSITION_MUSIC_PLAYED = getAdapterPosition();
                new PlayMusic().PlayMusic(Common.LISTSONGOFPLAYLIST.get(getAdapterPosition()));
                Common.SONGPLAYED = Common.LISTSONGOFPLAYLIST.get(getAdapterPosition());
                new OnlineFragment().SetLayoutWhenPlaying();
                new OfflineFragment().SetLayoutWhenPlaying();
                new ShowMusicOnlineActivity().ShowSmallLayoutOnline();
                new ShowMusicOnlineActivity().CheckPlayed();
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
