package com.study.shy.pipi.http;

import com.study.shy.pipi.bean.HotBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ServiceApi {
    /**获取当日精选视频**/
    /**http://baobab.kaiyanapp.com/api/v2/feed?date=1557018000000&num=1**/
    @GET("/api/v2/feed?date=1557018000000&num=1")
    Observable<HotBean> getDaily(@QueryMap Map<String,String> map);
}
