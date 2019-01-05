package com.bw.movie.model;

import com.bw.movie.bean.RegisterBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.server.DefaultDisposable;
import com.bw.movie.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/4
 * author:赵豪轩(xuan)
 * function:
 */
public class RegisterModel {

    private Disposable mDisposable = new DefaultDisposable();

    public void getData(HashMap<String,String> map, DisposableObserver<RegisterBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getRegister(map)
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
