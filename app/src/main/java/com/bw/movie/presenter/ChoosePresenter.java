package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.model.ChooseModel;
import com.bw.movie.view.ChooseView;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:选座页面Preseter层
 */
public class ChoosePresenter extends BaseMVPPresenter<ChooseView> {

    private final ChooseModel mChooseModel;

    public ChoosePresenter(){
        mChooseModel = new ChooseModel();
    }
}
