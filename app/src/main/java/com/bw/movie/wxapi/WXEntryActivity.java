package com.bw.movie.wxapi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private String mCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);

        MyApplication.api.handleIntent(getIntent(), this);

    }


    @Override
    public void onResp(BaseResp resp) {
        String result = "";
        if(resp.errCode==0){
            SendAuth.Resp req = (SendAuth.Resp)resp;
            EventBus.getDefault().post(req);
            finish();
        }
    }

    @Override
    public void onReq(BaseReq arg0) {

    }
}
