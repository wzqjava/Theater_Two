package com.bw.movie.model;

import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.bean.PingLunPingLunBean;
import com.bw.movie.bean.PingLunZanBean;
import com.bw.movie.bean.QuXiaoGuanZhuBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.server.DefaultDisposable;
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
    public void getQuXiaoData(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<QuXiaoGuanZhuBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getQuXiao(userId,sessionId,map)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }
    public void getGuanZhuData(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<QuXiaoGuanZhuBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getGuanZhu(userId,sessionId,map)
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

    public void getDianZanData(String userId, String sessionId, HashMap<String,String> map, DisposableObserver<PingLunZanBean> observer) {
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getdianzan(userId,sessionId,map)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }
    public void getPingLunPingLunData(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<PingLunPingLunBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getPingLunPingLun(userId,sessionId,map)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }
    public void getPingLunYingPianData(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<PingLunPingLunBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getPingLunYingPian(userId,sessionId,map)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }
}
