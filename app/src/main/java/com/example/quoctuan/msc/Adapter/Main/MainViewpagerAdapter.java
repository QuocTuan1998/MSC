package com.example.quoctuan.msc.Adapter.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quoctuan.msc.view.Main.MainFragment.OfflineFragment;
import com.example.quoctuan.msc.view.Main.MainFragment.OnlineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Tung on 4/28/2018.
 */

public class MainViewpagerAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment = new ArrayList<>();
    List<String> listTitle = new ArrayList<>();

    public MainViewpagerAdapter(FragmentManager fm) {
        super(fm);
        listFragment.add(new OnlineFragment());
        listFragment.add(new OfflineFragment());
        listTitle.add("Online");
        listTitle.add("Offline");
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

}
