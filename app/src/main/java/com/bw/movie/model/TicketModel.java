package com.bw.movie.model;

import com.bw.movie.bean.MovieTicketBean;
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
 * function:下单业务的Model层
 */
public class TicketModel {
    private Disposable mDisposable = new DefaultDisposable();

    public void getMovieTicket(Map<String,String> headerParams, Map<String,String> queryParams,
                                  DisposableObserver<MovieTicketBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getTicket(headerParams,queryParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        mDisposable = observer;
    }
    public boolean idDisposable(){
        //return mDisposable.isDisposed();
        return true;
    }

    public void disposable(){
        mDisposable.dispose();
    }
}
