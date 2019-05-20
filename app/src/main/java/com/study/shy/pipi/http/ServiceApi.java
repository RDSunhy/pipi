package com.study.shy.pipi.http;

import com.study.shy.pipi.bean.CategoryBean;
import com.study.shy.pipi.bean.CategoryContext;
import com.study.shy.pipi.bean.CategoryInfo;
import com.study.shy.pipi.bean.HotBean;
import com.study.shy.pipi.bean.SaveResult;

import java.util.List;
import java.util.Map;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ServiceApi {

    /**环信注册**/
    /**http://a1.easemob.com/1102190517019891/pipi/users**/
    @POST("/1102190517019891/pipi/users")
    Observable<Response> register(@QueryMap Map<String,String> map);
    /**存储 查询 两个接口**/
    /**存储**/
    @GET("/ucache/put")
    Observable<SaveResult> saveData(@QueryMap Map<String,String> map);
    /**查询**/
    @GET("/ucache/get")
    Observable<SaveResult> queryData(@QueryMap Map<String,String> map);
    /********************视频接口******************/
    /**获取当日精选视频**/
    /**http://baobab.kaiyanapp.com/api/v2/feed?date=1557018000000&num=1**/
    @GET("/api/v2/feed")
    Observable<HotBean> getDaily(@QueryMap Map<String,String> map);
    /**获取分类**/
    /**http://baobab.kaiyanapp.com/api/v4/categories/all**/
    @GET("/api/v4/categories/all")
    Observable<CategoryBean> getCategories();
    /**分类详情**/
    /**http://baobab.kaiyanapp.com/api/v4/categories/**/
    @GET("/api/v4/categories/detail/tab")
    Observable<CategoryInfo> getCategoryInfo(@QueryMap Map<String,String> map);
    /**分类视频**/
    /**http://baobab.kaiyanapp.com/api/v1/tag/videos?id=16**/
    /**start=20&num=10**/
    @GET("/api/v1/tag/videos")
    Observable<CategoryContext> getCategoryContext(@QueryMap Map<String,String> map);
}
