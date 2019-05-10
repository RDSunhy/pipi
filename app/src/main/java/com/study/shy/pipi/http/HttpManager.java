package com.study.shy.pipi.http;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.study.shy.pipi.bean.CategoryBean;
import com.study.shy.pipi.bean.CategoryContext;
import com.study.shy.pipi.bean.CategoryInfo;
import com.study.shy.pipi.bean.HotBean;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class HttpManager {

    private static HttpManager mInstance;
    public static HttpManager getInstance() {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    mInstance = new HttpManager();

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
            .baseUrl("http://baobab.kaiyanapp.com")//基地址
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    ServiceApi serviceApi = retrofit.create(ServiceApi.class);

    public Observable<HotBean> getDaily(Map<String,String> map){
        return serviceApi.getDaily(map);
    }

    public Observable<CategoryBean> getCategories(){
        return serviceApi.getCategories();
    }

    public Observable<CategoryInfo> getCategoryInfo(Map<String,String> map){
        return serviceApi.getCategoryInfo(map);
    }

    public Observable<CategoryContext> getCategoryContext(Map<String,String> map){
        return serviceApi.getCategoryContext(map);
    }
}
