package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.CinemaCommendBean;
import com.bw.movie.bean.CinemaDetailCinemaBean;
import com.bw.movie.model.CinemaCommendModel;
import com.bw.movie.view.CinemaCommendView;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:影院评论的Presenter层
 */
public class CinemaCommendPresenter extends BaseMVPPresenter<CinemaCommendView> {

    private CinemaCommendModel mCinemaCommendModel;

    public CinemaCommendPresenter(){
        mCinemaCommendModel = new CinemaCommendModel();
    }
    private int mPage = 1;
    private final int CONTENT_COUNT = 5;

    /**
     * 刷新
     * @param headerParams
     * @param queryParams
     */
    public void refreshData(Map<String, String> headerParams,Map<String, String> queryParams) {
        getCinemaMessage(headerParams,queryParams, 1);
    }

    /**
     * 加载更多
     * @param headerParams
     * @param queryParams
     */
    public void loadData(Map<String, String> headerParams,Map<String, String> queryParams) {
        getCinemaMessage(headerParams,queryParams, mPage);
    }
    public void getCinemaMessage(Map<String, String> headerParams, Map<String, String> queryParams,final int page){
        //处理分页参数
        queryParams.put("page", String.valueOf(page));
        queryParams.put("count", String.valueOf(CONTENT_COUNT));

        if (true || mCinemaCommendModel.idDisposable()){
            mCinemaCommendModel.getCinemaCommmend(headerParams, queryParams, new DisposableObserver<CinemaCommendBean>() {
                @Override
                public void onNext(CinemaCommendBean cinemaCommendBean) {
                    mPage = page + 1;
                    if (view != null){
                        view.success(page == 1,cinemaCommendBean.getResult());
                    }
                }

                @Override
                public void onError(Throwable e) {
                    onComplete();
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
    public void onDestory() {
        view = null;
        mCinemaCommendModel.disposable();
    }
}
