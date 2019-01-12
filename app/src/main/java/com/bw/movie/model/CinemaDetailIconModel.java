package com.bw.movie.model;

import com.bw.movie.bean.CinemaDetailBean;
import com.bw.movie.bean.CinemaDetailIconBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.server.DefaultDisposable;
import com.bw.movie.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/9
 * author:李壮(大壮)
 * function:
 */
public class CinemaDetailIconModel {

    private Disposable mDisposable = new DefaultDisposable();

    public void getDetailIcon(Map<String,String> queryParams, DisposableObserver<CinemaDetailIconBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getDetailIcon(queryParams)
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
