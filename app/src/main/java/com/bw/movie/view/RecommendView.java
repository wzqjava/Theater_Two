package com.bw.movie.view;

import com.bw.movie.bean.RecommendBean;

import java.util.List;

/**
 * date:2019/1/5
 * author:李壮(大壮)
 * function:------------附近影院 V层---------------
 */
public interface RecommendView {

    void success(List<RecommendBean.ResultBean.FollowCinemasBean> followCinemasBeans);
    void error(String msg);
}
