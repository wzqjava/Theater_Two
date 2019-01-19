package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.PlayDetailRecyclerviewPaiQi;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.bean.PlayDetailPaiQiRecyclerViewBean;
import com.bw.movie.bean.SelectThrastersRecyclerViewBean;
import com.bw.movie.presenter.PlayDetailPresenter;
import com.bw.movie.view.PlayDetailIdView;
import com.bw.movie.view.PlayDetailView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

public class PlayDetailActivity extends BaseMVPActivity<PlayDetailView, PlayDetailPresenter> implements PlayDetailView{

    private TextView play_detail_yingyuan_name;
    private TextView play_detail_yingyuan_address;
    private ImageView play_detail_yingpian_img;
    private TextView play_detail_yingpian_name;
    private TextView play_detail_yingpian_leixing;
    private TextView play_detail_yingpian_daoyan;
    private TextView play_detail_yingpian_shichang;
    private TextView play_detail_yingpian_chandi;
    private RecyclerView play_detail_recyclerview;
    private ImageView play_detail_fanhui;
    private int mYingYuanId;
    private int mYingPianId;
    private String mAddress;
    private String mName;
    private String mYingPianName;

    /**
     * 初始化presenter
     *
     * @return
     */
    @Override
    protected PlayDetailPresenter initPresenter() {
        return new PlayDetailPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_play_detail;
    }

    //跳转
    public static void start(Context context) {
        context.startActivity(new Intent(context, PlayDetailActivity.class));
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        play_detail_yingyuan_name  = findViewById(R.id.play_detail_yingyuan_name);
        play_detail_yingyuan_address  = findViewById(R.id.play_detail_yingyuan_address);
        play_detail_yingpian_img  = findViewById(R.id.play_detail_yingpian_img);
        play_detail_yingpian_name  = findViewById(R.id.play_detail_yingpian_name);
        play_detail_yingpian_leixing  = findViewById(R.id.play_detail_yingpian_leixing);
        play_detail_yingpian_daoyan  = findViewById(R.id.play_detail_yingpian_daoyan);
        play_detail_yingpian_shichang  = findViewById(R.id.play_detail_yingpian_shichang);
        play_detail_yingpian_chandi  = findViewById(R.id.play_detail_yingpian_chandi);
        play_detail_recyclerview  = findViewById(R.id.play_detail_recyclerview);
        play_detail_fanhui  = findViewById(R.id.play_detail_fanhui);
    }
    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void even(Detail_Detail_Bean.ResultBean resultBean) {
        Log.e("zhx"+"播放详情", resultBean.getName()+ "");
        mYingPianName = resultBean.getName();
        play_detail_yingpian_name.setText(resultBean.getName()+"");
        play_detail_yingpian_leixing.setText("类型："+resultBean.getMovieTypes()+"");
        play_detail_yingpian_chandi.setText("产地："+resultBean.getPlaceOrigin()+"");
        play_detail_yingpian_shichang.setText("时长："+resultBean.getDuration()+"");
        play_detail_yingpian_daoyan.setText("导演："+resultBean.getDirector()+"");
        Glide.with(this).load(resultBean.getImageUrl()+"").into(play_detail_yingpian_img);
        mYingPianId = resultBean.getId();
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void even(SelectThrastersRecyclerViewBean.ResultBean resultBean) {
        mYingYuanId = resultBean.getId();
        mAddress = resultBean.getAddress();
        mName = resultBean.getName();
        Log.e("zhx"+"播放详情", resultBean.getAddress() + "");
        play_detail_yingyuan_name.setText(resultBean.getName()+"");
        play_detail_yingyuan_address.setText(resultBean.getAddress()+"");

        boolean aNull = presenter.isNull(mYingYuanId + "", mYingPianId + "");
        Log.e("zhx",mYingPianId+"      "+mYingYuanId);
        Log.e("zhx",aNull+"");
        if (!aNull){
            HashMap<String, String> map = new HashMap<>();
            map.put("cinemasId",mYingYuanId+"");
            map.put("movieId",mYingPianId+"");
            presenter.getPaiQiData(map);
        }


    }
    /**
     * 设置监听
     */
    @Override
    protected void setListener() {
        play_detail_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void success(List<PlayDetailPaiQiRecyclerViewBean.ResultBean> result) {
        Log.e("zhx"," paiqi:     "+result.get(0).getScreeningHall()+"");
        PlayDetailRecyclerviewPaiQi playDetailRecyclerviewPaiQi = new PlayDetailRecyclerviewPaiQi(this, result);
        play_detail_recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        play_detail_recyclerview.setAdapter(playDetailRecyclerviewPaiQi);

        playDetailRecyclerviewPaiQi.setPlayDetailIdView(new PlayDetailIdView() {
            @Override
            public void success(String id, PlayDetailPaiQiRecyclerViewBean.ResultBean resultBean) {
                    Intent intent = new Intent(PlayDetailActivity.this,ChooseActivity.class);

                    intent.putExtra("scheduleId",id);
                    intent.putExtra("cinemaName",mName);
                    intent.putExtra("cinemaAddress",mAddress);
                    intent.putExtra("movieName",mYingPianName);
                    intent.putExtra("movieHall",resultBean.getScreeningHall());
                    intent.putExtra("movieBegin",resultBean.getBeginTime());
                    intent.putExtra("movieEnd",resultBean.getEndTime());
                    intent.putExtra("price",resultBean.getPrice()+"");
                    startActivity(intent);

            }
        });
    }

    @Override
    public void error(String msg) {
        Log.e("zhx","paiqi:"+msg);
    }
}
