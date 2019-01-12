package com.bw.movie.server;


import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.bean.PlayDetailPaiQiRecyclerViewBean;
import com.bw.movie.bean.QuXiaoGuanZhuBean;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SelectThrastersRecyclerViewBean;
import com.bw.movie.net.Constom;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * date:2018/12/31
 * author:赵豪轩(xuan)
 * function:
 */
public interface APIServer {
    /**
     * 登录
     *
     * @param map
     * @return
     */
    @GET("movieApi/user/v1/findRecommendCinemas")
    Observable<RecommendBean> Recommend(@QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("movieApi/user/v1/login")
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



}