package com.study.shy.pipi.util;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.study.shy.pipi.bean.SaveResult;
import com.study.shy.pipi.http.HttpHelper;
import com.study.shy.pipi.http.HttpManager;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DBUtils {

    public final static String APPKEY = "2b1187972912c";
    public final static String TABLE = "PIPI_USER";

    public static void saveData(String k,String v){
        /**先进行编码**/
        k = new String(EncodeUtils.base64Encode(k));
        v = new String(EncodeUtils.base64Encode(GsonUtils.toJson(v)));

        Map<String,String> map = new HashMap<>();
        map.put("key",DBUtils.APPKEY);
        map.put("table",DBUtils.TABLE);
        map.put("k",k);
        map.put("v",v);

        Log.e("加密的k",k);
        Log.e("加密的v",v);

        HttpHelper.getInstance().getServiceApi()
                .saveData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SaveResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("请求失败！");
                        Log.e("saveData失败",e.toString());
                    }

                    @Override
                    public void onNext(SaveResult saveResult) {
                        if (saveResult.getRetCode().equals("200")){
                            Log.e("saveData成功",""+saveResult.toString());
                        }
                    }
                });
    }

    public static void queryData(String k){
        /**先进行编码**/
        k = new String(EncodeUtils.base64Encode(k));

        Map<String,String> map = new HashMap<>();
        map.put("key",DBUtils.APPKEY);
        map.put("table",DBUtils.TABLE);
        map.put("k",k);

        Log.e("加密的k",k);

        HttpHelper.getInstance().getServiceApi()
                .queryData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SaveResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("请求失败！");
                        Log.e("queryData失败",e.toString());
                    }

                    @Override
                    public void onNext(SaveResult saveResult) {

                    }
                });
    }
}
