package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.model.MyFragmentGuanZhuModel;
import com.bw.movie.view.MyFragmentGuanZhuView;

/**
 * date:2019/1/18
 * author:赵豪轩(xuan)
 * function:
 */
public class MyFragmentGuanZhuPresenter extends BaseMVPPresenter<MyFragmentGuanZhuView> {

    MyFragmentGuanZhuModel mMyFragmentGuanZhuModel;

    public MyFragmentGuanZhuPresenter() {
        mMyFragmentGuanZhuModel = new MyFragmentGuanZhuModel();
    }

}
