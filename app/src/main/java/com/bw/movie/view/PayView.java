package com.bw.movie.view;

import com.bw.movie.bean.PayResponseBean;

/**
 * date:2019/1/14
 * author:李壮(大壮)
 * function:支付View层
 */
public interface PayView {
    void success(PayResponseBean payResponseBean);
    void error(String msg);
}
