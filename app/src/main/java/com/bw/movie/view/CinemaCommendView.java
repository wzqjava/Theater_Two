package com.bw.movie.view;

import com.bw.movie.bean.CinemaCommendBean;

import java.util.List;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:影院评论View层
 */
public interface CinemaCommendView {
    void success(boolean isRefresh,List<CinemaCommendBean.ResultBean> resultBeans);
    void error();
    void onloadComplete();
}
