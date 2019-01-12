package com.bw.movie;

import com.bw.movie.bean.RecommendBean;
import com.bw.movie.server.APIServer;
import com.bw.movie.utils.RetrofitUtils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void recommendTest() throws InterruptedException {
        final CountDownLatch cdl = new CountDownLatch(1);
        Map<String, String> headerParams = new HashMap<>();
        Map<String, String> queryParams = new HashMap<>();
        headerParams.put("userId", "3895");
        headerParams.put("sessionId", "15468313763163895");

        queryParams.put("longitude", "");
        queryParams.put("latitude", "");
        queryParams.put("page", "1");
        queryParams.put("count", "10");

        RetrofitUtils
                .getInstance()
                .getService(APIServer.class)
                .getRecommend(headerParams, queryParams)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<RecommendBean>() {
                    @Override
                    public void onNext(RecommendBean recommendBean) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        cdl.countDown();
                    }

                    @Override
                    public void onComplete() {
                        cdl.countDown();
                    }
                });
        cdl.await();
    }
}