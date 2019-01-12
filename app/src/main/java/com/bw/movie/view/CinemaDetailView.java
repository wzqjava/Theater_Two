package com.bw.movie.view;

import com.bw.movie.bean.CinemaDetailBean;
import com.bw.movie.bean.CinemaDetailIconBean;
import com.bw.movie.bean.CinemaDetailScheduleBean;

import java.util.List;

/**
 * date:2019/1/9
 * author:李壮(大壮)
 * function:  影院详情View层
 */
public interface CinemaDetailView {
    void success(CinemaDetailBean cinemaDetailBeans);
    void successIcon(List<CinemaDetailIconBean.ResultBean> cinemaDetailIconBean);
    void successSchedule(List<CinemaDetailScheduleBean.ResultBean> cinemaDetailScheduleBeans);
    void error(String msg);
}
