package com.bw.movie.model;

import com.bw.movie.bean.CinemaDetailCinemaBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:影院详情展示影院信息的Model层
 */
public class CinemaDetailCinemaModel {
    private Disposable mDisposable = new DefaultDisposable();

    public void getCinemaMessage(Map<String,String> headerParams, Map<String,String> queryParams,
                                 DisposableObserver<CinemaDetailCinemaBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getCinemaMessage(headerParams,queryParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        mDisposable = observer;
    }

    public boolean idDisposable(){
        return mDisposable.isDisposed();
    }

    public void disposable(){
        mDisposable.dispose();
    }
}
