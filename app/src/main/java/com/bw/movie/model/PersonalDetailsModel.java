package com.bw.movie.model;

import com.bw.movie.bean.PingLunPingLunBean;
import com.bw.movie.bean.ShangChuanHeadPicBean;
import com.bw.movie.bean.UserIDChaXunBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.utils.RetrofitUtils;

import java.io.File;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * date:2019/1/18
 * author:赵豪轩(xuan)
 * function:
 */
public class PersonalDetailsModel {

    private Disposable mDisposable = new DefaultDisposable();
    public void getChaXunData(String userId, String sessionId, DisposableObserver<UserIDChaXunBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getChaXun(userId,sessionId)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }

    public void getShangChuanHeadPicData(String userId, String sessionId, HashMap<String, RequestBody> map, DisposableObserver<ShangChuanHeadPicBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getShangChuanHeadPic(userId,sessionId,map)
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
