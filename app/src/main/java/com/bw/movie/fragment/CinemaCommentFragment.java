package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaCommentAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPFragment;
import com.bw.movie.bean.CinemaCommendBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.CinemaCommendPresenter;
import com.bw.movie.view.CinemaCommendView;
import com.greendao.gen.UserBeanDao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date:2019/1/10
 * author:李壮(大壮)
 * function:----影院评论展示--------
 */
public class CinemaCommentFragment extends BaseMVPFragment<CinemaCommendView,CinemaCommendPresenter> implements CinemaCommendView{

    private XRecyclerView mCinema_recommend_xrecyclerview;
    private CinemaCommentAdapter mCinemaCommentAdapter;
    private Map<String, String> mHeaderParams = new HashMap<>();
    private Map<String, String> mQueryParams = new HashMap<>();
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private String mId;

    @Override
    protected CinemaCommendPresenter initPresenter() {
        return new CinemaCommendPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getString("id");
    }

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.cinema_comment_item;
    }

    /**
     * 初始化资源控件
     * @param view
     */
    @Override
    protected void initView(View view) {
        mCinema_recommend_xrecyclerview = view.findViewById(R.id.cinema_recommend_xrecyclerview);
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        mHeaderParams.put("userId",mUserBeans.get(0).getUserId());
        mHeaderParams.put("sessionId",mUserBeans.get(0).getSessionId());
        mQueryParams.put("cinemaId",mId);
        //创建适配器
        mCinemaCommentAdapter = new CinemaCommentAdapter(getActivity());
        mCinema_recommend_xrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false));

        mCinema_recommend_xrecyclerview.setAdapter(mCinemaCommentAdapter);
        mCinema_recommend_xrecyclerview.setPullRefreshEnabled(true);
        mCinema_recommend_xrecyclerview.setLoadingMoreEnabled(true);

        mCinema_recommend_xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                presenter.refreshData(mHeaderParams,mQueryParams);
            }

            @Override
            public void onLoadMore() {
                //加载
                presenter.loadData(mHeaderParams,mQueryParams);
            }
        });
    }

    /**
     * 加载数据
     * @param view
     */
    @Override
    protected void initData(View view) {
           presenter.refreshData(mHeaderParams,mQueryParams);
    }

    /**
     * 设置监听
     * @param view
     */
    @Override
    protected void setListener(View view) {

    }


    @Override
    public void success(boolean isRefresh, List<CinemaCommendBean.ResultBean> resultBeans) {
        if (isRefresh){
            mCinemaCommentAdapter.setData(resultBeans);
        }else {
            mCinemaCommentAdapter.addData(resultBeans);
        }
    }

    @Override
    public void error() {

    }

    @Override
    public void onloadComplete() {
        mCinema_recommend_xrecyclerview.loadMoreComplete();
        mCinema_recommend_xrecyclerview.refreshComplete();
    }
}
