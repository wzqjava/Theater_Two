package com.bw.movie.view;

import com.bw.movie.bean.RecommendBean;

import java.util.List;

/**
 * date:2019/1/5
 * author:李壮(大壮)
 * function:------------推荐影院 V层---------------
 */
public interface RecommendView {

    void success(boolean isRefresh,List<RecommendBean.ResultBean> resultBeans);
    void error(String msg);

    /**
     * 停止刷新加载的动画
     */
    void onloadComplete();
}
