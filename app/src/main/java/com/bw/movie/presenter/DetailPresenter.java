package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;
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
}
