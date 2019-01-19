package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bw.movie.R;
import com.bw.movie.activity.CityActivity;
import com.bw.movie.activity.SearchActivity;
import com.bw.movie.bean.CityEventBusManager;
import com.bw.movie.bean.LatitudeLongitudeBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.reactivex.subjects.PublishSubject;

/**
 * date:2019/1/4
 * author:李壮(大壮)
 * function:------------------影院页面---------------
 */
public class CinemaFregment extends Fragment implements AMapLocationListener, View.OnClickListener {

    private ViewPager cinema_pager;
    private ArrayList<Fragment> mFragments;
    private RadioGroup cinema_group;
    private RadioButton cinema_bt_recommend;
    private RadioButton cinema_bt_nearby;
    private View mCinema_Location;
    private TextView mCinema_name;
    private ImageView mSearch;

    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.cinema_fragment, null);
        initView(view);
        //注册EventBus
        EventBus.getDefault().register(this);
        //初始化数据
        initData();

        //初始化侧边索引控件
        initBroadside();
        return view;
    }

    /**
     * 设置侧边索引
     */
    private void initBroadside() {
        mCinema_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CityActivity.class);
                startActivity(intent);
            }
        });
    }

    //加载数据
    private void initData() {
        //跳转搜索页面
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void setCityMessageEventBus(CityEventBusManager cityMessageEventBus) {
        mCinema_name.setText(cityMessageEventBus.getName());
    }

    //加载定位信息
    private void initLocation() {

        mlocationClient = new AMapLocationClient(getActivity());
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setNeedAddress(true);
//设置定位监听
        mlocationClient.setLocationListener(this);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                stopLocation();
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                //怎么拿到城市名称
                //将经纬度传到下级页面
                publishSubject.onNext(new LatitudeLongitudeBean(amapLocation.getLatitude(), amapLocation.getLongitude()));
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    //事件发布者
    private PublishSubject<LatitudeLongitudeBean> publishSubject = PublishSubject.create();

    private void initView(View view) {
        cinema_pager = (ViewPager) view.findViewById(R.id.cinema_pager);
        cinema_bt_recommend = (RadioButton) view.findViewById(R.id.cinema_bt_recommend);
        cinema_bt_nearby = (RadioButton) view.findViewById(R.id.cinema_bt_nearby);
        cinema_group = (RadioGroup) view.findViewById(R.id.cinema_group);
        mCinema_name = view.findViewById(R.id.cinema_name);
        mCinema_name.setOnClickListener(this);
        mSearch = view.findViewById(R.id.search);
        //影院资源id
        mCinema_Location = view.findViewById(R.id.cinema_location);
        mFragments = new ArrayList<>();

        RecommendFragment recommendFragment = new RecommendFragment();
        recommendFragment.initPublishSubject(publishSubject);

        NearbyFragment nearbyFragment = new NearbyFragment();
        nearbyFragment.initPublishSubject(publishSubject);


        mFragments.add(recommendFragment);
        mFragments.add(nearbyFragment);

        cinema_pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
        cinema_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        cinema_group.check(R.id.cinema_bt_recommend);
                        break;
                    case 1:
                        cinema_group.check(R.id.cinema_bt_nearby);
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

        cinema_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cinema_bt_recommend:
                        cinema_pager.setCurrentItem(0);
                        break;
                    case R.id.cinema_bt_nearby:
                        cinema_pager.setCurrentItem(1);
                        break;
                }
            }
        });

        initLocation();
    }

    /**
     * 停止定位
     */
    private void stopLocation() {
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocation();
        //解除EventBus注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {

    }
}
