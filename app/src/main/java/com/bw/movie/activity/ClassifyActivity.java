package com.bw.movie.activity;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.presenter.ClassfyPresenter;
import com.bw.movie.view.ClassfyView;

public class ClassifyActivity extends BaseMVPActivity<ClassfyView, ClassfyPresenter> {

    private Button classfy_remen;
    private Button classfy_zhengzai;
    private Button classfy_jijiang;
    private RecyclerView classfy_recyclerview;

    /**
     * 初始化presenter
     *
     * @return
     */
    @Override
    protected ClassfyPresenter initPresenter() {
        return new ClassfyPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_classify;
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        classfy_remen = findViewById(R.id.classfy_remen);
        classfy_zhengzai = findViewById(R.id.classfy_zhengzai);
        classfy_jijiang = findViewById(R.id.classfy_jijiang);
        classfy_recyclerview = findViewById(R.id.classfy_recyclerview);
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
