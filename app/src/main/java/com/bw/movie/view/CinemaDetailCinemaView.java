package com.bw.movie.view;

import com.bw.movie.bean.CinemaDetailCinemaBean;

import java.util.List;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:影院详情影院介绍
 */
public interface CinemaDetailCinemaView {

    void success(CinemaDetailCinemaBean.ResultBean resultBeans);
    void error();
}
