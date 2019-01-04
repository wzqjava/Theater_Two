package com.bw.movie.activity;

import com.bw.movie.R;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.presenter.RegisterPresenter;
import com.bw.movie.view.RegisterView;

/**
 * date:2019/1/3
 * author:赵豪轩(xuan)
 * function:
 */
public class RegisterActivity extends BaseMVPActivity<RegisterView,RegisterPresenter> {
    /**
     * 初始化presenter
     *
     * @return
     */
    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_register;
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
