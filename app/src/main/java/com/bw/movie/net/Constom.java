package com.bw.movie.net;

/**
 * date:2018/12/21
 * author:赵豪轩(xuan)
 * function:
 */
public class Constom {
    //总接口
    public final static String BASE_URL = "http://172.17.8.100/";

    //public final static String DATA_URL="http://120.27.23.105/user/";
    //注册的接口
    public final static String REGISTER_URL="movieApi/user/v1/registerUser";
    //首页的热门电影
    public final static String MOVIEFRAGMENT_REMEN="movieApi/movie/v1/findHotMovieList";
    //首页的正在上映
    public final static String MOVIEFRAGMENT_ZHENGZAI="movieApi/movie/v1/findReleaseMovieList";
    //首页的即将上映
    public final static String MOVIEFRAGMENT_JIJIANG="movieApi/movie/v1/findComingSoonMovieList";
    //影片详情
    public final static String DETAIL_DETAIL="movieApi/movie/v1/findMoviesDetail";
    //影片详情的评论
    public final static String DETAIL_PINGLUN="movieApi/movie/v1/findAllMovieComment";
    //根据影片查询影院
    public final static String SELECTTHRATERS_RECYCLERVIEW = "movieApi/movie/v1/findCinemasListByMovieId";

    //根据影片和影院的id查询排期列表
    public final static String PAIQI_RECYCLERVIEW = "movieApi/movie/v1/findMovieScheduleList";
    //取消关注
    public final static String QUXIAOGUANZHU = "movieApi/movie/v1/verify/cancelFollowMovie";
    //关注
    public final static String GUANZHU = "movieApi/movie/v1/verify/followMovie";
}
