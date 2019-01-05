package com.bw.movie.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.bw.movie.activity.NetWorkActivity;

/**
 * date:2019/1/2
 * author:赵豪轩(xuan)
 * function:
 */
public abstract class BaseMVPActivity<V,P extends BaseMVPPresenter> extends Activity {
    public P presenter;
    /**
     * 初始化presenter
     * @return
     */
    protected abstract P initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setView());
        presenter = initPresenter();
        initView();
        if (!isConnNet()){
            showToast("亲，您的手机没有网络呦！！！");
            Intent intent = new Intent(this,NetWorkActivity.class);
            startActivity(intent);
            finish();
        }else{
            initData();
            setListener();
        }
    }
    Toast mToast;
    /**
     * 吐司的方法
     * @param
     */
    public void showToast(String text){
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }
    /**
     * 设置布局
     * @return
     */
    protected abstract int setView();
    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected abstract void setListener();


    /**
     * 判断网络
     * @return
     */
    public boolean isConnNet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }
    @Override
    protected void onResume() {
        super.onResume();
        presenter.attch(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
        presenter  = null;
    }
}
