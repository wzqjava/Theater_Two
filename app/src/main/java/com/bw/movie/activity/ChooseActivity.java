package com.bw.movie.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.WechatPayResponse;
import com.bw.movie.presenter.ChoosePresenter;
import com.bw.movie.utils.WechatUtil;
import com.bw.movie.view.ChooseView;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 选座页面
 */
public class ChooseActivity extends BaseMVPActivity<ChooseView, ChoosePresenter> {

    private TextView mChoose_movie_name;

    @Override
    protected ChoosePresenter initPresenter() {
        return new ChoosePresenter();
    }

    /**
     * 初始化视图
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_choose;
    }

    /**
     * 初始化资源控件id
     */
    @Override
    protected void initView() {
        mChoose_movie_name = findViewById(R.id.choose_movie_name);
        findViewById(R.id.wechat_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!WechatUtil.getInstance().getApi().isWXAppInstalled()) {
                    showToast("没有安装微信");
                    return ;
                }

                //前提下单完成    订单号

                //请求支付    需要订单号

                //微信支付     需要支付信息
                PayReq req = new PayReq();
                //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                WechatPayResponse response = new Gson().fromJson("{\"appId\":\"wxb3852e6a6b7d9516\",\"message\":\"支付成功\",\"nonceStr\":\"3UsjMYFFVIIEBcSs\",\"packageValue\":\"Sign=WXPay\",\"partnerId\":\"1510865081\",\"prepayId\":\"wx12105521154763f0c71ab3b40808973703\",\"sign\":\"D45DB9ABCA9F3FD640819951E6A06E9D\",\"status\":\"0000\",\"timeStamp\":\"1547261735\"}", WechatPayResponse.class);

                req.appId = response.getAppId();
                req.partnerId = response.getPartnerId();
                req.prepayId = response.getPrepayId();
                req.nonceStr = response.getNonceStr();
                req.timeStamp = response.getTimeStamp();
                req.packageValue = response.getPackageValue();
                req.sign = response.getSign();
                req.extData = "app data"; // optional
                Toast.makeText(ChooseActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                WechatUtil.getInstance().getApi().sendReq(req);
            }
        });
    }

    /**
     * 加载数据
     */
    @Override
    protected void initData() {

    }

    /**
     * 设置监听
     */
    @Override
    protected void setListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
