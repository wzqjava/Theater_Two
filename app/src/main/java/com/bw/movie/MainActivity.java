package com.bw.movie;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.activity.CountDownActivity;
import com.bw.movie.adapter.ViewPager_Adapter_Main;
import com.bw.movie.base.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private ViewPager welcome_viewpager;
    private Button welcome_start;
    private RadioButton mMain_rb01,mMain_rb02,mMain_rb03,mMain_rb04;
    private RadioGroup mMain_group;

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

        mMain_rb01 = findViewById(R.id.main_rb01);
        mMain_rb02 = findViewById(R.id.main_rb02);
        mMain_rb03 = findViewById(R.id.main_rb03);
        mMain_rb04 = findViewById(R.id.main_rb04);
        mMain_group = findViewById(R.id.main_group);

        initGroup();

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

    private void initGroup() {
        mMain_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_rb01:
                        welcome_viewpager.setCurrentItem(0);
                        break;
                    case R.id.main_rb02:
                        welcome_viewpager.setCurrentItem(1);
                        break;
                    case R.id.main_rb03:
                        welcome_viewpager.setCurrentItem(2);
                        break;
                    case R.id.main_rb04:
                        welcome_viewpager.setCurrentItem(3);
                        break;
                }
            }
        });

        welcome_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                 switch (i){
                     case 0:
                         mMain_group.check(R.id.main_rb01);
                         break;
                     case 1:
                         mMain_group.check(R.id.main_rb02);
                         break;
                     case 2:
                         mMain_group.check(R.id.main_rb03);
                         break;
                     case 3:
                         mMain_group.check(R.id.main_rb04);
                         break;
                 }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

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
        list.add(R.mipmap.welcome_01);
        list.add(R.mipmap.welcome_02);
        list.add(R.mipmap.welcome_03);
        list.add(R.mipmap.icon);

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
