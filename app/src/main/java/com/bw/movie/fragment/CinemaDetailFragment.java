package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPFragment;
import com.bw.movie.bean.CinemaDetailCinemaBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.CinemaDetailCinemaPresenter;
import com.bw.movie.view.CinemaDetailCinemaView;
import com.greendao.gen.UserBeanDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date:2019/1/10
 * author:李壮(大壮)
 * function:----影院详情展示--------
 */
public class CinemaDetailFragment extends BaseMVPFragment<CinemaDetailCinemaView,CinemaDetailCinemaPresenter> implements CinemaDetailCinemaView{

    private TextView mCinema_detail_address;
    private TextView mCinema_detail_cinema_phone;
    private TextView mCinema_detail_cinema_line;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private String mMId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMId  = getArguments().getString("id");
    }

    /**
     * 初始化Presenter层
     * @return
     */
    @Override
    protected CinemaDetailCinemaPresenter initPresenter() {
        return new CinemaDetailCinemaPresenter();
    }

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int setView() {
        return  R.layout.cinema_detail_cinema_item;
    }

    /**
     * 加载资源控件id
     * @param view
     */
    @Override
    protected void initView(View view) {
        mCinema_detail_address = view.findViewById(R.id.cinema_detail_address);
        mCinema_detail_cinema_phone = view.findViewById(R.id.cinema_detail_cinema_phone);
        mCinema_detail_cinema_line = view.findViewById(R.id.cinema_detail_cinema_line);
    }

    /**
     * 初始化数据
     * @param view
     */
    @Override
    protected void initData(View view) {
        Map<String,String> headerParams = new HashMap<>();
        Map<String,String> queryParams = new HashMap<>();
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        headerParams.put("userId",mUserBeans.get(0).getUserId());
        headerParams.put("sessionId",mUserBeans.get(0).getSessionId());

        queryParams.put("cinemaId",mMId);
        presenter.getCinemaMessage(headerParams,queryParams);
    }

    /**
     * 初始化监听
     * @param view
     */
    @Override
    protected void setListener(View view) {

    }

    @Override
    public void success(CinemaDetailCinemaBean.ResultBean resultBeans) {
        mCinema_detail_address.setText(resultBeans.getAddress());
        mCinema_detail_cinema_phone.setText(resultBeans.getPhone());
        mCinema_detail_cinema_line.setText(resultBeans.getVehicleRoute());
    }

    @Override
    public void error() {

    }
}
