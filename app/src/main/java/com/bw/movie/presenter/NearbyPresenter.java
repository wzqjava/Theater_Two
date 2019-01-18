package com.bw.movie.presenter;

import android.view.View;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.model.NearbyModel;
import com.bw.movie.model.RecommendModel;
import com.bw.movie.view.NearbyView;
import com.bw.movie.view.RecommendView;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/5
 * author:李壮(大壮)
 * function:---------------------附近影院    P层--------------------
 */
public class NearbyPresenter extends BaseMVPPresenter<NearbyView> {
    private final NearbyModel mNearbyModel;

    public NearbyPresenter() {
        mNearbyModel = new NearbyModel();
    }

    public void refreshData(Map<String, String> headerParams,Map<String, String> queryParams) {
        getData(headerParams,queryParams, 1);
    }

    public void loadData(Map<String, String> headerParams,Map<String, String> queryParams) {
        getData(headerParams,queryParams, mPage);
    }

    private int mPage = 1;
    private final int CONTENT_COUNT = 10;

    private void getData(Map<String, String> headerParams, Map<String, String> queryParams, final int page) {
        //处理分页参数
        queryParams.put("page", String.valueOf(page));
        queryParams.put("count", String.valueOf(CONTENT_COUNT));

        if (true || mNearbyModel.isDisposable()) {
            mNearbyModel.getNearby(headerParams, queryParams, new DisposableObserver<NearbyBean>() {
                @Override
                public void onNext(NearbyBean nearbyBean) {
                    mPage = page + 1;
                    if (view != null) {
                        view.success(page == 1, nearbyBean.getResult());
                    }
                }

                @Override
                public void onError(Throwable e) {
                    //
                    onComplete();
                }

                @Override
                public void onComplete() {
                    if(view != null) {
                        view.onloadComplete();
                    }
                }
            });
        }
    }
}
