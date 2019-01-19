package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.CinemaDetailAdapter;
import com.bw.movie.adapter.CinemaDetailScheduleAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.CinemaDetailBean;
import com.bw.movie.bean.CinemaDetailIconBean;
import com.bw.movie.bean.CinemaDetailScheduleBean;
import com.bw.movie.dialog.CinemaDetailCommentDialog;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.CinemaDetailPresenter;
import com.bw.movie.view.CinemaDetailView;
import com.greendao.gen.UserBeanDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * ------影院详情页面-----------
 */
public class CinemaDetailActivity extends BaseMVPActivity<CinemaDetailView,CinemaDetailPresenter> implements View.OnClickListener, CinemaDetailView {

    private ImageView mCinema_detail_cinema_icon;
    private TextView mCinema_detail_cinema_name;
    private TextView mCinema_detail_cinema_address;
    private RecyclerCoverFlow mCinema_detail_recyclercoverflow;
    private RecyclerView mCinema_detail_recyclerview;
    private String mId;
    private Button mCinema_detail_back;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private CinemaDetailAdapter mCinemaDetailAdapter;
    private CinemaDetailScheduleAdapter mCinemaDetailScheduleAdapter;
    private int mScheduleId;
    private String mCinemaName;
    private String mCinemaAddress;
    private String mMovieName;

    @Override
    protected CinemaDetailPresenter initPresenter() {
        return new CinemaDetailPresenter();
    }

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_cinema_detail;
    }

    /**
     * 初始化资源控件id
     */
    @Override
    protected void initView() {


        mCinema_detail_cinema_icon = findViewById(R.id.cinema_detail_cinema_icon);
        mCinema_detail_cinema_name = findViewById(R.id.cinema_detail_cinema_name);
        mCinema_detail_cinema_address = findViewById(R.id.cinema_detail_cinema_address);
        mCinema_detail_recyclercoverflow = findViewById(R.id.cinema_detail_recyclercoverflow);
        mCinema_detail_recyclerview = findViewById(R.id.cinema_detail_recyclerview);
        mCinema_detail_back = findViewById(R.id.cinema_detail_back);

        mCinema_detail_back.setOnClickListener(this);
        mCinema_detail_cinema_name.setOnClickListener(this);
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        mCinema_detail_cinema_name.setText(mId);

        //初始化画廊效果轮播图
        initViewPager();


    }

    /**
     * 加载画廊效果的轮播图
     */
    private void initViewPager() {
        //设置的是画廊轮播图
        mCinema_detail_recyclercoverflow.setFlatFlow(false);
        mCinemaDetailAdapter = new CinemaDetailAdapter(CinemaDetailActivity.this);
        mCinema_detail_recyclercoverflow.setAdapter(mCinemaDetailAdapter);
        mCinema_detail_recyclercoverflow.scrollToPosition(2);
        mCinema_detail_recyclercoverflow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                /**
                 * 初始化当前影片的排期
                 */
                initSchedule(position);
            }
        });
    }

    /**
     * 设置当前影片的排期
     * @param position
     */
    private void initSchedule(int position) {
        mScheduleId = mCinemaDetailAdapter.getItem(position).getId();
        mMovieName = mCinemaDetailAdapter.getItem(position).getName();
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("cinemasId" , mId);
        queryParams.put("movieId", mScheduleId +"");
        presenter.getDetailSchedule(queryParams);
        mCinemaDetailScheduleAdapter = new CinemaDetailScheduleAdapter(CinemaDetailActivity.this);
        mCinema_detail_recyclerview.setAdapter(mCinemaDetailScheduleAdapter);
        mCinema_detail_recyclerview.setLayoutManager(new LinearLayoutManager(CinemaDetailActivity.this,OrientationHelper.VERTICAL,false));

        mCinemaDetailScheduleAdapter.setSetOnClickListener(new CinemaDetailScheduleAdapter.SetOnClickChooseListener() {
            @Override
            public void onClick(int id, String screeningHall, String beginTime, String endTime, String price) {
                Intent intent = new Intent(CinemaDetailActivity.this,ChooseActivity.class);

                intent.putExtra("scheduleId",id);
                intent.putExtra("cinemaName",mCinemaName);
                intent.putExtra("cinemaAddress",mCinemaAddress);
                intent.putExtra("movieName",mMovieName);
                intent.putExtra("movieHall",screeningHall);
                intent.putExtra("movieBegin",beginTime);
                intent.putExtra("movieEnd",endTime);
                intent.putExtra("price",price);
                startActivity(intent);
            }



        });
    }


    /**
     * 加载数据
     */
    @Override
    protected void initData() {
        Map<String,String> headerParams = new HashMap<>();
        Map<String,String> queryParams = new HashMap<>();
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        headerParams.put("userId",mUserBeans.get(0).getUserId());
        headerParams.put("sessionId",mUserBeans.get(0).getSessionId());

        queryParams.put("cinemaId",mId);
        presenter.getData(headerParams,queryParams);

        presenter.getDetailIcon(queryParams);

    }

    /**
     * 加载监听
     */
    @Override
    protected void setListener() {
    }

    /**
     * 返回按钮
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cinema_detail_back:
                finish();
                break;
            case R.id.cinema_detail_cinema_name:
                //调取弹框
                CinemaDetailCommentDialog dialog = new CinemaDetailCommentDialog();
                Bundle bundle = new Bundle();
                bundle.putString("id",mId);
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(),"");
                break;
        }

    }

    /**
     * 显示影院信息
     * @param cinemaDetailBeans
     */
    @Override
    public void success(CinemaDetailBean cinemaDetailBeans) {
        cinemaDetailBeans.getResult();
        mCinema_detail_cinema_name.setText(cinemaDetailBeans.getResult().getName());
        Glide.with(CinemaDetailActivity.this).load(cinemaDetailBeans.getResult().getLogo()).into(mCinema_detail_cinema_icon);
        mCinema_detail_cinema_address.setText(cinemaDetailBeans.getResult().getAddress());
        //影院名称
        mCinemaName = cinemaDetailBeans.getResult().getName();
        //影院地址
        mCinemaAddress = cinemaDetailBeans.getResult().getAddress();

    }

    @Override
    public void successIcon(List<CinemaDetailIconBean.ResultBean> cinemaDetailIconBean) {
        mCinemaDetailAdapter.setData(cinemaDetailIconBean);
        initSchedule(0);
    }

    /**
     * 影院影片的排期
     * @param cinemaDetailScheduleBeans
     */
    @Override
    public void successSchedule(List<CinemaDetailScheduleBean.ResultBean> cinemaDetailScheduleBeans) {
            mCinemaDetailScheduleAdapter.setData(cinemaDetailScheduleBeans);
    }


    @Override
    public void error(String msg) {
        showToast(msg);
    }



}
