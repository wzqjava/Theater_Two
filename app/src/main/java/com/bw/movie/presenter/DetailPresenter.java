package com.bw.movie.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.bean.PingLunPingLunBean;
import com.bw.movie.bean.PingLunZanBean;
import com.bw.movie.bean.QuXiaoGuanZhuBean;
import com.bw.movie.model.DetailModel;
import com.bw.movie.view.DetailView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/8
 * author:赵豪轩(xuan)
 * function:
 */
public class DetailPresenter extends BaseMVPPresenter<DetailView> {

    public DetailModel mDetailModel;
    public DetailPresenter() {
        mDetailModel = new DetailModel();
    }
    public void getdetailData(String userId,String sessionId ,HashMap<String,String> map){
        mDetailModel.getData(userId,sessionId,map, new DisposableObserver<Detail_Detail_Bean>() {
            @Override
            public void onNext(Detail_Detail_Bean Bean) {
                Detail_Detail_Bean.ResultBean result = Bean.getResult();
                if(result != null){
                    Log.e("zzz",Bean.getMessage()+"            详情");
                    if (Bean.getStatus().equals("0000")){
                        view.suucess(result);
                    }else{
                        view.error(Bean.getMessage());
                    }
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
    public void getPingLunData(String userId,String sessionId ,HashMap<String,String> map){
        mDetailModel.getPingLunData(userId,sessionId,map, new DisposableObserver<DetailPingLunBean>() {
            @Override
            public void onNext(DetailPingLunBean  Bean) {
                List<DetailPingLunBean.ResultBean> result = Bean.getResult();
                if(result != null){
                    Log.e("zzz",Bean.getMessage()+"            评论");
                    if (Bean.getStatus().equals("0000")){
                        view.suucessPingLun(result);
                    }else{
                        view.errorPingLun(Bean.getMessage());
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                view.errorPingLun(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }
    public void getQuXiao(String userId,String sessionId ,HashMap<String,String> map){
        mDetailModel.getQuXiaoData(userId,sessionId,map, new DisposableObserver<QuXiaoGuanZhuBean>() {
            @Override
            public void onNext(QuXiaoGuanZhuBean  bean) {
                if(bean != null){
                    Log.e("zzz",bean.getMessage()+"            取消关注");
                    if (bean.getStatus().equals("0000")){
                        view.suucessQuXiao(bean.getMessage());
                    }else{
                        view.errorQuXiao(bean.getMessage());
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                view.errorQuXiao(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }
    public void getGuanZhu(String userId,String sessionId ,HashMap<String,String> map){
        mDetailModel.getGuanZhuData(userId,sessionId,map, new DisposableObserver<QuXiaoGuanZhuBean>() {
            @Override
            public void onNext(QuXiaoGuanZhuBean  bean) {
                if(bean != null){
                    Log.e("zzz",bean.getMessage()+"            关注");
                    if (bean.getStatus().equals("0000")){
                        view.suucessGuanZhu(bean.getMessage());
                    }else{
                        view.errorGuanZhu(bean.getMessage());
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                view.errorGuanZhu(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }

    public void getDianZan(String userId, String sessionId, HashMap<String,String> map) {
        mDetailModel.getDianZanData(userId,sessionId,map, new DisposableObserver<PingLunZanBean>() {
            @Override
            public void onNext(PingLunZanBean  bean) {
                if(bean != null){
                    if (bean.getMessage().equals("点赞成功")){
                        view.successdianzan(bean.getMessage());
                    }else{
                        view.errordianzan(bean.getMessage());
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                view.errordianzan(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }

    public void getPingLunPingLun(String userId, String sessionId, HashMap<String,String> map) {
        mDetailModel.getPingLunPingLunData(userId,sessionId,map, new DisposableObserver<PingLunPingLunBean>() {
            @Override
            public void onNext(PingLunPingLunBean bean) {
                if (bean != null){
                    if (bean.getStatus().equals("0000")){
                        view.successpinglunpinglun(bean.getMessage());
                    }else{
                        view.errorpinglunpinglun(bean.getMessage());
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
              //  view.errordianzan(e.toString());
                view.errorpinglunpinglun(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }
    public void getPingLunYingPian(String userId, String sessionId, HashMap<String,String> map) {
        mDetailModel.getPingLunYingPianData(userId,sessionId,map, new DisposableObserver<PingLunPingLunBean>() {
            @Override
            public void onNext(PingLunPingLunBean bean) {
                if (bean != null){
                    if (bean.getStatus().equals("0000")){
                        view.successpinglunyingpian(bean.getMessage());
                    }else{
                        view.errorpinglunyingpian(bean.getMessage());
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                //  view.errordianzan(e.toString());
                view.errorpinglunyingpian(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }

    public boolean isNull(String trim) {
        if (!TextUtils.isEmpty(trim)){
            return true;
        }else{
            return false;
        }
    }
}
