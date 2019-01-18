package com.bw.movie.model;

import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.server.APIServer;
import com.bw.movie.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/8
 * author:赵豪轩(xuan)
 * function:
 */
public class DetailModel {

    private Disposable mDisposable = new DefaultDisposable();

    public void getData(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<Detail_Detail_Bean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getDetail_Detail(userId,sessionId,map)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }
    public void getPingLunData(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<DetailPingLunBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getDetail_PingLun(userId,sessionId,map)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }


    public boolean isDisposable() {
        return mDisposable.isDisposed();

    }
    public void disposable(){
        mDisposable.dispose();
    }
}
