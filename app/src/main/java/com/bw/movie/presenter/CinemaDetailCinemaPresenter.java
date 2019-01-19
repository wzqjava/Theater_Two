package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.CinemaDetailCinemaBean;
import com.bw.movie.model.CinemaDetailCinemaModel;
import com.bw.movie.view.CinemaDetailCinemaView;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:影院详情介绍影院
 */
public class CinemaDetailCinemaPresenter extends BaseMVPPresenter<CinemaDetailCinemaView> {

    private final CinemaDetailCinemaModel mCinemaDetailCinemaModel;

    public CinemaDetailCinemaPresenter(){
        mCinemaDetailCinemaModel = new CinemaDetailCinemaModel();
    }

    public void getCinemaMessage(Map<String, String> headerParams, Map<String, String> queryParams){
        if (mCinemaDetailCinemaModel.idDisposable()){
            mCinemaDetailCinemaModel.getCinemaMessage(headerParams, queryParams, new DisposableObserver<CinemaDetailCinemaBean>() {
                @Override
                public void onNext(CinemaDetailCinemaBean cinemaDetailCinemaBean) {
                    view.success(cinemaDetailCinemaBean.getResult());
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
}
