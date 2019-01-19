package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.CinemaDetailCinemaBean;
import com.bw.movie.bean.MovieTicketBean;
import com.bw.movie.model.TicketModel;
import com.bw.movie.view.TicketView;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:下单页面Preseter层
 */
public class TicketPresenter extends BaseMVPPresenter<TicketView> {

    private final TicketModel mTicketModel;

    public TicketPresenter(){
        mTicketModel = new TicketModel();
    }

    public void getMovieTicket(Map<String, String> headerParams, Map<String, String> queryParams){
        if (mTicketModel.idDisposable()){
            mTicketModel.getMovieTicket(headerParams, queryParams, new DisposableObserver<MovieTicketBean>() {

                @Override
                public void onNext(MovieTicketBean movieTicketBean) {
                    view.success(movieTicketBean);
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
