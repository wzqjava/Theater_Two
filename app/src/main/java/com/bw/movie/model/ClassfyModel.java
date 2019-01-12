package com.bw.movie.model;

import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.bean.QuXiaoGuanZhuBean;
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
public class ClassfyModel {

    private Disposable mDisposable = new DefaultDisposable();

    public void getRemenData(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<MovieFragmentBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getMovieFragmentReMen(userId,sessionId,map)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }

    public void getZhengZaiData(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<MovieFragmentBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getMovieFragmentZhengZai(userId,sessionId,map)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }
    public void getJiJiangData(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<MovieFragmentBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getMovieFragmentJiJiang(userId,sessionId,map)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }
    public void getQuXiao(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<QuXiaoGuanZhuBean> observer){
        RetrofitUtils.getInstance()
                .getService(APIServer.class)
                .getQuXiao(userId,sessionId,map)
                //切换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
        mDisposable = observer;
    }
    public void getGuanZhu(String userId,String sessionId,HashMap<String,String> map, DisposableObserver<QuXiaoGuanZhuBean> observer){
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

}
