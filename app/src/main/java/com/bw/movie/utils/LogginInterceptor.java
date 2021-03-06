package com.bw.movie.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * date:2018/12/4
 * author:赵豪轩(xuan)
 * function:
 *     拦截器    公共参数
 */
public class LogginInterceptor implements Interceptor {
    Map<String,String> map;
  //  Map<String,String> map1;

    public LogginInterceptor(Map<String, String> map) {
        this.map = map;
    }

    private boolean l = false;
    @Override
    public Response intercept(Chain chain) throws IOException {
        map = null;
        //获取旧的request
        Request oldrequest = chain.request();
        String url = oldrequest.url().toString();
        //get请求
        if (oldrequest.method().equalsIgnoreCase("GET")){
            if (map != null && map.size() > 0){
                //StringBuilder stringBuilder = new StringBuilder(url);

                /*for (Map.Entry<String,String> entry: map.entrySet()){
                    stringBuilder.append("&"+entry.getKey()+"="+entry.getValue());
                }*/

                //url = stringBuilder.toString();

                /*if (!url.contains("?")){
                    url = url.replaceFirst("&","?");
                }*/
                /**
                 * map.put("ak","0110010010000");
                 *         map.put("Content-Type","application/x-www-form-urlencoded");
                 */
                Request.Builder builder = oldrequest.newBuilder();
                for (Map.Entry<String, String> entry: map.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
                Request request = builder.url(url).build();
                return chain.proceed(request);
            }
        }else{
            if (map != null && map.size() > 0){
                RequestBody requestBody = oldrequest.body();
                if (requestBody != null && requestBody instanceof FormBody){
                   FormBody formBody =  (FormBody)  requestBody;

                    FormBody.Builder builder = new FormBody.Builder();

                    HashMap<String, String> tempmap = new HashMap<>();

                    for (int i = 0 ; i < formBody.size() ; i ++){
                        builder.add(formBody.encodedName(i),formBody.encodedValue(i));
                        tempmap.put(formBody.encodedName(i),formBody.encodedValue(i));
                    }

                    for (Map.Entry<String,String> entry : map.entrySet()){
                        if (!tempmap.containsKey(entry.getKey())){
                            builder.add(entry.getKey(),entry.getValue());
                        }
                    }
                    /*List<String> strings = new ArrayList<>();
                    for (Map.Entry<String,String> entry : map1.entrySet()){
                       strings.add(entry.getValue());
                    }*/

                    FormBody oFormBody = builder.build();
                    Request.Builder nRequestBuilder = oldrequest.newBuilder();
                    for (Map.Entry<String, String> entry: map.entrySet()) {
                        nRequestBuilder.addHeader(entry.getKey(), entry.getValue());
                    }
                    Request request = nRequestBuilder.post(oFormBody).build();

                    return chain.proceed(request);
                    /**
                     * .addHeader("userId", strings.get(0))
                     *                             .addHeader("sessionId", strings.get(1))
                     */

                }
            }
        }

        return chain.proceed(oldrequest);
    }
}
