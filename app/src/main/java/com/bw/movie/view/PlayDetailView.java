package com.bw.movie.view;

import com.bw.movie.bean.PlayDetailPaiQiRecyclerViewBean;

import java.util.List;

/**
 * date:2019/1/11
 * author:赵豪轩(xuan)
 * function:
 */
public interface PlayDetailView {
    void success(List<PlayDetailPaiQiRecyclerViewBean.ResultBean> result);
    void error(String msg);
}
