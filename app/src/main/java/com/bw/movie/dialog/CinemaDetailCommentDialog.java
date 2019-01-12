package com.bw.movie.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaDetailAdapter;
import com.bw.movie.adapter.CinemaTabLayoutAdapter;
import com.bw.movie.fragment.CinemaCommentFragment;
import com.bw.movie.fragment.CinemaDetailFragment;

import java.util.ArrayList;

/**
 * date:2019/1/10
 * author:李壮(大壮)
 * function:----------影院详情，评论弹框----------
 */
public class CinemaDetailCommentDialog extends DialogFragment implements View.OnClickListener {

    private TabLayout mCinema_tetail_tab;
    private ViewPager mCinema_detail_viewpager;
    private CinemaTabLayoutAdapter mCinemaTabLayoutAdapter;
    private ArrayList<Fragment> mFragments;
    private String mId;
    private ImageView mCinema_detail_down_icon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.cinema_detail_comment_item,null);

        /**
         * 初始化资源控件id
         */
        initView(view);

        /**
         * 初始化数据
         */
        initData();

        return view;
    }

    /**
     * 设置对话框样式
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getString("id");
        setStyle(DialogFragment.STYLE_NORMAL, R.style.TranslucentNoTitleDialog);

    }

    /**
     * 加载数据
     */
    private void initData() {
    }

    /**
     * 获取资源控件id
     */
    private void initView(View view) {
        mCinema_tetail_tab = view.findViewById(R.id.cinema_detail_tab);
        mCinema_detail_viewpager = view.findViewById(R.id.cinema_detail_viewpager);
        mCinema_detail_down_icon = view.findViewById(R.id.cinema_detail_down_icon);
        mCinema_detail_down_icon.setOnClickListener(this);

        //创建适配器
        mCinemaTabLayoutAdapter = new CinemaTabLayoutAdapter(getChildFragmentManager());
        mFragments = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putString("id",mId);
        //影院详情
        CinemaDetailFragment cinemaDetailFragment = new CinemaDetailFragment();
        cinemaDetailFragment.setArguments(bundle);
        //影院评论
        CinemaCommentFragment cinemaCommentFragment = new CinemaCommentFragment();
        cinemaCommentFragment.setArguments(bundle);

        mFragments.add(cinemaDetailFragment);
        mFragments.add(cinemaCommentFragment);

        mCinemaTabLayoutAdapter.setData(mFragments);
        mCinema_detail_viewpager.setAdapter(mCinemaTabLayoutAdapter);
        mCinema_tetail_tab.setupWithViewPager(mCinema_detail_viewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cinema_detail_down_icon:
                dismiss();
                break;
        }
    }
}
