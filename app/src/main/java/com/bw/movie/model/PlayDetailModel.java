package com.bw.movie.model;

import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.bean.PlayDetailPaiQiRecyclerViewBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.server.DefaultDisposable;
import com.bw.movie.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/11
 * author:赵豪轩(xuan)
 * function:
 */
public class PlayDetailModel {
    private Disposable mDisposable = new DefaultDisposable();

    public void getPaiQiData(HashMap<String,String> map, DisposableObserver<PlayDetailPaiQiRecyclerViewBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getPaiQi(map)
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
