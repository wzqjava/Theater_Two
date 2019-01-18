package com.bw.movie.presenter;

import android.media.AsyncPlayer;
import android.os.Message;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.bw.movie.alipay.alipay;
import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.PayResponseBean;
import com.bw.movie.model.PayModel;
import com.bw.movie.utils.WechatUtil;
import com.bw.movie.view.PayView;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/14
 * author:李壮(大壮)
 * function:支付Presenter
 */
public class PayPresenter extends BaseMVPPresenter<PayView> {

    private final PayModel mPayModel;

    public PayPresenter(){
        mPayModel = new PayModel();
    }

    public void getPay(final Map<String, String> headerParams, final Map<String, String> queryParams){
        final int type = payType;
        if(!WechatUtil.getInstance().getApi().isWXAppInstalled() && type == WeatchPayType) {
            // showToast("没有安装微信");
            view.error("没有安装微信");
            return ;
        }

        queryParams.put("payType",type+"");
        //if (mPayModel.isDisposable()){
            mPayModel.getPay(headerParams, queryParams, new DisposableObserver<PayResponseBean>() {
                @Override
                public void onNext(PayResponseBean payResponseBean) {
                    if (type == WeatchPayType){
                        onPay(headerParams,queryParams,payResponseBean);
                        Log.e("lz",payResponseBean.toString());
                    }else if (type == AlipayPayType){
                        if (payResponseBean.isSuccess()){
                            onAlipayPay(payResponseBean.getResult());
                        }else {
                            view.error(payResponseBean.getMessage());
                        }

                    }

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        //}
    }

    /**
     * 微信支付
     * @param headerParams
     * @param queryParams
     * @param payResponseBean
     */
    public void onPay(Map<String,String> headerParams, Map<String,String> queryParams, PayResponseBean payResponseBean){

        //请求支付    需要订单号

        //微信支付     需要支付信息
        PayReq req = new PayReq();

       /* //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        PayResponseBean response = new Gson().fromJson("{\"appId\":\"wxb3852e6a6b7d9516\",\"message\":\"支付成功\",\"nonceStr\":\"3UsjMYFFVIIEBcSs\",\"packageValue\":\"Sign=WXPay\",\"partnerId\":\"1510865081\",\"prepayId\":\"wx12105521154763f0c71ab3b40808973703\",\"sign\":\"D45DB9ABCA9F3FD640819951E6A06E9D\",\"status\":\"0000\",\"timeStamp\":\"1547261735\"}", PayResponseBean.class);
*/

        req.appId = payResponseBean.getAppId();
        req.partnerId = payResponseBean.getPartnerId();
        req.prepayId = payResponseBean.getPrepayId();
        req.nonceStr = payResponseBean.getNonceStr();
        req.timeStamp = payResponseBean.getTimeStamp();
        req.packageValue = payResponseBean.getPackageValue();
        req.sign = payResponseBean.getSign();
        req.extData = "app data"; // optional
        //Toast.makeText(ChooseActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        WechatUtil.getInstance().getApi().sendReq(req);
    }

    /**
     * 支付宝支付
     */
    public void onAlipayPay(final String resultStr){

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                if (view != null){
                    PayTask alipay = new PayTask(view.getActivity());
                    String version = alipay.getVersion();
                    Map <String,String> result = alipay.payV2(resultStr,true);
                    Log.e("lz",result+"");
                   Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    view.getHandler().sendMessage(msg);
                }

            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    public final static int WeatchPayType = 1;
    public final static int AlipayPayType = 2;
    public final static int SDK_PAY_FLAG = 3;
    private int payType ;
    public void changePayType(int i) {
        payType = i;
    }
}
