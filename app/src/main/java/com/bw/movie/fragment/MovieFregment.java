package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BaseMVPFragment;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.MoviewFragmentPresenter;
import com.bw.movie.view.MovieFregmentView;
import com.greendao.gen.UserBeanDao;

import java.util.HashMap;
import java.util.List;

/**
 * date:2019/1/4
 * author:赵豪轩
 * function:-----------影片页面------------
 */
public class MovieFregment extends BaseMVPFragment<MovieFregmentView,MoviewFragmentPresenter> implements MovieFregmentView{
    private RelativeLayout movie_fragment_relative_one;
    private RecyclerView movie_fragment_recyclerview_remen;
    private RelativeLayout movie_fragment_relative_two;
    private RecyclerView movie_fragment_recyclerview_zhengzai;
    private RelativeLayout movie_fragment_relative_three;
    private RecyclerView movie_fragment_recyclerview_jijiang;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;

    @Override
    protected MoviewFragmentPresenter initPresenter() {
        return new MoviewFragmentPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.movie_fragment;
    }

    /**
     * 初始化View
     *
     * @param view
     */
    @Override
    protected void initView(View view) {
        movie_fragment_relative_one = view.findViewById(R.id.movie_fragment_relative_one);
        movie_fragment_relative_two = view.findViewById(R.id.movie_fragment_relative_two);
        movie_fragment_relative_three = view.findViewById(R.id.movie_fragment_relative_three);
        movie_fragment_recyclerview_remen = view.findViewById(R.id.movie_fragment_recyclerview_remen);
        movie_fragment_recyclerview_zhengzai = view.findViewById(R.id.movie_fragment_recyclerview_zhengzai);
        movie_fragment_recyclerview_jijiang = view.findViewById(R.id.movie_fragment_recyclerview_jijiang);
    }

    /**
     * 初始化数据
     *
     * @param view
     */
    @Override
    protected void initData(View view) {
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        HashMap<String, String> map = new HashMap<>();
        map.put("page","1");
        map.put("count","10");
        Log.e("zhx",mUserBeans.get(0).getUserId()+"   "+mUserBeans.get(0).getSessionId()+"");
        presenter.moviewFragmentRemen(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);

    }

    /**
     * 设置监听
     *
     * @param view
     */
    @Override
    protected void setListener(View view) {

    }

    @Override
    public void successsReMen(List<MovieFragmentBean.ResultBean> result) {

        Log.e("zhx",result.get(0).getName()+"");
    }

    @Override
    public void error(String msg) {
        Log.e("zhx",msg+"");
    }
}
