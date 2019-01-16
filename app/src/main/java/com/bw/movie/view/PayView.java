package com.bw.movie.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.bw.movie.bean.PayResponseBean;

/**
 * date:2019/1/14
 * author:李壮(大壮)
 * function:支付View层
 */
public interface PayView {
    void success(PayResponseBean payResponseBean);
    Activity getActivity();
    void error(String msg);
    Handler getHandler();
}
