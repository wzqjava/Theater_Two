package com.bw.movie.dialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.PayResponseBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.presenter.PayPresenter;
import com.bw.movie.view.PayView;
import com.greendao.gen.UserBeanDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import retrofit2.http.HeaderMap;

import static com.bw.movie.presenter.PayPresenter.AlipayPayType;
import static com.bw.movie.presenter.PayPresenter.WeatchPayType;
import static com.tencent.mm.opensdk.modelbase.BaseResp.ErrCode.ERR_OK;

/**
 * date:2019/1/15
 * author:李壮(大壮)
 * function:支付弹框
 */
public class ChooseDialog extends DialogFragment implements View.OnClickListener, PayView {

    private TextView mChoose_pay;
    private TextView mChoose_alipay_pay;
    private TextView mChoose_wechat_pay;
    private TextView mChoose_pay_tv;
    private String mPrice;
    private String mOrderId;
    private PayPresenter mPayPresenter;
    private String mUserId;
    private String mSessionId;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;
    private Map<String, String> mHeaderParams;
    private Map<String, String> mQueryParams;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.choose_pay_dialog,null);

        //初始化资源控件
        initView(view);

        //初始化数据
        initData();
        return view;
    }

    /**
     * 加载数据
     */
    private void initData() {


    }

    /**
     * 获取资源控件id
     */
    private void initView(View view) {
        mChoose_pay = view.findViewById(R.id.choose_pay);
        mChoose_wechat_pay = view.findViewById(R.id.choose_wechat_pay);
        mChoose_alipay_pay = view.findViewById(R.id.choose_alipay_pay);
        mChoose_pay_tv = view.findViewById(R.id.choose_pay_tv);
        mChoose_alipay_pay.setOnClickListener(this);
        mChoose_pay.setOnClickListener(this);
        mChoose_wechat_pay.setOnClickListener(this);
        mChoose_pay_tv.setOnClickListener(this);

        mHeaderParams = new HashMap<>();
        mQueryParams = new HashMap<>();
        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();
        mUserId = mUserBeans.get(0).getUserId();
        mSessionId = mUserBeans.get(0).getSessionId();
        mHeaderParams.put("userId",mUserId);
        mHeaderParams.put("sessionId",mSessionId);
        mQueryParams.put("orderId",mOrderId);

        changeSelect(mChoose_wechat_pay);
        mPayPresenter.changePayType(WeatchPayType);
        mChoose_pay_tv.setText("微信支付"+mPrice+"元");

    }

    /**
     * 设置对话框样式
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter intentFilter = new IntentFilter("bw.com.movie.WechatPayResult");
        //注册广播
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
               int code = intent.getIntExtra("result_code",-1);
               if (code == ERR_OK){
                   Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                   getActivity().finish();
               }else {
                   Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
               }
            }
        },intentFilter);
        mPayPresenter = new PayPresenter();
        mPayPresenter.attch(this);
        mPrice = getArguments().getString("price");
        mOrderId = getArguments().getString("orderId");
        setStyle(DialogFragment.STYLE_NORMAL, R.style.TranslucentNoTitleDialog);

    }

    @Override
    public void onClick(View v) {
           switch (v.getId()){
               //返回
               case R.id.choose_pay:
                   getActivity().finish();
                   dismiss();
                   break;
                   //微信支付
               case R.id.choose_wechat_pay:
                   mChoose_pay_tv.setText("微信支付"+mPrice+"元");
                   changeSelect(mChoose_wechat_pay);
                   mPayPresenter.changePayType(WeatchPayType);
                   break;
                   //支付宝支付
               case R.id.choose_alipay_pay:
                   mChoose_pay_tv.setText("支付宝支付"+mPrice+"元");
                   changeSelect(mChoose_alipay_pay);
                   mPayPresenter.changePayType(AlipayPayType);
                   break;
                   //去支付
               case R.id.choose_pay_tv:
                   mPayPresenter.getPay(mHeaderParams,mQueryParams);
                   break;
           }
    }

    private void changeSelect(TextView view) {
        mChoose_alipay_pay.setSelected(false);
        mChoose_wechat_pay.setSelected(false);

        view.setSelected(true);
    }

    @Override
    public void success(PayResponseBean payResponseBean) {

    }

    @Override
    public void error(String msg) {
        Toast.makeText(getActivity(), "没有安装微信", Toast.LENGTH_SHORT).show();
    }
}
