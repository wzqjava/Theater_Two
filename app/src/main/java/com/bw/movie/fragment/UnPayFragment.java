package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.TicketActivity;
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
 * function:未支付页面
 */
public class UnPayFragment extends BaseMVPFragment<PayTicketView,PayTicketPresenter> implements PayTicketView {

    private XRecyclerView mUnpay_fragment_xrecyclerview;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private Map<String, String> mQueryParams;
    private Map<String, String> mHeaderParams;
    private UnPayTicketAdapter mUnPayTicketAdapter;

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
        return R.layout.unpay_fragment_item;
    }

    /**
     * 初始化资源控件id
     * @param view
     */
    @Override
    protected void initView(View view) {
        mUnpay_fragment_xrecyclerview = view.findViewById(R.id.unpay_fragment_xrecyclerview);
        //创建未支付适配器
        mUnPayTicketAdapter = new UnPayTicketAdapter(getActivity());
        mUnpay_fragment_xrecyclerview.setAdapter(mUnPayTicketAdapter);
        mUnpay_fragment_xrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false));

        mUnpay_fragment_xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
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
     * @param view
     */
    @Override
    protected void initData(View view) {
        mHeaderParams = new HashMap<>();
        mQueryParams = new HashMap<>();
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        mHeaderParams.put("userId",mUserBeans.get(0).getUserId());
        mHeaderParams.put("sessionId",mUserBeans.get(0).getSessionId());
        mQueryParams.put("status","1");

        presenter.refreshData(mHeaderParams,mQueryParams);
        /**
         * 调起支付
         */
        mUnPayTicketAdapter.setOnClickPayListener(new UnPayTicketAdapter.OnClickPayListener() {
            @Override
            public void onClickPay(double price, String orderId) {
                ChooseDialog chooseDialog = new ChooseDialog();
                Bundle bundle = new Bundle();
                bundle.putString("price", String.valueOf(price));
                bundle.putString("orderId",orderId);
                chooseDialog.setArguments(bundle);
                chooseDialog.show(getChildFragmentManager(),"");
            }
        });


    }

    /**
     * 设置监听
     * @param view
     */
    @Override
    protected void setListener(View view) {
    }



    @Override
    public void success(boolean isRefresh, List<QueryTicketBean.ResultBean> resultBeans) {
        if (isRefresh){
            mUnPayTicketAdapter.setData(resultBeans);
        }else {
            mUnPayTicketAdapter.addData(resultBeans);
        }

    }

    @Override
    public void error(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
        mUnpay_fragment_xrecyclerview.loadMoreComplete();
        mUnpay_fragment_xrecyclerview.refreshComplete();
    }
}
