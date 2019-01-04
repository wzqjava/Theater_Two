package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * date:2018/12/27
 * author:赵豪轩(xuan)
 * function:
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(setView(), container, false);
        initView(view);
        initData(view);
        setListener(view);
        return view;
    }
    /**
     * 吐司的方法
     * @param ss
     */
    public void showToast(String ss){
        Toast.makeText(getActivity(), ss+"", Toast.LENGTH_SHORT).show();
    }
    /**
     * 设置布局
     * @return
     */
    protected abstract int setView();
    /**
     * 初始化View
     * @param view
        */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     * @param view
     */
    protected abstract void initData(View view);
    /**
     * 设置监听
     * @param view
     */
    protected abstract void setListener(View view);
    }
