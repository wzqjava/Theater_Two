package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.bean.QuXiaoGuanZhuBean;
import com.bw.movie.model.ClassfyModel;
import com.bw.movie.view.ClassfyView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/3
 * author:赵豪轩(xuan)
 * function:
 */
public class ClassfyPresenter extends BaseMVPPresenter<ClassfyView> {


    public ClassfyModel mClassfyModel;

    public ClassfyPresenter() {
        mClassfyModel = new ClassfyModel();
    }

    public void classFyRemen(String userId,String sessionId,HashMap<String,String> map){

        mClassfyModel.getRemenData(userId,sessionId,map, new DisposableObserver<MovieFragmentBean>() {
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
    public void classFyZhengZai(String userId, String sessionId, HashMap<String,String> map) {
        mClassfyModel.getZhengZaiData(userId,sessionId,map, new DisposableObserver<MovieFragmentBean>() {
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
    public void classFyJiJiang(String userId, String sessionId, HashMap<String,String> map) {
        mClassfyModel.getJiJiangData(userId,sessionId,map, new DisposableObserver<MovieFragmentBean>() {
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

    public void quXiao(String userId, String sessionId, HashMap<String,String> map) {
        mClassfyModel.getQuXiao(userId,sessionId,map, new DisposableObserver<QuXiaoGuanZhuBean>() {
            @Override
            public void onNext(QuXiaoGuanZhuBean bean) {

                if (bean != null){
                    if (bean.getStatus().equals("0000")){
                        view.successquxiao(bean.getMessage());
                    }else{
                        view.errorquxiao(bean.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                view.errorquxiao(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void GuanZhu(String userId, String sessionId, HashMap<String,String> map) {
        mClassfyModel.getGuanZhu(userId,sessionId,map, new DisposableObserver<QuXiaoGuanZhuBean>() {
            @Override
            public void onNext(QuXiaoGuanZhuBean bean) {

                if (bean != null){
                    if (bean.getStatus().equals("0000")){
                        view.successguanzhu(bean.getMessage());
                    }else{
                        view.errorguanzhu(bean.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                view.errorguanzhu(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
