package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/*
 *          欢迎使用页面的view pager
 */
public class ViewPager_Adapter_Main extends PagerAdapter {
    Context mContext;
    ArrayList<Integer> list;
    public ViewPager_Adapter_Main(Context con, ArrayList<Integer> images){
        mContext = con;
        list =new ArrayList<>();
        list.addAll(images);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);

        imageView.setImageResource(list.get(position));

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}
