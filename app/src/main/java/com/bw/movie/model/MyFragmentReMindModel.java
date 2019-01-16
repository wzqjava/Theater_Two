package com.bw.movie.model;

import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.bean.MyFragmentReMindRecyclerViewBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.server.DefaultDisposable;
import com.bw.movie.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/15
 * author:赵豪轩(xuan)
 * function:
 */
public class MyFragmentReMindModel {
    private Disposable mDisposable = new DefaultDisposable();

    public void getReMindRecyclerViewData(String userId, String sessionId, HashMap<String,String> map, DisposableObserver<MyFragmentReMindRecyclerViewBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getReMindRecyclerView(userId,sessionId,map)
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
