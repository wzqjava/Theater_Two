package com.bw.movie.view;

import com.bw.movie.bean.MovieTicketBean;

import java.util.List;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:选座页面View层
 */
public interface TicketView {
    void success(MovieTicketBean movieTicketBeans);
    void error();
}
