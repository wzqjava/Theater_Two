package com.bw.movie.model;

import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.bean.SelectThrastersRecyclerViewBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.server.DefaultDisposable;
import com.bw.movie.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/10
 * author:赵豪轩(xuan)
 * function:
 */
public class SelectTheatersModel {
    private Disposable mDisposable = new DefaultDisposable();

    public void getSelectTheatersData(HashMap<String,String> map, DisposableObserver<SelectThrastersRecyclerViewBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getSelectThrathers(map)
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
