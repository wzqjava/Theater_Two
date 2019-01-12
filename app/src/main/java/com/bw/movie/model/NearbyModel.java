package com.bw.movie.model;

import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.server.DefaultDisposable;
import com.bw.movie.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/5
 * author:李壮(大壮)
 * function:附近影院M层
 */
public class NearbyModel {
    private Disposable mDisposable = new DefaultDisposable();

    public void getNearby(Map<String, String> headerParams, Map<String, String> queryParams, DisposableObserver<NearbyBean> observer){
        mDisposable = RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getNearby(headerParams, queryParams)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(observer);
    }


    public boolean isDisposable() {
        return mDisposable.isDisposed();

    }
    public void disposable(){
        mDisposable.dispose();
    }

}
