package com.bw.movie.server;


import com.bw.movie.bean.LoginBean;
import com.bw.movie.net.Constom;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * date:2018/12/31
 * author:赵豪轩(xuan)
 * function:
 */
public interface APIServer {

    /**
     * 推荐影院接口
     */
    public final static String RECOMMEND = "movieApi/cinema/v1/findRecommendCinemas";
    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> Login(@FieldMap Map<String, String> map);

}
