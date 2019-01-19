package com.bw.movie.fragment;

import android.os.Bundle;
import android.os.MessageQueue;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.PayTicketAdapter;
import com.bw.movie.adapter.UnPayTicketAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPFragment;
import com.bw.movie.bean.QueryTicketBean;
import com.bw.movie.dialog.ChooseDialog;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.PayTicketPresenter;
import com.bw.movie.view.PayTicketView;
import com.greendao.gen.UserBeanDao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date:2019/1/19
 * author:李壮(大壮)
 * function:已完成页面
 */
public class PayFragment extends BaseMVPFragment<PayTicketView,PayTicketPresenter> implements PayTicketView {
    private XRecyclerView mPay_fragment_xrecyclerview;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private Map<String, String> mHeaderParams;
    private Map<String, String> mQueryParams;
    private PayTicketAdapter mPayTicketAdapter;

    @Override
    protected PayTicketPresenter initPresenter() {
        return new PayTicketPresenter();
    }

    /**
     * 初始化视图
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.pay_fragment_item;
    }

    /**
     * 初始化资源控件id
     *
     * @param view
     */
    @Override
    protected void initView(View view) {
        mPay_fragment_xrecyclerview = view.findViewById(R.id.pay_fragment_xrecyclerview);
        //创建未支付适配器
        mPayTicketAdapter = new PayTicketAdapter(getActivity());
        mPay_fragment_xrecyclerview.setAdapter(mPayTicketAdapter);
        mPay_fragment_xrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));

        mPay_fragment_xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                presenter.refreshData(mHeaderParams,mQueryParams);
            }

            @Override
            public void onLoadMore() {
               //加载
                presenter.loadData(mHeaderParams,mQueryParams);
            }
        });

    }

    /**
     * 初始化数据
     *
     * @param view
     */
    @Override
    protected void initData(View view) {
        mHeaderParams = new HashMap<>();
        mQueryParams = new HashMap<>();
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        mHeaderParams.put("userId", mUserBeans.get(0).getUserId());
        mHeaderParams.put("sessionId", mUserBeans.get(0).getSessionId());
        mQueryParams.put("status","2");

        presenter.refreshData(mHeaderParams,mQueryParams);
    }

    /**
     * 设置监听
     *
     * @param view
     */
    @Override
    protected void setListener(View view) {
    }


    @Override
    public void success(boolean isRefresh, List<QueryTicketBean.ResultBean> resultBeans) {
        if (isRefresh){
            mPayTicketAdapter.setData(resultBeans);
        }else {
            mPayTicketAdapter.addData(resultBeans);
        }

    }

    @Override
    public void error(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
        mPay_fragment_xrecyclerview.refreshComplete();
        mPay_fragment_xrecyclerview.loadMoreComplete();
    }
}
