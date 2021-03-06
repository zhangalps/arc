package com.arc.news.utils.retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * package : com.arc.news.utils.retrofit
 * anthor : 张贺岗
 * Date : 2019/2/14
 * Use : <类的用途>
 */
public class RetrofitUtils {
    private static final int DEFAULT_TIME = 10;
    private RetrofitUtils() {
    }

    private static class Getter{
        public static final RetrofitUtils retrofit = new RetrofitUtils();
    }

    public static RetrofitUtils getInstance(){
        return Getter.retrofit;
    }

    /**
     * 获取retrofit
     * @param baseUrl
     * @return
     */
    public Retrofit getRetrofitForLog(String baseUrl){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new HttpHeaderInterCeptor())
                .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    static class HttpHeaderInterCeptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request);
        }
    }


}
