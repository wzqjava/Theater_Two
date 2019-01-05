package com.bw.movie.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

/**
 * 倒计时页面
 */
public class CountDownActivity extends BaseActivity {

    private TextView mTv_Count_Down;
    private int NUM = 3;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NUM--;
            if (NUM <= 0){
                Intent intent= new Intent(CountDownActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }else {
                mTv_Count_Down.setText(NUM + "S");
                mHandler.sendEmptyMessageDelayed(1,1000);
            }

        }
    };

    /**
     * 初始化页面
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_count_down;
    }

    @Override
    protected void initView() {
        mTv_Count_Down = findViewById(R.id.tv_count_down);
    }

    @Override
    protected void initData() {
        mHandler.sendEmptyMessage(1);
    }

    @Override
    protected void setListener() {
        //点击倒计时可以跳过倒计时
        mTv_Count_Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CountDownActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(1);
    }

}
