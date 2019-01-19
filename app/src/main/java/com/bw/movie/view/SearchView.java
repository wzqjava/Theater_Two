package com.bw.movie.view;

import com.bw.movie.bean.SearchBean;

import java.util.List;

/**
 * date:2019/1/16
 * author:李壮(大壮)
 * function:搜索查询View层
 */
public interface SearchView {
    void success(List<SearchBean.ResultBean> resultBeans);
    void error();
}
