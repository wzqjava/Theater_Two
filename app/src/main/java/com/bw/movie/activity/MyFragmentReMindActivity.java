package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.MyFragmentReMindRecyclerviewAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.MyFragmentReMindRecyclerViewBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.MyFragmentReMindPresenter;
import com.bw.movie.view.MyFragmentReMindView;
import com.greendao.gen.UserBeanDao;

import java.util.HashMap;
import java.util.List;

public class MyFragmentReMindActivity extends BaseMVPActivity<MyFragmentReMindView,MyFragmentReMindPresenter> implements MyFragmentReMindView{

    private ImageView activity_remind_fanhui;
    private RecyclerView activity_remind_recyclerview;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;

    /**
     * 初始化presenter
     *
     * @return
     */
    @Override
    protected MyFragmentReMindPresenter initPresenter() {
        return new MyFragmentReMindPresenter();
    }
    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_my_fragment_re_mind;
    }
    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        activity_remind_fanhui = (ImageView) findViewById(R.id.activity_remind_fanhui);
        activity_remind_recyclerview = (RecyclerView) findViewById(R.id.activity_remind_recyclerview);
        //activity_remind_text = (TextView) findViewById(R.id.activity_remind_text);
    }
    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        /**
         *
         */
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        HashMap<String, String> map = new HashMap<>();
        map.put("page","1");
        map.put("count","10");
        presenter.getReMindRecyclerViewData(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);
    }
    /**
     * 设置监听
     */
    @Override
    protected void setListener() {
        activity_remind_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void success(List<MyFragmentReMindRecyclerViewBean.ResultBean> result) {
        if (result != null){
            Log.e("zhx","我的页面的通知栏成功    "+result.get(0).getTitle());
            MyFragmentReMindRecyclerviewAdapter myFragmentReMindRecyclerviewAdapter = new MyFragmentReMindRecyclerviewAdapter(this,result);
            activity_remind_recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            activity_remind_recyclerview.setAdapter(myFragmentReMindRecyclerviewAdapter);
        }
    }
    @Override
    public void error(String msg) {
        Log.e("zhx","我的页面的通知栏失败    "+msg);
    }
}
