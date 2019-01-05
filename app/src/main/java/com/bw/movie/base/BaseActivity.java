package com.bw.movie.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.bw.movie.activity.NetWorkActivity;
import com.bw.movie.net.Constans;


/**
 * date:2018/12/27
 * author:赵豪轩(xuan)
 * function:
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setView());
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

    /**
     * 判断权限是否已经配置了，
     *     String...    意思是可以传入多个字符串
     * @param permissions
     * @return
     */
    public boolean hasPermissions(String... permissions){
        for (String permission : permissions){
            if (ContextCompat.checkSelfPermission(this,permission)
                    != PackageManager.PERMISSION_GRANTED){
                    return true;
            }
        }
        return false;
    }

    /**
     * 申请权限
         * code 是请求码
         * permissions 是要申请的权限
     *
     * @param code
     * @param permissions
     */
    public void requestPermissions(int code, String... permissions){
        ActivityCompat.requestPermissions(this,
                permissions,code);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            //打电话的权限
            case Constans.CALL_PHONE:
                //判断打电话申请权限是否成功,成功就执行打电话的逻辑
                //注意:因为集合里只有一个权限申请,所以参数为0代表打电话权限
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //执行打电话的操作
                    doCall();
                }else{
                    //获取失败，提示、、
                    showToast("亲，获取授权失败了哦！！！");
                }
                break;
            case Constans.WRITE_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //
                    doSDCard();
                }else{
                    //获取失败，提示、、
                    showToast("亲，获取授权失败了哦！！！");
                }
                break;
        }
    }
    /**
     * 若打电话权限已经存在，执行以下方法
     */
    public void doCall() {}
    /**
     * 若SD卡写的权限已经存在，执行以下方法
     */
    public void doSDCard() {}

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
    protected void onDestroy() {
        super.onDestroy();

    }
}
