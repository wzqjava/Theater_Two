package com.bw.movie.server;

import io.reactivex.disposables.Disposable;

/**
 * date:2019/1/2
 * author:赵豪轩(xuan)
 * function:
 */
public class DefaultDisposable implements Disposable {
    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return true;
    }
}
