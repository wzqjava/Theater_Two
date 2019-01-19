package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.QueryTicketBean;
import com.bw.movie.model.PayTicketModel;
import com.bw.movie.view.PayTicketView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.ext.beans.HashAdapter;
import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/18
 * author:李壮(大壮)
 * function:购票记录的Presenter层
 */
public class PayTicketPresenter extends BaseMVPPresenter<PayTicketView> {

    private final PayTicketModel mPayTicketModel;

    private int mPage = 1;
    private final int CONTENT_COUNT = 10;

    public PayTicketPresenter(){
        mPayTicketModel = new PayTicketModel();
    }

    //刷新
    public void refreshData(Map<String, String> headerParams,Map<String, String> queryParams) {
        getQueryTicket(headerParams, queryParams,1);
    }

    //加载
    public void loadData(Map<String, String> headerParams,Map<String, String> queryParams) {
        getQueryTicket(headerParams, queryParams,mPage);
    }

    //查询数据
    public void getQueryTicket(final Map<String, String> headerParams,
                               final Map<String,String> queryParams , final int page) {
        queryParams.put("page", String.valueOf(page));
        queryParams.put("count", CONTENT_COUNT+"");
        if (mPayTicketModel.isDisposable()){
            mPayTicketModel.getQueryTicket(headerParams, queryParams, new DisposableObserver<QueryTicketBean>() {
                @Override
                public void onNext(QueryTicketBean queryTicketBean) {
                    if (queryTicketBean.isSuccess()){
                        mPage = page+1;
                        List<QueryTicketBean.ResultBean> result = queryTicketBean.getResult();
                        if (view != null){
                            view.success(page == 1,result);
                        }
                    }else {
                        if (view != null){
                            view.error(queryTicketBean.getMessage());
                        }
                    }

                }

                @Override
                public void onError(Throwable e) {
                    if (view != null){
                        view.error(String.valueOf(e));
                    }

                    onComplete();
                }

                @Override
                public void onComplete() {
                    if (view != null){
                        view.onComplete();
                    }

                }
            });
        }
    }

}
