package com.bw.movie.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.bean.PlayDetailPaiQiRecyclerViewBean;
import com.bw.movie.model.PlayDetailModel;
import com.bw.movie.view.PlayDetailView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/11
 * author:赵豪轩(xuan)
 * function:
 */
public class PlayDetailPresenter extends BaseMVPPresenter<PlayDetailView> {
    public PlayDetailModel mPlayDetailModel;

    public PlayDetailPresenter() {
        mPlayDetailModel = new PlayDetailModel();
    }

    public boolean isNull(String YingYuanId,String YingPianId) {
        if (TextUtils.isEmpty(YingPianId) || TextUtils.isEmpty(YingYuanId)){
            return true;
        }
        return false;
    }

    public void getPaiQiData(HashMap<String, String> map) {
        mPlayDetailModel.getPaiQiData(map, new DisposableObserver<PlayDetailPaiQiRecyclerViewBean>() {
            @Override
            public void onNext(PlayDetailPaiQiRecyclerViewBean bean) {

                List<PlayDetailPaiQiRecyclerViewBean.ResultBean> result = bean.getResult();
                if(result != null){
                    Log.e("zzz",bean.getMessage()+"            排期列表");
                    if (bean.getStatus().equals("0000")){
                        view.success(result);
                    }else{
                        view.error(bean.getMessage());
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
}
