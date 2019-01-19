package com.bw.movie.utils;

import java.util.HashMap;
import java.util.Map;

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
    private HashMap<String, String> sMap = new HashMap<>();

    private OkHttpUtils(){
        sMap.put("ak", "0110010010000");
        //application/x-www-form-urlencoded
        sMap.put("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
        //sMap.put("Content-Type","application/x-www-form-urlencoded");
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

    OkHttpClient.Builder generateDefaultBuilder(){
        //链接时间
        OkHttpClient.Builder builder = new OkHttpClient
                .Builder()
                //添加日志拦截器
                .addInterceptor(new LogginInterceptor(sMap))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder;
    }

    void updateUserId(String userId, String sessionId) {
        sMap.put("userId", userId);
        sMap.put("sessionId", sessionId);
    }
}
