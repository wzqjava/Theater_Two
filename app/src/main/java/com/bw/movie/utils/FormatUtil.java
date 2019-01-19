package com.bw.movie.utils;

import java.text.SimpleDateFormat;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:格式转换工具类
 */
public class FormatUtil {

    static SimpleDateFormat sFormat = new SimpleDateFormat("MM-dd hh:mm");


    public static String showTime(long msecs){
        return sFormat.format(msecs);
    }
}
