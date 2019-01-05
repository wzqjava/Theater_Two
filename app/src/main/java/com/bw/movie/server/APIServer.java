package com.bw.movie.server;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.net.Constom;

import java.util.HashMap;
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

    //注册的网络请求
    //http://172.17.8.100/movieApi/user/v1/registerUser
    @FormUrlEncoded
    @POST(Constom.REGISTER_URL)
    Observable<RegisterBean> getRegister(@FieldMap HashMap<String,String> map);
}
