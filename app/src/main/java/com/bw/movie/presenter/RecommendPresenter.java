package com.bw.movie.presenter;

import com.bw.movie.bean.RecommendBean;
import com.bw.movie.model.RecommendModel;
import com.bw.movie.view.RecommendView;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/5
 * author:李壮(大壮)
 * function:---------------------附近影院    P层--------------------
 */
public class RecommendPresenter {
    private RecommendView mRecommendView;
    private final RecommendModel mRecommendModel;

    public RecommendPresenter(){
        mRecommendModel = new RecommendModel();
    }

    public void getData(Map<String, String > map){
        if (mRecommendModel.isDisposable()){
          mRecommendModel.getRecommend(map, new DisposableObserver<RecommendBean>() {
              @Override
              public void onNext(RecommendBean recommendBean) {
                  if (mRecommendView != null){
                      mRecommendView.success(recommendBean.getResult().getFollowCinemas());
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

    public void onCreate(RecommendView view){
        mRecommendView = view;
    }
    public void onDestory(){
        mRecommendView = null;
        mRecommendModel.disposable();
    }
}
