package com.bw.movie;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.bw.movie.activity.CountDownActivity;
import com.bw.movie.adapter.ViewPager_Adapter_Main;
import com.bw.movie.base.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private ViewPager welcome_viewpager;
    private Button welcome_start;

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_main;
    }
    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        welcome_viewpager = (ViewPager) findViewById(R.id.welcome_viewpager);
        welcome_start = (Button) findViewById(R.id.welcome_start);
        welcome_start.setOnClickListener(this);

        welcome_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 3) {
                    welcome_start.setVisibility(View.VISIBLE);
                } else {
                    welcome_start.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        welcome_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jump();
            }
        });
    }

    private void Jump() {
        startActivity(new Intent(MainActivity.this, CountDownActivity.class));
        finish();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

        SharedPreferences first_time_into = getSharedPreferences("First_time_into", MODE_PRIVATE);

        if (first_time_into.getBoolean("isFirst", false)) {
            Jump();
        } else {
            SharedPreferences.Editor edit = first_time_into.edit();
            edit.putBoolean("isFirst", true);
            edit.commit();
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.welcome_vp1);
        list.add(R.drawable.welcome_vp2);
        list.add(R.drawable.welcome_vp3);
        list.add(R.drawable.welcome_vp4);

        welcome_viewpager.setAdapter(new ViewPager_Adapter_Main(MainActivity.this, list));

    }

    /**
     * 设置监听
     */
    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
