package com.study.shy.pipi.http;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpHelper {

    private static HttpHelper mInstance;

    public static HttpHelper getInstance() {
        if (mInstance == null) {
            synchronized (HttpHelper.class) {
                if (mInstance == null) {
                    mInstance = new HttpHelper();
                }
            }
        }

        return mInstance;
    }


    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .removeHeader("User-Agent")//移除旧的
                            //WebSettings.getDefaultUserAgent(mContext) 是获取原来的User-Agent
                            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")
                            .build();
                    return chain.proceed(request);
                }
            })
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://a1.easemob.com/")//基地址
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    ServiceApi serviceApi = retrofit.create(ServiceApi.class);

    public ServiceApi getServiceApi(){
        return retrofit.create(ServiceApi.class);
    }
}
