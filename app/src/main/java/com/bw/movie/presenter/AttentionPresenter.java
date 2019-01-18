package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.MovieTicketBean;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.model.AttentionModel;
import com.bw.movie.model.BaseResponse;
import com.bw.movie.model.UnAttentionModel;
import com.bw.movie.view.AttentionView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/17
 * author:李壮(大壮)
 * function:关注与取消关注的Presenter层
 */
public class AttentionPresenter extends BaseMVPPresenter<AttentionView> {

    private final AttentionModel mAttentionModel;
    private final UnAttentionModel mUnAttentionModel;

    public AttentionPresenter(){
        mAttentionModel = new AttentionModel();
        mUnAttentionModel = new UnAttentionModel();
    }
    public void getAttention(Map<String,String> headerParams, int position, boolean isFollowCinema, String cinemaId) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("cinemaId",cinemaId);
        if (isFollowCinema){
            //取消关注
            onUnAttention(headerParams,position,queryParams);
        }else {
            //关注
            onAttention(headerParams,position,queryParams);
        }


    }
    /**
     * 关注
     * @param headerParams
     * @param position
     * @param queryParams
     */
    private void onAttention(Map<String,String> headerParams, final int position, HashMap<String,String> queryParams) {
        if (mAttentionModel.idDisposable()){
            mAttentionModel.getAttention(headerParams, queryParams, new DisposableObserver<BaseResponse>() {

                @Override
                public void onNext(BaseResponse baseResponse) {
                    if ( view != null){
                        if (baseResponse.isSuccess()){
                            view.successGZ(position);
                        }else {
                            view.reeorGZ(baseResponse.getMessage());
                        }
                    }


                }

                @Override
                public void onError(Throwable e) {
                    if (view != null){
                        view.reeorGZ("关注失败");
                    }

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    /**
     * 取消关注
     * @param headerParams
     * @param position
     * @param queryParams
     */
    private void onUnAttention(Map<String, String> headerParams, final int position, HashMap<String, String> queryParams) {
        if (mUnAttentionModel.idDisposable()){
            mUnAttentionModel.getUnAttention(headerParams, queryParams, new DisposableObserver<BaseResponse>() {

                @Override
                public void onNext(BaseResponse baseResponse) {
                    if ( view != null){
                        if (baseResponse.isSuccess()){
                            view.successGZ(position);
                        }else {
                            view.reeorGZ(baseResponse.getMessage());
                        }
                    }


                }

                @Override
                public void onError(Throwable e) {
                    if (view != null){
                        view.reeorGZ("取消关注失败");
                    }

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }


}
