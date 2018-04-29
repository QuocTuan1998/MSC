package com.example.quoctuan.msc.Adapter.ListSong;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quoctuan.msc.Common.Common;
import com.example.quoctuan.msc.view.ListSong.Fragment.AlbumFragment;
import com.example.quoctuan.msc.view.ListSong.Fragment.MusicFragment;
import com.example.quoctuan.msc.view.ListSong.Fragment.PlaylistFragment;
import com.example.quoctuan.msc.view.ListSong.Fragment.SingerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Tung on 4/29/2018.
 */

public class ListSongViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;
    private List<String> listTitle;

    public ListSongViewPagerAdapter(FragmentManager fm) {
        super(fm);
        listFragment = new ArrayList<>();
        listFragment.add(new MusicFragment());
        listFragment.add(new PlaylistFragment());
        listFragment.add(new AlbumFragment());
        listFragment.add(new SingerFragment());

        listTitle = new ArrayList<>();
        listTitle.add(Common.SONG);
        listTitle.add(Common.PLAYLIST);
        listTitle.add(Common.ALBUM);
        listTitle.add(Common.SINGER);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
}
