package com.bw.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

//降级处理

/**
 *
 *
 * 当用户没有网络的时候去跳转到本页面
 */
public class NetWorkActivity extends BaseActivity {

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_net_work;
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {

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

    }
}
