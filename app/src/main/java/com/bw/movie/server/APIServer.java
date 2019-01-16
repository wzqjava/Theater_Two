package com.bw.movie.server;


import com.bw.movie.bean.CinemaCommendBean;
import com.bw.movie.bean.CinemaDetailBean;
import com.bw.movie.bean.CinemaDetailCinemaBean;
import com.bw.movie.bean.CinemaDetailIconBean;
import com.bw.movie.bean.CinemaDetailScheduleBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.bean.MyFragmentReMindRecyclerViewBean;
import com.bw.movie.bean.MovieTicketBean;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.PayResponseBean;
import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.bean.PlayDetailPaiQiRecyclerViewBean;
import com.bw.movie.bean.QuXiaoGuanZhuBean;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SelectThrastersRecyclerViewBean;
import com.bw.movie.net.Constom;

import java.util.HashMap;
import java.util.Map;

import freemarker.template.utility.Constants;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * date:2018/12/31
 * author:赵豪轩(xuan)
 * function:
 */
public interface APIServer {

    /**
     * 推荐影院
     * @param headerParams
     * @param queryParams
     * @return
     */
    //movieApi/cinema/v1/findRecommendCinemas
    @GET(Constom.RECOMMEND_CINEMA)
    //@Query   拼到url后面的  ?k=v&k=v
    //@Header 请求头参数: @Header("userId")String userId, @Header("sessionId")String sessionId
    Observable<RecommendBean> getRecommend(@HeaderMap Map<String, String> headerParams,
                                         @QueryMap Map<String, String> queryParams);
    //Observable<RecommendBean> Recommend(@QueryMap Map<String,String> map);


    /**
     * 附近影院
     * @param headerParams
     * @param queryParams
     * @return
     */
    @GET(Constom.NEARBY_CINEMA)
    //@Query   拼到url后面的  ?k=v&k=v
    //@Header 请求头参数: @Header("userId")String userId, @Header("sessionId")String sessionId
    Observable<NearbyBean> getNearby(@HeaderMap Map<String, String> headerParams,
                                             @QueryMap Map<String, String> queryParams);

    /**
     * 影院详情页面展示宣传海报
     * @param queryParams
     * @return
     */
    @GET(Constom.CINEMA_POSTER)
    Observable<CinemaDetailIconBean> getDetailIcon(@QueryMap Map<String,String> queryParams);

    /**
     * 影院相对应的影片的排期
     * @param queryParams
     * @return
     */
    @GET(Constom.CINEMA_SCHEDULE)
    Observable<CinemaDetailScheduleBean> getDetailSchedule(@QueryMap Map<String,String> queryParams);

    /**
     * 影院的详情信息
     * @param headerParams
     * @param queryParams
     * @return
     */
    @GET(Constom.CINEMA_DETAIL)
    Observable<CinemaDetailCinemaBean> getCinemaMessage(@HeaderMap Map<String, String> headerParams,
                                                        @QueryMap Map<String, String> queryParams);

    /**
     * 影院详情
     * @param headerParams
     * @param queryParams
     * @return
     */
    @GET(Constom.CINEMA_DETAIL_DIALOG)
    Observable<CinemaDetailBean> getCinemaDetail(@HeaderMap Map<String,String> headerParams,
                                                 @QueryMap Map<String,String> queryParams);

    /**
     * 影院评论
     * @param headerParams
     * @param queryParams
     * @return
     */
    @GET(Constom.CINEMA_COMMENT)
    Observable<CinemaCommendBean> getCinemaCommend(@HeaderMap Map<String,String> headerParams,
                                                   @QueryMap Map<String,String> queryParams);

    /**
     *购票下单
     * @param headerParams
     * @param queryParams
     * @return
     */
    @FormUrlEncoded
    @POST(Constom.MOVIE_BUY_TICKET)
    Observable<MovieTicketBean> getTicket(@HeaderMap Map<String,String> headerParams, @FieldMap Map<String,String> queryParams);

    /**
     *支付
     * @param headerParams
     * @param queryParams
     * @return
     */
    @FormUrlEncoded
    @POST(Constom.MOVIE_BUY_TICKET_PAY )
    Observable<PayResponseBean> getPay(@HeaderMap Map<String,String> headerParams, @FieldMap Map<String,String> queryParams);


    /**
     * 登录
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    //@Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    //phone=13793014727&pwd=eWLPHopE945d2ivttHaQTQ%3D%3D
    @POST(Constom.LOHGIN_URL)
    Observable<LoginBean> Login(@FieldMap Map<String, String> map);

    //注册的网络请求
    //http://172.17.8.100/movieApi/user/v1/registerUser
    @FormUrlEncoded
    @POST(Constom.REGISTER_URL)
    Observable<RegisterBean> getRegister(@FieldMap HashMap<String, String> map);


    //影片的热门电影
    @GET(Constom.MOVIEFRAGMENT_REMEN)
    Observable<MovieFragmentBean> getMovieFragmentReMen(@Header("userId") String userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> map);

    //影片的热门电影
    @GET(Constom.MOVIEFRAGMENT_ZHENGZAI)
    Observable<MovieFragmentBean> getMovieFragmentZhengZai(@Header("userId") String userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> map);

    //影片的热门电影
    @GET(Constom.MOVIEFRAGMENT_JIJIANG)
    Observable<MovieFragmentBean> getMovieFragmentJiJiang(@Header("userId") String userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> map);

    //影片的详情的查询
    @GET(Constom.DETAIL_DETAIL)
    Observable<Detail_Detail_Bean> getDetail_Detail(@Header("userId") String userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> map);

    //影片的评论的查询
    @GET(Constom.DETAIL_PINGLUN)
    Observable<DetailPingLunBean> getDetail_PingLun(@Header("userId") String userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> map);

    //根据影片查询影院
    @GET(Constom.SELECTTHRATERS_RECYCLERVIEW)
    Observable<SelectThrastersRecyclerViewBean> getSelectThrathers(@QueryMap HashMap<String, String> map);

    //根据影片id和影院ID去查询电影的排期列表
    @GET(Constom.PAIQI_RECYCLERVIEW)
    Observable<PlayDetailPaiQiRecyclerViewBean> getPaiQi(@QueryMap HashMap<String, String> map);

    //取消关注
    @GET(Constom.QUXIAOGUANZHU)
    Observable<QuXiaoGuanZhuBean> getQuXiao(@Header("userId") String userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> map);

    //关注
    @GET(Constom.GUANZHU)
    Observable<QuXiaoGuanZhuBean> getGuanZhu(@Header("userId") String userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> map);

    //我的页面的通知
    @GET(Constom.REMINDRECYCLERVIEW)
    Observable<MyFragmentReMindRecyclerViewBean> getReMindRecyclerView(@Header("userId") String userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String, String> map);


}