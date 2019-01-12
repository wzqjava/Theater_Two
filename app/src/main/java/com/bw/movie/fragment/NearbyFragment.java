package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.activity.CinemaDetailActivity;
import com.bw.movie.adapter.CinemaRecommendAdapter;
import com.bw.movie.adapter.NearbyAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.LatitudeLongitudeBean;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.NearbyPresenter;
import com.bw.movie.view.NearbyView;
import com.greendao.gen.UserBeanDao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * date:2019/1/4
 * author:李壮(大壮)
 * function:附近影院展示数据
 */
public class NearbyFragment extends BaseFragment implements NearbyView, Consumer<LatitudeLongitudeBean> {

    private XRecyclerView mCinema_nearby_xrecyclerview;
    private NearbyAdapter mNearbyAdapter;
    private NearbyPresenter mNearbyPresenter;
    private Map<String, String> mHeaderParams = new HashMap<>();
    private Map<String, String> mQueryParams = new HashMap<>();
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private Intent mIntent;

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.cinema_nearby_fragment;
    }

    /**
     * 初始化资源控件id
     * @param view
     */
    @Override
    protected void initView(View view) {
        mCinema_nearby_xrecyclerview = view.findViewById(R.id.cinema_nearby_xrecyclerView);

        //创建适配器
        mNearbyAdapter = new NearbyAdapter(getActivity());

        //设置布局管理器
        mCinema_nearby_xrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false));
        mCinema_nearby_xrecyclerview.setAdapter(mNearbyAdapter);

        mCinema_nearby_xrecyclerview.setLoadingMoreEnabled(true);
        mCinema_nearby_xrecyclerview.setPullRefreshEnabled(true);
        mCinema_nearby_xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                buildLatitudeLongitude();
                mNearbyPresenter.refreshData(mHeaderParams,mQueryParams);
            }

            @Override
            public void onLoadMore() {
               //加载
                buildLatitudeLongitude();
                mNearbyPresenter.loadData(mHeaderParams,mQueryParams);
            }
        });

    }

    private void buildLatitudeLongitude(){
        if(mLatitudeLongitudeBean != null) {
            mQueryParams.put("longitude", String.valueOf(mLatitudeLongitudeBean.getLongitude()));
            mQueryParams.put("latitude", String.valueOf(mLatitudeLongitudeBean.getLatitude()));
        }
    }

    /**
     * 加载数据
     * @param view
     */
    @Override
    protected void initData(View view) {
        //初始化P层
        mNearbyPresenter = new NearbyPresenter();
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        mHeaderParams.put("userId",mUserBeans.get(0).getUserId());
        mQueryParams.put("sessionId",mUserBeans.get(0).getSessionId());
        mNearbyPresenter.onCreate(this);
        //从外面过来
        buildLatitudeLongitude();
        //mRecommendPresenter.getData(queryParams);
        mNearbyPresenter.refreshData(mHeaderParams,mQueryParams);
        mIntent = new Intent(getActivity(),CinemaDetailActivity.class);
        mNearbyAdapter.setCinemaClickListener(new CinemaRecommendAdapter.CinemaClickListener() {
            @Override
            public void cinemaClick(int id) {
                mIntent.putExtra("id",String.valueOf(id));
                startActivity(mIntent);
            }
        });
    }

    /**
     * 设置监听
     * @param view
     */
    @Override
    protected void setListener(View view) {

    }

    @Override
    public void success(boolean isRefresh,List<NearbyBean.ResultBean> resultBeans) {
        if(isRefresh) {
            mNearbyAdapter.setData(resultBeans);
        } else {
            mNearbyAdapter.addData(resultBeans);
        }
    }

    @Override
    public void error(String msg) {

    }

    @Override
    public void onloadComplete() {
        mCinema_nearby_xrecyclerview.refreshComplete();
        mCinema_nearby_xrecyclerview.loadMoreComplete();
    }

    public void initPublishSubject(PublishSubject<LatitudeLongitudeBean> publishSubject) {
        publishSubject.subscribe(this);
    }

    private LatitudeLongitudeBean mLatitudeLongitudeBean = new LatitudeLongitudeBean(40.04121761079534,116.30031551487085);
    @Override
    public void accept(LatitudeLongitudeBean latitudeLongitudeBean) throws Exception {
        mLatitudeLongitudeBean = latitudeLongitudeBean;
    }
}
