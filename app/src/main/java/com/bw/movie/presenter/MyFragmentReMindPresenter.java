package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.bean.MyFragmentBean;
import com.bw.movie.bean.MyFragmentReMindRecyclerViewBean;
import com.bw.movie.model.DetailModel;
import com.bw.movie.model.MyFragmentReMindModel;
import com.bw.movie.view.MyFragmentReMindView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/15
 * author:赵豪轩(xuan)
 * function:
 */
public class MyFragmentReMindPresenter extends BaseMVPPresenter<MyFragmentReMindView> {

    MyFragmentReMindModel mMyFragmentReMindModel;

    public MyFragmentReMindPresenter() {
        mMyFragmentReMindModel = new MyFragmentReMindModel();
    }

    public void getReMindRecyclerViewData(String userId,String sessionId ,HashMap<String,String> map){
        mMyFragmentReMindModel.getReMindRecyclerViewData(userId,sessionId,map, new DisposableObserver<MyFragmentReMindRecyclerViewBean>() {
            @Override
            public void onNext(MyFragmentReMindRecyclerViewBean bean) {
                List<MyFragmentReMindRecyclerViewBean.ResultBean> result =
                        bean.getResult();

                if(result != null){
                    Log.e("zzz",bean.getMessage()+"            我的页面的通知栏");
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
