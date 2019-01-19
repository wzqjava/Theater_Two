package com.bw.movie.view;

import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendBean;

import java.util.List;

/**
 * date:2019/1/5
 * author:李壮(大壮)
 * function:------------附近影院 V层---------------
 */
public interface NearbyView {

    void success(boolean isRefresh, List<NearbyBean.ResultBean> resultBeans);
    void error(String msg);
    void onloadComplete();
}
