package com.bw.movie.base;

/**
 * date:2019/1/2
 * author:赵豪轩(xuan)
 * function:
 */
public class BaseMVPPresenter<V> {
    V view;

    public void detach() {
        view = null;
    }

    public void attch(V v) {
        this.view = v;
    }
}
