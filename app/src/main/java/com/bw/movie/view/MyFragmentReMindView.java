package com.bw.movie.view;

import com.bw.movie.bean.MyFragmentReMindRecyclerViewBean;

import java.util.List;

/**
 * date:2019/1/15
 * author:赵豪轩(xuan)
 * function:
 */
public interface MyFragmentReMindView {

    void success(List<MyFragmentReMindRecyclerViewBean.ResultBean> result);
    void error(String msg);
}
