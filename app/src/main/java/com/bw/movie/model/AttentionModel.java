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
 * date:2019/1/17
 * author:李壮(大壮)
 * function:关注Model层
 */
public class AttentionModel {
    private Disposable mDisposable = new DefaultDisposable();

    public void getAttention(Map<String,String> headerParams, Map<String,String> queryParams,
                                 DisposableObserver<BaseResponse> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getAttention(headerParams,queryParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        mDisposable = observer;
    }

    public boolean idDisposable(){
        return true;
    }

    public void disposable(){
        mDisposable.dispose();
    }
}
