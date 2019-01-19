package com.bw.movie.model;

import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.QueryTicketBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/18
 * author:李壮(大壮)
 * function:购票记录的Model层
 */
public class PayTicketModel {
    private Disposable mDisposable = new DefaultDisposable();

    public void getQueryTicket(Map<String, String> headerParams, Map<String, String> queryParams,
                             DisposableObserver<QueryTicketBean> observer){
        mDisposable = RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getQueryTicket(headerParams, queryParams)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(observer);
    }


    public boolean isDisposable() {
        return true;

    }
    public void disposable(){
        mDisposable.dispose();
    }
}
