package com.example.quoctuan.msc.Adapter.Play;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quoctuan.msc.view.PlayMusic.fragment.ListMusicFragment;
import com.example.quoctuan.msc.view.PlayMusic.fragment.LyricFragment;
import com.example.quoctuan.msc.view.PlayMusic.fragment.PlayFragment;

import java.util.ArrayList;
import java.util.List;

public class PlayViewpagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<>();

    public PlayViewpagerAdapter(FragmentManager fm) {
        super(fm);
        listFragment.add(new ListMusicFragment());
        listFragment.add(new PlayFragment());
        listFragment.add(new LyricFragment());

    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}
