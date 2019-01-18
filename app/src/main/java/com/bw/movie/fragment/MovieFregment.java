package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.ClassifyActivity;
import com.bw.movie.adapter.MovieFragmentReMenAdapter;
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

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

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
    private RecyclerCoverFlow movie_fragment_recyclercoverflow;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private MovieFragmentReMenAdapter mMovieFragmentReMenAdapter;
    private TextView movie_fragment_text_dong;
    private TextView movie_fragment_text_xian;

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
        movie_fragment_recyclercoverflow = view.findViewById(R.id.movie_fragment_recyclercoverflow);
        movie_fragment_text_dong = view.findViewById(R.id.movie_fragment_text_dong);
        movie_fragment_text_xian = view.findViewById(R.id.movie_fragment_text_xian);
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
        //热门电影
        presenter.moviewFragmentRemen(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);
        //正在上映
        presenter.moviewFragmentZhengZai(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);
        //即将上映
        presenter.moviewFragmentJiJiang(mUserBeans.get(0).getUserId(),mUserBeans.get(0).getSessionId(),map);
    }
    /**
     * 设置监听
     *
     * @param view
     */
    @Override
    protected void setListener(View view) {
        movie_fragment_relative_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClassifyActivity.class);
                intent.putExtra("name","1");
                startActivity(intent);
            }
        });
        movie_fragment_relative_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClassifyActivity.class);
                intent.putExtra("name","2");
                startActivity(intent);
            }
        });
        movie_fragment_relative_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClassifyActivity.class);
                intent.putExtra("name","3");
                startActivity(intent);
            }
        });
    }

    public void startActivity(Class ss){
        startActivity(new Intent(getActivity(),ss));
    }
    //热门电影
    @Override
    public void successsReMen(List<MovieFragmentBean.ResultBean> result) {
        mMovieFragmentReMenAdapter = new MovieFragmentReMenAdapter(getActivity(), result);
        movie_fragment_recyclerview_remen.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        movie_fragment_recyclerview_remen.setAdapter(mMovieFragmentReMenAdapter);
        Log.e("zhx","remen+r"+result.get(0).getName()+"");

        //设置的是画廊轮播图
        movie_fragment_recyclercoverflow.setFlatFlow(false);
        movie_fragment_recyclercoverflow.setAdapter(mMovieFragmentReMenAdapter);
        movie_fragment_recyclercoverflow.scrollToPosition(2);
      /*  movie_fragment_recyclercoverflow.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });*/


    }
    //正在上映
    @Override

    public void successsZhengZai(List<MovieFragmentBean.ResultBean> result) {
        MovieFragmentReMenAdapter movieFragmentReMenAdapter = new MovieFragmentReMenAdapter(getActivity(), result);
        movie_fragment_recyclerview_zhengzai.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        movie_fragment_recyclerview_zhengzai.setAdapter(movieFragmentReMenAdapter);
        Log.e("zhx","zhengzai+r" +result.get(0).getName());
    }
    //即将上映
    @Override
    public void successsJiJiang(List<MovieFragmentBean.ResultBean> result) {
        Log.e("zhx","jijiang+r" +result.get(0).getName());
        MovieFragmentReMenAdapter movieFragmentReMenAdapter1 = new MovieFragmentReMenAdapter(getActivity(), result);
        movie_fragment_recyclerview_jijiang.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        movie_fragment_recyclerview_jijiang.setAdapter(movieFragmentReMenAdapter1);
    }

    @Override
    public void errorReMen(String msg) {
        Log.e("zhx",msg+"     remen");
    }
    @Override
    public void errorZhengZai(String msg) {
        Log.e("zhx",msg+"     zhengzai");
    }
    @Override
    public void errorJiJiang(String msg) {
        Log.e("zhx",msg+"     jijiang");
    }
}
