package com.bw.movie.wxapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bw.movie.app.MyApplication;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class AppRegister extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
		msgApi.registerApp(MyApplication.APP_ID);
	}
}
