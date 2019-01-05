package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaRecommendAdapter;
import com.bw.movie.base.BaseFragment;

/**
 * date:2019/1/4
 * author:李壮(大壮)
 * function:
 */
public class RecommendFragment extends BaseFragment {

    private RecyclerView mCinema_Recommend_RecyclerView;
    private CinemaRecommendAdapter mCinemaRecommendAdapter;

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.cinema_recommend_fragment;
    }

    /**
     * 初始化控件资源id
     * @param view
     */
    @Override
    protected void initView(View view) {
        mCinema_Recommend_RecyclerView = view.findViewById(R.id.cinema_recommend_recyclerview);

        //创建适配器
        mCinemaRecommendAdapter = new CinemaRecommendAdapter();

    }

    /**
     * 加载数据
     * @param view
     */
    @Override
    protected void initData(View view) {

    }

    /**
     * 设置监听事件
     * @param view
     */
    @Override
    protected void setListener(View view) {

    }
}
