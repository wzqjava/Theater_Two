package com.bw.movie.activity;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

//降级处理

/**
 *
 *
 * 当用户没有网络的时候去跳转到本页面
 */
public class NetWorkActivity extends Activity {

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work);
    }
}
