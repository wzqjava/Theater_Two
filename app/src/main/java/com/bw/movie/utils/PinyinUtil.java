package com.bw.movie.utils;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * date:2017/8/15
 * author:易宸锋(dell)
 * function:工具类的方法,一般我定义为静态方法,不要创建类对象一样可以调用方法,方便
 */
public class PinyinUtil {
    /**
     * 根据指定的汉字字符串, 返回其对应的拼音
     * @param string
     * @return
     */
    public static String getPinyin(String string){
        //创建对象,以便调用方法
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        //不要音标
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //设置转换出大写字母
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);

        //我把得到的字符串,改为了字符数组,pinyin4g只能一个字符一个字符去转换成拼音
        char[] chars = string.toCharArray();
        //创建了一个装字符的容器,StringBuffer
        StringBuffer sb = new StringBuffer();

        for(int x=0; x<chars.length; x++){
            char c = chars[x];

            //如果是空格 ,跳过当前循环
            if(Character.isWhitespace(c)){
                continue;
            }
            //是不是汉字,如果不是汉字,直接拼写
            if (c >-128  && c<127){
                sb.append(c);
            }//是汉字,那么我们就获取请拼音
            else {
                try {
                    //获取某个字符对应的拼音,可以获取到多音字  ,  单->shan ,dan  ,朴->  pu  ,piao
                    String s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
                    sb.append(s);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
