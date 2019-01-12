package com.bw.movie.model;

import com.bw.movie.bean.CinemaCommendBean;
import com.bw.movie.bean.CinemaDetailCinemaBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.server.DefaultDisposable;
import com.bw.movie.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:影院评论的Model层
 */
public class CinemaCommendModel {

    private Disposable mDisposable = new DefaultDisposable();

    public void getCinemaCommmend(Map<String,String> headerParams,Map<String,String> queryParams,
                                  DisposableObserver<CinemaCommendBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getCinemaCommend(headerParams,queryParams)
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
