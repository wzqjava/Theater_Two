package com.bw.movie.utils;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * date:2019/1/2
 * author:赵豪轩(xuan)
 * function:
 */
public class OkHttpUtils {
    private static OkHttpUtils instance;
    protected OkHttpClient mOkHttpClient;

    private OkHttpUtils(){
        mOkHttpClient = generateDefaultBuilder().build();
    }

    //单例模式
    public static OkHttpUtils getInstance(){
        if (instance == null){
            synchronized (OkHttpUtils.class){
                instance = new OkHttpUtils();
            }
        }
        return instance;
    }

    static OkHttpClient.Builder generateDefaultBuilder(){

        HashMap<String, String> map = new HashMap<>();
        LogginInterceptor interceptor = new LogginInterceptor(map);
        //链接时间
        OkHttpClient.Builder builder = new OkHttpClient
                .Builder()
                //添加日志拦截器
                .addInterceptor(interceptor)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder;
    }
}
