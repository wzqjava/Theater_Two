package com.bw.movie.model;

import com.bw.movie.bean.CinemaDetailScheduleBean;
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
 * function:当前影院影片的排期
 */
public class CinemaDetailScheduleModel {

    private Disposable mDisposable = new DefaultDisposable();

    public void getDetailSchedule(Map<String,String> queryParams, DisposableObserver<CinemaDetailScheduleBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getDetailSchedule(queryParams)
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
