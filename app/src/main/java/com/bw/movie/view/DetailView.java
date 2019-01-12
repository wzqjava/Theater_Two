package com.bw.movie.view;

import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;

import java.util.List;

/**
 * date:2019/1/8
 * author:赵豪轩(xuan)
 * function:
 */
public interface DetailView {

    void suucess(Detail_Detail_Bean.ResultBean resultBean);

    void error(String msg);

    void suucessPingLun(List<DetailPingLunBean.ResultBean> result);

    void errorPingLun(String msg);
}
