package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

/**
 * date:2019/1/10
 * author:李壮(大壮)
 * function:
 */
public class CinemaTabLayoutAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments;

    public CinemaTabLayoutAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
    }

    public void setData(ArrayList<Fragment> fragments) {
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "详情";
            case 1:
                return "评论";
            case 2:
                return "返回";
        }
        return null;
    }

}
