package com.bw.movie.net;

/**
 * date:2018/12/21
 * author:赵豪轩(xuan)
 * function:
 */
public class Constom {

    private final static  boolean flag=true;
    /**
     * 通过flag返回内网/外网测试环境
     */
    public final static String GETNETWORK=netWrok();

    public static String netWrok(){
        if(flag){
            //内网环境
            return  "http://172.17.8.100";
        }else{
            //外网环境
            return  "http://mobile.bwstudent.com";
        }
    }

    //注册的接口
    public final static String REGISTER_URL="movieApi/user/v1/registerUser";
    //登录的接口
    public final static String LOHGIN_URL="movieApi/user/v1/login";
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
    public final static String SELECTTHRATERS_RECYCLERVIEW ="movieApi/movie/v1/findCinemasListByMovieId";

    //根据影片和影院的id查询排期列表
    public final static String PAIQI_RECYCLERVIEW = "movieApi/movie/v1/findMovieScheduleList";
    //取消关注
    public final static String QUXIAOGUANZHU ="movieApi/movie/v1/verify/cancelFollowMovie";
    //关注
    public final static String GUANZHU = "movieApi/movie/v1/verify/followMovie";
    //我的页面的通知
    public final static String REMINDRECYCLERVIEW = "movieApi/tool/v1/verify/findAllSysMsgList";

    //推荐影院
    public final static String RECOMMEND_CINEMA = "movieApi/cinema/v1/findRecommendCinemas";
    //附近影院
    public final static String NEARBY_CINEMA = "movieApi/cinema/v1/findNearbyCinemas";
    //影院详情的影片海报
    public final static String CINEMA_POSTER ="movieApi/movie/v1/findMovieListByCinemaId";
    //影院影片排期
    public final static String CINEMA_SCHEDULE = "movieApi/movie/v1/findMovieScheduleList";
    //影院详情
    public final static String CINEMA_DETAIL ="movieApi/cinema/v1/findCinemaInfo";
    //影院详情介绍弹框
    public final static String CINEMA_DETAIL_DIALOG ="movieApi/cinema/v1/findCinemaInfo";
    //影院评论
    public final static String CINEMA_COMMENT ="movieApi/cinema/v1/findAllCinemaComment";
    //电影购票下单
    public final static String MOVIE_BUY_TICKET ="movieApi/movie/v1/verify/buyMovieTicket";
    //电影购票下单支付
    public final static String MOVIE_BUY_TICKET_PAY ="movieApi/movie/v1/verify/pay";
    //搜索查询
    public final static String SERARCH_URL ="movieApi/cinema/v1/findAllCinemas";
    //关注影院
    public final static String ATTENTION_URL ="movieApi/cinema/v1/verify/followCinema";
    //取消关注影院
    public final static String UNATTENTION_URL ="movieApi/cinema/v1/verify/cancelFollowCinema";
    //zan
    public final static String DETAIL_PINGLUN_ZAN ="movieApi/movie/v1/verify/movieCommentGreat";
    //评论评论
    public final static String DETAIL_PINGLUN_PINGLUN ="movieApi/movie/v1/verify/commentReply";
    //评论影片
    public final static String DETAIL_PINGLUN_YINGPIAN ="movieApi/movie/v1/verify/movieComment";

    //查询购票记录
    public final static String QUERY_TICKET="movieApi/user/v1/verify/findUserBuyTicketRecordList";

    //查询用户信息
    public final static String USERID_USERBEAN ="movieApi/user/v1/verify/getUserInfoByUserId";
    //上传头像
    public final static String SHANGCHUANTOUXAING ="movieApi/user/v1/verify/uploadHeadPic";
}
