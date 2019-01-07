package com.bw.movie.view;

import com.bw.movie.bean.MovieFragmentBean;

import java.util.List;

/**
 * date:2019/1/5
 * author:赵豪轩(xuan)
 * function:
 */
public interface MovieFregmentView {

     void successsReMen(List<MovieFragmentBean.ResultBean> result);

     void error(String msg);
}
