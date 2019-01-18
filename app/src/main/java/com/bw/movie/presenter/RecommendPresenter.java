package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.model.RecommendModel;
import com.bw.movie.view.RecommendView;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;
import retrofit2.http.PUT;

/**
 * date:2019/1/5
 * author:李壮(大壮)
 * function:---------------------推荐影院    P层--------------------
 */
public class RecommendPresenter extends BaseMVPPresenter<RecommendView> {
    private final RecommendModel mRecommendModel;

    public RecommendPresenter() {
        mRecommendModel = new RecommendModel();
    }

    public void refreshData(Map<String, String> headerParams,Map<String, String> queryParams) {
        getData(headerParams,queryParams, 1);
    }

    public void loadData(Map<String, String> headerParams,Map<String, String> queryParams) {
        getData(headerParams,queryParams, mPage);
    }

    private int mPage = 1;
    private final int CONTENT_COUNT = 10;

    public void getData(Map<String, String> headerParams, Map<String, String> queryParams, final int page) {
        //处理分页参数
        queryParams.put("page", String.valueOf(page));
        queryParams.put("count", String.valueOf(CONTENT_COUNT));

        if (true || mRecommendModel.isDisposable()) {
            mRecommendModel.getRecommend(headerParams, queryParams, new DisposableObserver<RecommendBean>() {
                @Override
                public void onNext(RecommendBean recommendBean) {
                    mPage = page + 1;
                    if (view!= null) {
                        view.success(page == 1,recommendBean.getResult());
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                  if (view != null){
                      view.onloadComplete();
                  }
                }
            });
        }
    }
}
