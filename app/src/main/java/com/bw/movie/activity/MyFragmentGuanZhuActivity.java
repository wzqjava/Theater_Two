package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.presenter.MyFragmentGuanZhuPresenter;
import com.bw.movie.view.MyFragmentGuanZhuView;

public class MyFragmentGuanZhuActivity extends BaseMVPActivity<MyFragmentGuanZhuView,MyFragmentGuanZhuPresenter> {

    private RadioButton myfragment_guanzhu_dianying;
    private RadioButton myfragment_guanzhu_yingyuan;
    private RadioGroup radiogroup;
    private RecyclerView myfragment_guanzhu_recyclerview;
    private ImageView myfragment_guanzhu_fanhui;

    /**
     * 初始化presenter
     *
     * @return
     */
    @Override
    protected MyFragmentGuanZhuPresenter initPresenter() {
        return new MyFragmentGuanZhuPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_my_fragment_guan_zhu;
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        myfragment_guanzhu_dianying = (RadioButton) findViewById(R.id.myfragment_guanzhu_dianying);
        myfragment_guanzhu_yingyuan = (RadioButton) findViewById(R.id.myfragment_guanzhu_yingyuan);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        myfragment_guanzhu_recyclerview = (RecyclerView) findViewById(R.id.myfragment_guanzhu_recyclerview);
        myfragment_guanzhu_fanhui = (ImageView) findViewById(R.id.myfragment_guanzhu_fanhui);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MyFragmentGuanZhuActivity.class));
    }
    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

    }
    /**
     * 设置监听
     */
    @Override
    protected void setListener() {
        myfragment_guanzhu_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
