package com.bw.movie.server;

import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.net.Constom;
import com.greendao.gen.UserBeanDao;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("movieApi/user/v1/login")
    Observable<LoginBean> Login(@FieldMap Map<String, String> map);

    //注册的网络请求
    //http://172.17.8.100/movieApi/user/v1/registerUser
    @FormUrlEncoded
    @POST(Constom.REGISTER_URL)
    Observable<RegisterBean> getRegister(@FieldMap HashMap<String,String> map);

    //影片的热门电影
    @GET(Constom.MOVIEFRAGMENT_REMEN)
    Observable<MovieFragmentBean> getMovieFragment(@Header("userId") String userId,@Header("sessionId") String sessionId, @QueryMap HashMap<String, String> map);
}
