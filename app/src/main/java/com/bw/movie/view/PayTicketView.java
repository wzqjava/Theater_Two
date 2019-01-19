package com.bw.movie.view;

import com.bw.movie.bean.QueryTicketBean;

import java.util.List;

/**
 * date:2019/1/18
 * author:李壮(大壮)
 * function:购票记录View层
 */
public interface PayTicketView {

    void success(boolean isRefresh,List<QueryTicketBean.ResultBean> resultBeans);
    void error(String msg);
    void onComplete();
}
