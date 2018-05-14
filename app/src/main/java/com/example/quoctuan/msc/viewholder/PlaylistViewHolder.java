package com.example.quoctuan.msc.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.Connect.DownloadJson;
import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.ShowMusicOnlineActivity;
import com.example.quoctuan.msc.model.ParserJson.ParserJsonPlaylist;
import com.example.quoctuan.msc.model.Songs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Nguyen Van Tung on 5/5/2018.
 */

public class PlaylistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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
        item_playlist_layout.setOnClickListener(this);
        item_playlist_img.setOnClickListener(this);
    }

    private void GetListMusicFromPlaylistID(int adapterPosition) {
        List<Songs> listsong = null;
        List<HashMap<String, String>> attr = new ArrayList<>();
        HashMap<String, String> HmController = new HashMap<>();
        HmController.put("c", Common.PLAYLIST);
        HashMap<String, String> HmAction = new HashMap<>();
        HmAction.put("a", Common.FINDSONGFROMIDPLAYLIST);
        HashMap<String, String> HmID = new HashMap<>();
        HmID.put("id", String.valueOf(adapterPosition));

        attr.add(HmController);
        attr.add(HmAction);
        attr.add(HmID);

        DownloadJson downloadJson = new DownloadJson(attr);
        downloadJson.execute(Common.URL_API);

        try {
            listsong = new ParserJsonPlaylist().ParserJsonGetListSongFromIDPlayList(downloadJson.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Common.LISTSONGOFPLAYLIST = listsong;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_playlist_img:
                Intent iShow = new Intent(view.getContext(), ShowMusicOnlineActivity.class);
                GetListMusicFromPlaylistID(Common.LISTPLAYLIST.get(getAdapterPosition()).getId());
                view.getContext().startActivity(iShow);
                break;
        }
    }
}
