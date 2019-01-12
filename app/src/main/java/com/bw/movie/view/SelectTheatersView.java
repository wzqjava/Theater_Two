package com.bw.movie.view;

import com.bw.movie.bean.SelectThrastersRecyclerViewBean;

import java.util.List;

/**
 * date:2019/1/10
 * author:赵豪轩(xuan)
 * function:
 */
public interface SelectTheatersView {
    void success(List<SelectThrastersRecyclerViewBean.ResultBean> result);
    void error(String msg);
}
