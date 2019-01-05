package com.bw.movie.presenter;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.model.ClassfyModel;
import com.bw.movie.view.ClassfyView;

import java.util.HashMap;
/**
 * date:2019/1/3
 * author:赵豪轩(xuan)
 * function:
 */
public class ClassfyPresenter extends BaseMVPPresenter<ClassfyView> {


    public ClassfyModel mClassfyModel;

    public ClassfyPresenter() {
        mClassfyModel = new ClassfyModel();
    }

    public void getClassfyData(HashMap<String,String> map){


    }

}
