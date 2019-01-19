package com.bw.movie.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * date:2017/8/14
 * author:易宸锋(dell)
 * function:吐司的工具类,防止好久没有更新文字的问题
 */
public class ToastUtil {

    private static Toast toast;

    public static void showToast(Context context ,String msg){
        if (toast ==null){
            toast=Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
