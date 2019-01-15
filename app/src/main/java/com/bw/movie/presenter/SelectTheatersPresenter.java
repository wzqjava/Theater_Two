package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.SelectThrastersRecyclerViewBean;
import com.bw.movie.model.SelectTheatersModel;
import com.bw.movie.view.SelectTheatersView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/10
 * author:赵豪轩(xuan)
 * function:
 */
public class SelectTheatersPresenter extends BaseMVPPresenter<SelectTheatersView> {

    SelectTheatersModel mSelectTheatersModel;

    public SelectTheatersPresenter() {
        mSelectTheatersModel = new SelectTheatersModel();
    }
    public void getSelectTheatersData(HashMap<String,String> map){
        mSelectTheatersModel.getSelectTheatersData(map, new DisposableObserver<SelectThrastersRecyclerViewBean>() {
            @Override
            public void onNext(SelectThrastersRecyclerViewBean  Bean) {
                List<SelectThrastersRecyclerViewBean.ResultBean> result = Bean.getResult();

                if(result != null){
                   // Log.e("zzz",Bean.getMessage()+"            根据电影id查询影院");
                    if (Bean.getStatus().equals("0000")){
                        view.success(result);
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
}
