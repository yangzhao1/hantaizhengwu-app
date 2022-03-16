package com.example.gs.gonser.govenmentservice.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yangzhao on 2018/3/28.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private String tabs[];
    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list, String tabs[]) {
        super(fm);
        this.list = list;
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {

        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * 如果不是自定义标签布局，需要重写该方法
     */
     @Override
     public CharSequence getPageTitle(int position) {
         return tabs[position];
     }
}
