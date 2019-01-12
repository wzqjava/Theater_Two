package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.SelectThreaterRecyclerviewAdapter;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.SelectThrastersRecyclerViewBean;
import com.bw.movie.presenter.SelectTheatersPresenter;
import com.bw.movie.view.SelectTheatersView;

import java.util.HashMap;
import java.util.List;

public class SelectTheatersActivity extends BaseMVPActivity<SelectTheatersView, SelectTheatersPresenter> implements SelectTheatersView{

    private String name;
    private String id;
    private TextView select_theaters_name;
    private RecyclerView select_theaters_recyclerview;
    private ImageView select_theaters_fanhui;

    /**
     * 初始化presenter
     *
     * @return
     */
    @Override
    protected SelectTheatersPresenter initPresenter() {
        return new SelectTheatersPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_select_theaters;
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        select_theaters_name = findViewById(R.id.select_theaters_name);
        select_theaters_recyclerview = findViewById(R.id.select_theaters_recyclerview);
        select_theaters_fanhui = findViewById(R.id.select_theaters_fanhui);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        select_theaters_name.setText(name+"");
        HashMap<String, String> map = new HashMap<>();
        map.put("movieId",id+"");
        presenter.getSelectTheatersData(map);
    }

    /**
     * 设置监听
     */
    @Override
    protected void setListener() {
        select_theaters_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void success(List<SelectThrastersRecyclerViewBean.ResultBean> result) {
        Log.e("dianying",result.get(0).getName()+"");
        //开始展示影院的数据
        SelectThreaterRecyclerviewAdapter selectThreaterRecyclerviewAdapter = new SelectThreaterRecyclerviewAdapter(this, result);
        select_theaters_recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        select_theaters_recyclerview.setAdapter(selectThreaterRecyclerviewAdapter);
    }

    @Override
    public void error(String msg) {
        showToast(msg);
        Log.e("zhx","电影id查询影院: "+msg);
    }
}
