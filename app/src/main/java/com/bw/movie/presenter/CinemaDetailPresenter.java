package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.CinemaDetailBean;
import com.bw.movie.bean.CinemaDetailIconBean;
import com.bw.movie.bean.CinemaDetailScheduleBean;
import com.bw.movie.model.CinemaDetailIconModel;
import com.bw.movie.model.CinemaDetailModel;
import com.bw.movie.model.CinemaDetailScheduleModel;
import com.bw.movie.view.CinemaDetailView;

import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/9
 * author:李壮(大壮)
 * function:影院详情Presenter层
 */
public class CinemaDetailPresenter extends BaseMVPPresenter<CinemaDetailView> {

    private final CinemaDetailModel mCinemaDetailModel;
    private final CinemaDetailIconModel mCinemaDetailIconModel;
    private final CinemaDetailScheduleModel mCinemaDetailScheduleModel;

    public CinemaDetailPresenter(){
        mCinemaDetailModel = new CinemaDetailModel();
        mCinemaDetailIconModel = new CinemaDetailIconModel();
        mCinemaDetailScheduleModel = new CinemaDetailScheduleModel();
    }

    /**
     * 加载该影院的信息
     * @param headerParams
     * @param queryParams
     */
    public void getData(Map<String, String> headerParams, Map<String, String> queryParams){
        if (mCinemaDetailModel.idDisposable()){
            mCinemaDetailModel.getCinemaDetail(headerParams, queryParams, new DisposableObserver<CinemaDetailBean>() {
                @Override
                public void onNext(CinemaDetailBean cinemaDetailBean) {
                    if (cinemaDetailBean != null){
                        view.success(cinemaDetailBean);
                    }
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

    /**
     * 加载影院影片信息
     * @param queryParams
     */
    public void getDetailIcon(Map<String,String> queryParams){
        if (mCinemaDetailIconModel.idDisposable()){
            mCinemaDetailIconModel.getDetailIcon(queryParams, new DisposableObserver<CinemaDetailIconBean>() {
                @Override
                public void onNext(CinemaDetailIconBean cinemaDetailIconBean) {
                    view.successIcon(cinemaDetailIconBean.getResult());
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

    /**
     * 加载当前影院上线影片的排期信息
     * @param queryParams
     */
    public void getDetailSchedule(Map<String,String> queryParams){
        mCinemaDetailScheduleModel.disposable();
            mCinemaDetailScheduleModel.getDetailSchedule(queryParams, new DisposableObserver<CinemaDetailScheduleBean>() {

                @Override
                public void onNext(CinemaDetailScheduleBean cinemaDetailScheduleBean) {
                    view.successSchedule(cinemaDetailScheduleBean.getResult());
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
