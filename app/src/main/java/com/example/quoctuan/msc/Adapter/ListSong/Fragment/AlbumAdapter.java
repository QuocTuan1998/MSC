package com.example.quoctuan.msc.Adapter.ListSong.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quoctuan.msc.R;
import com.example.quoctuan.msc.model.Albums;
import com.example.quoctuan.msc.viewholder.AlbumViewHolder;
import com.example.quoctuan.msc.viewholder.MusicViewHolder;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
private List<Albums> listAlbumOffline;
private Context context;

private MediaMetadataRetriever mediaMetadataRetriever; //dung de lay anh tu file mp3

        public AlbumAdapter(List<Albums> listAlbumOffline, Context context){
                this.listAlbumOffline = listAlbumOffline;
                this.context = context;
        }

@Override
public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_offline, parent, false);
        return new AlbumViewHolder(view);
        }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        // set image
//        mediaMetadataRetriever = new MediaMetadataRetriever();
//        mediaMetadataRetriever.setDataSource(listAlbumOffline.get(position).getAnh());
//        byte [] data = mediaMetadataRetriever.getEmbeddedPicture();
//        if (data != null){
//        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//        holder.item_album_off_img_album.setImageBitmap(bitmap);
//        }else {
//            holder.item_album_off_img_album.setImageResource(R.drawable.music);
//        }

        holder.item_album_off_txt_name.setText(listAlbumOffline.get(position).getTen());
        holder.item_album_off_txt_singer.setText(listAlbumOffline.get(position).getCasi());
    }

    @Override
    public int getItemCount() {
        return listAlbumOffline.size();
    }

//@Override
//public void onBindViewHolder(AlbumViewHolder holder, int position) {
//        //set image
//        mediaMetadataRetriever = new MediaMetadataRetriever();
////        mediaMetadataRetriever.setDataSource(listAlbumOffline.get(position).get());
//        byte [] data = mediaMetadataRetriever.getEmbeddedPicture();
//        if (data != null){
//        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//        holder.item_music_off_img_music.setImageBitmap(bitmap);
//        }
//
//        holder.item_music_off_txt_name.setText(listAlbumOffline.get(position).getTen());
//        holder.item_music_off_txt_singer.setText(listAlbumOffline.get(position).getCasi_id());
//        }
//
//        @Override
//        public int getItemCount() {
//                return listAlbumOffline.size();
//        }
}
