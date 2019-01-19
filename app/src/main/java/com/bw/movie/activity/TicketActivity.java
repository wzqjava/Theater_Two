package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.PayTicketAdapter;
import com.bw.movie.adapter.UnPayTicketAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.QueryTicketBean;
import com.bw.movie.dialog.ChooseDialog;
import com.bw.movie.fragment.PayFragment;
import com.bw.movie.fragment.UnPayFragment;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.PayTicketPresenter;
import com.bw.movie.view.PayTicketView;
import com.greendao.gen.UserBeanDao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 展示购票记录
 */
public class TicketActivity extends BaseMVPActivity<PayTicketView,PayTicketPresenter> implements PayTicketView{

    private RadioButton mTicket_unpay;
    private RadioButton mTicket_pay;

    private RadioGroup mTicket_group;
    private ImageView mTicket_back;
    private PayTicketAdapter mPayTicketAdapter;
    private ViewPager mTicket_viewpager;
    private ArrayList<Fragment> mFragments;

    @Override
    protected PayTicketPresenter initPresenter() {
        return new PayTicketPresenter();
    }


    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_ticket;
    }

    //初始化控件资源id
    @Override
    protected void initView() {
        mTicket_unpay = findViewById(R.id.ticket_unpay);
        mTicket_pay = findViewById(R.id.ticket_pay);
        mTicket_group = findViewById(R.id.ticket_group);
        mTicket_back = findViewById(R.id.ticket_back);
        mTicket_viewpager = findViewById(R.id.ticket_viewpager);

        mFragments = new ArrayList<>();
        //未付款
        mFragments.add(new UnPayFragment());
        //已完成
        mFragments.add(new PayFragment());
        mTicket_viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });


        /**
         * Viewpager 滑动监听
         *
         */
        mTicket_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTicket_group.check(R.id.ticket_unpay);
                        break;
                    case 1:
                        mTicket_group.check(R.id.ticket_pay);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /**
         * 按钮选中监听
         */

        mTicket_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.ticket_unpay:
                        mTicket_viewpager.setCurrentItem(0);
                        break;
                    case R.id.ticket_pay:
                        mTicket_viewpager.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    /**
     * 设置监听
     */
    @Override
    protected void setListener() {
        /**
         * 返回
         */
        mTicket_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, TicketActivity.class));
    }

    /**
     * 请求成功
     * @param resultBeans
     */
    @Override
    public void success(boolean isRefresh, List<QueryTicketBean.ResultBean> resultBeans) {

    }

    /**
     * 请求失败
     * @param msg
     */
    @Override
    public void error(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {

    }
}
