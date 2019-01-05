package com.bw.movie.view;

import com.bw.movie.bean.RegisterBean;

/**
 * date:2019/1/4
 * author:赵豪轩(xuan)
 * function:
 */
public interface ClassfyView {
    //成功的方法
    void success(RegisterBean registerBean);
    //失败的方法
    void failure(String msg);
}
