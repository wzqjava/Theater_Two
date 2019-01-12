package com.bw.movie.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.presenter.ChoosePresenter;
import com.bw.movie.view.ChooseView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 选座页面
 */
public class ChooseActivity extends BaseMVPActivity<ChooseView,ChoosePresenter> {

    private TextView mChoose_movie_name;

    @Override
    protected ChoosePresenter initPresenter() {
        return new ChoosePresenter();
    }

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_choose;
    }

    /**
     * 初始化资源控件id
     */
    @Override
    protected void initView() {
        mChoose_movie_name = findViewById(R.id.choose_movie_name);
    }

    /**
     * 加载数据
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
