package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.CinemaDetailBean;
import com.bw.movie.bean.SearchBean;
import com.bw.movie.model.SearchModel;
import com.bw.movie.view.SearchView;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/16
 * author:李壮(大壮)
 * function:搜索查询Presenter层
 */
public class SearchPresenter extends BaseMVPPresenter<SearchView> {

    private final SearchModel mSearchModel;

    public SearchPresenter(){
        mSearchModel = new SearchModel();

    }
    public void getSearch(Map<String, String> queryParams){
        if (mSearchModel.isDisposable()){
            mSearchModel.getRecommend(queryParams, new DisposableObserver<SearchBean>() {
                @Override
                public void onNext(SearchBean searchBean) {
                    if (searchBean != null){
                        view.success(searchBean.getResult());
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
}
