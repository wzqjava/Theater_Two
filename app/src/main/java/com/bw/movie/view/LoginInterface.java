package com.bw.movie.view;

import com.bw.movie.bean.LoginBean;

/**
 * date:2019/1/4
 * author:赵豪轩
 * function:
 */
public interface LoginInterface {
    void Success(LoginBean loginBean);
    void Failed();
}
