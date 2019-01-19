package com.bw.movie.view;

import com.bw.movie.bean.UserIDChaXunBean;

/**
 * date:2019/1/18
 * author:赵豪轩(xuan)
 * function:
 */
public interface PersonalDetailsView {
    void ChaXunSuccess(UserIDChaXunBean.ResultBean result);

    void ChaXunError(String message);

    void ShangChuanHeadPicSuccess(String message);

    void ShangChuanHeadPicError(String message);
}
