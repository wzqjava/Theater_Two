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

    void suucessQuXiao(String msg);

    void suucessGuanZhu(String msg);

    void errorQuXiao(String msg);

    void errorGuanZhu(String msg);

    void successdianzan(String message);

    void errordianzan(String s);

    void successpinglunpinglun(String message);

    void errorpinglunpinglun(String message);

    void successpinglunyingpian(String message);

    void errorpinglunyingpian(String message);
}
