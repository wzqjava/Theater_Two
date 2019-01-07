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
                    view.error(movieFragmentBean.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.error(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }


}
