package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.model.MoviewFragmentModel;
import com.bw.movie.view.MovieFregmentView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/5
 * author:赵豪轩(xuan)
 * function:
 */
public class MoviewFragmentPresenter extends BaseMVPPresenter<MovieFregmentView> {

    public MoviewFragmentModel mMoviewFragmentModel;

    public MoviewFragmentPresenter() {
        mMoviewFragmentModel = new MoviewFragmentModel();
    }
    public void moviewFragmentRemen(String userId,String sessionId,HashMap<String,String> map){

        mMoviewFragmentModel.getRemenData(userId,sessionId,map, new DisposableObserver<MovieFragmentBean>() {
            @Override
            public void onNext(MovieFragmentBean movieFragmentBean) {
                if (movieFragmentBean.isSuccess()){
                    List<MovieFragmentBean.ResultBean> result =
                            movieFragmentBean.getResult();
                    view.successsReMen(result);
                }else{
                    view.errorReMen(movieFragmentBean.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.errorReMen(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void moviewFragmentZhengZai(String userId, String sessionId, HashMap<String,String> map) {
        mMoviewFragmentModel.getZhengZaiData(userId,sessionId,map, new DisposableObserver<MovieFragmentBean>() {
            @Override
            public void onNext(MovieFragmentBean movieFragmentBean) {
                if (movieFragmentBean.isSuccess()){
                    List<MovieFragmentBean.ResultBean> result =
                            movieFragmentBean.getResult();
                    view.successsZhengZai(result);
                }else{
                    view.errorZhengZai(movieFragmentBean.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.errorZhengZai(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });

    }
    public void moviewFragmentJiJiang(String userId, String sessionId, HashMap<String,String> map) {
        mMoviewFragmentModel.getJiJiangData(userId,sessionId,map, new DisposableObserver<MovieFragmentBean>() {
            @Override
            public void onNext(MovieFragmentBean movieFragmentBean) {
                if (movieFragmentBean.isSuccess()){
                    List<MovieFragmentBean.ResultBean> result =
                            movieFragmentBean.getResult();
                    view.successsJiJiang(result);
                }else{
                    view.errorJiJiang(movieFragmentBean.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.errorJiJiang(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
