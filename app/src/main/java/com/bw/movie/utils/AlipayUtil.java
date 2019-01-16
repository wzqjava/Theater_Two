package com.bw.movie.utils;

import android.content.Context;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * date:2019/1/12
 * author:李壮(大壮)
 * function:支付宝支付工具类
 */
public class AlipayUtil {
    public final static String APP_ID = "wxb3852e6a6b7d9516";

    private IWXAPI api;

    private static AlipayUtil instance;

    public static void init(Context context) {
        instance = new AlipayUtil(context);
    }

    public static AlipayUtil getInstance() {
        if (instance == null) {
            //////
            throw new RuntimeException("没有初始化: init(Context)");
        }
        return instance;
    }

    public AlipayUtil(Context context) {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(context, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

        //api.sendReq()
    }

    public IWXAPI getApi() {
        return api;
    }
}
