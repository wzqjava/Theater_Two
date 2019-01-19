package com.bw.movie.view;

import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.bean.RegisterBean;

import java.util.List;

/**
 * date:2019/1/4
 * author:赵豪轩(xuan)
 * function:
 */
public interface ClassfyView {
    void successsReMen(List<MovieFragmentBean.ResultBean> result);

    void errorReMen(String msg);

    void successsZhengZai(List<MovieFragmentBean.ResultBean> result);

    void successsJiJiang(List<MovieFragmentBean.ResultBean> result);

    void errorZhengZai(String msg);

    void errorJiJiang(String msg);

    void successquxiao(String message);

    void errorquxiao(String message);

    void successguanzhu(String message);

    void errorguanzhu(String message);
}
