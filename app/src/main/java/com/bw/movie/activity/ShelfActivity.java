package com.bw.movie.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.fragment.CinemaFregment;
import com.bw.movie.fragment.MovieFregment;
import com.bw.movie.fragment.MyFragment;

import java.util.ArrayList;

public class ShelfActivity extends AppCompatActivity {

    private ViewPager view_change;
    private RadioButton btn_movie;
    private RadioButton btn_cinema;
    private RadioButton btn_my;
    private RadioGroup group;
    ArrayList<Fragment> mFragments ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_shelf);

        initView();

        initData();
    }

    private void initData() {

        mFragments = new ArrayList<Fragment>();

        mFragments.add(new MovieFregment());
        mFragments.add(new CinemaFregment());
        mFragments.add(new MyFragment());

        view_change.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return mFragments.get(arg0);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mFragments.size();
            }

        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.btn_movie:
                        view_change.setCurrentItem(0);
                        break;
                    case R.id.btn_cinema:
                        view_change.setCurrentItem(1);
                        break;
                    case R.id.btn_my:
                        view_change.setCurrentItem(2);
                        break;

                    default:
                        break;
                }
            }
        });

        view_change.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                switch (arg0) {
                    case 0:
                        group.check(R.id.btn_movie);
                        btn_movie.setBackground(getResources().getDrawable(R.mipmap.com_icon_film_selected));
                        btn_cinema.setBackground(getResources().getDrawable(R.mipmap.com_icon_cinema_default));
                        btn_my.setBackground(getResources().getDrawable(R.mipmap.com_icon_my_default));

                        break;
                    case 1:
                        group.check(R.id.btn_cinema);
                        btn_movie.setBackground(getResources().getDrawable(R.mipmap.com_icon_film_fault));
                        btn_cinema.setBackground(getResources().getDrawable(R.mipmap.com_icon_cinema_selected));
                        btn_my.setBackground(getResources().getDrawable(R.mipmap.com_icon_my_default));

                        break;
                    case 2:
                        group.check(R.id.btn_my);
                        btn_movie.setBackground(getResources().getDrawable(R.mipmap.com_icon_film_fault));
                        btn_cinema.setBackground(getResources().getDrawable(R.mipmap.com_icon_cinema_default));
                        btn_my.setBackground(getResources().getDrawable(R.mipmap.com_icon_my_selected));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void initView() {
        view_change = (ViewPager) findViewById(R.id.view_change);
        btn_movie = (RadioButton) findViewById(R.id.btn_movie);
        btn_cinema = (RadioButton) findViewById(R.id.btn_cinema);
        btn_my = (RadioButton) findViewById(R.id.btn_my);
        group = (RadioGroup) findViewById(R.id.group);
    }


}
