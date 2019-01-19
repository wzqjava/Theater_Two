package com.bw.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.SearchAdapter;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.SearchBean;
import com.bw.movie.presenter.SearchPresenter;
import com.bw.movie.view.SearchView;

import java.util.HashMap;
import java.util.List;

/**
 * 搜索查询页面
 */
public class SearchActivity extends BaseMVPActivity<SearchView,SearchPresenter> implements View.OnClickListener,SearchView {

    private android.widget.SearchView mSearch_input;
    private RecyclerView mSearch_recyclerView;
    private SearchAdapter mSearchAdapter;
    private HashMap<String, String> mQueryParams;
    private ImageView mSearch_back;

    @Override
    protected SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_search;
    }

    /**
     * 初始化资源控件id
     */
    @Override
    protected void initView() {
        mSearch_input = findViewById(R.id.search_input);
        mSearch_recyclerView = findViewById(R.id.search_recyclerview);
        mSearch_back = findViewById(R.id.search_back);
        mSearch_back.setOnClickListener(this);
        mQueryParams = new HashMap<>();

        //创建展示数据适配器
        mSearchAdapter = new SearchAdapter(this);
        mSearch_recyclerView.setAdapter(mSearchAdapter);
        mSearch_recyclerView.setLayoutManager(new LinearLayoutManager(this,OrientationHelper.VERTICAL,false));
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
        mSearch_input.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query == null){
                    Toast.makeText(SearchActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    mQueryParams.put("cinemaName",query);
                    mQueryParams.put("page","1");
                    mQueryParams.put("count","10");
                    presenter.getSearch(mQueryParams);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_back:
                finish();
                break;
        }
    }

    @Override
    public void success(List<SearchBean.ResultBean> resultBeans) {
           mSearchAdapter.setData(resultBeans);
    }

    @Override
    public void error() {

    }
}
