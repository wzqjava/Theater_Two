package com.bw.movie.bean;


import com.bw.movie.utils.PinyinUtil;

/**
 * date:2017/8/15
 * author:易宸锋(dell)
 * function:
 */

public class CityBean implements Comparable<CityBean>{
    private String name;
    private String pinyin;

    public CityBean(String name) {
        this.name = name;
        //使用工具类,根据汉字拿到拼音
        this.pinyin = PinyinUtil.getPinyin(name);
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    //主要为了排序,按照拼音排序
    @Override
    public int compareTo(CityBean cityBean) {
        return this.pinyin.compareTo(cityBean.pinyin);
    }
}
