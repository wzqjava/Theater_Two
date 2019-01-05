package com.bw.movie.presenter;

import com.bw.movie.bean.LoginBean;

/**
 * date:2019/1/4
 * author:刘振国(Liu)
 * function:
 */
public interface LoginInterface {
    void Success(LoginBean loginBean);
    void Failed();
}
