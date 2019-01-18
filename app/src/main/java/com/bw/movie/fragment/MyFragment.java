package com.bw.movie.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.MainActivity;
import com.bw.movie.R;
import com.bw.movie.activity.MyFragmentReMindActivity;
import com.bw.movie.adapter.MyFragmentListViewAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPFragment;
import com.bw.movie.bean.MyFragmentBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.myview.ParallaxListView;
import com.bw.movie.presenter.MyFragmentPresenter;
import com.bw.movie.view.MyFragmentView;
import com.greendao.gen.UserBeanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/4
 * author:赵豪轩
 * function:
 */
public class MyFragment extends BaseMVPFragment<MyFragmentView, MyFragmentPresenter> {

    private ParallaxListView parallaxlistview;
    private ImageView iv_header;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    TextView iv_name;
    ImageView iv_img;
    private ImageView myfragment_remind;
    @Override
    protected MyFragmentPresenter initPresenter() {
        return new MyFragmentPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.my_fragment;
    }

    /**
     * 初始化View
     *
     * @param view
     */
    @Override
    protected void initView(View view) {
        parallaxlistview = view.findViewById(R.id.parallaxlistview);
        myfragment_remind = view.findViewById(R.id.myfragment_remind);
    }

    /**
     * 初始化数据
     *
     * @param view
     */
    @Override
    protected void initData(View view) {
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        //获取外部的布局
        View head = View.inflate(getContext(), R.layout.myfragment_layout_header, null);
        //给listview添加头布局
        parallaxlistview.addHeaderView(head);
        //获取头布局中的图片控件
        iv_header = head.findViewById(R.id.iv_header);
        iv_img = head.findViewById(R.id.iv_img);
        iv_name = head.findViewById(R.id.iv_name);

        iv_name.setText(mUserBeans.get(0).getNickName());
        iv_name.setTextColor(Color.WHITE);
        //圆形
        RequestManager with = Glide.with(this);
        with.load(mUserBeans.get(0).getHeadPic())
                .apply(new RequestOptions().transform(new CircleCrop()))
                .into(iv_img);
      // Glide.with(this).load(mUserBeans.get(0).getHeadPic()).into(iv_img);
        /**
         * 我们知道在oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，
         * 这是因为View组件布局要在onResume回调后完成。
         * 所以现在需要使用getViewTreeObserver().addOnGlobalLayoutListener()来获得宽度或者高度
         * 。这是获得一个view的宽度和高度的方法之一。
         * OnGlobalLayoutListener 是ViewTreeObserver的内部类，
         * 当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到，
         * 这是一个注册监听视图树的观察者(observer)，在视图树的全局事件改变时得到通知。
         * ViewTreeObserver不能直接实例化，而是通过getViewTreeObserver()获得。
         *
         * 但是需要注意的是OnGlobalLayoutListener可能会被多次触发，因此在得到了高度之后，要将OnGlobalLayoutListener注销掉。
         */
        iv_header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //把控件传到自定义
                parallaxlistview.setView(iv_header);
                //释放资源
                iv_header.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        List<MyFragmentBean> strings = new ArrayList<>();
        strings.add(new MyFragmentBean("我的信息",null,R.mipmap.my_icon_messiage_default,1));
        strings.add(new MyFragmentBean("我的关注","购票记录",R.mipmap.my_icon_attention_default,R.mipmap.my_icon_rccord_default));
        strings.add(new MyFragmentBean("意见反馈","检查更新",R.mipmap.my_icon_feedback_default,R.mipmap.my_icon_version_default));

        MyFragmentListViewAdapter myFragmentListViewAdapter = new MyFragmentListViewAdapter(getActivity(), strings);
        parallaxlistview.setAdapter(myFragmentListViewAdapter);
    }
    /**
     * 设置监听
     *
     * @param view
     */
    @Override
    protected void setListener(View view) {
        myfragment_remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MyFragmentReMindActivity.class));
            }
        });
    }
}
