package com.study.shy.pipi.util;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.study.shy.pipi.bean.SaveResult;
import com.study.shy.pipi.http.HttpHelper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterUtils {

    public final static String ORG_NAME = "1102190517019891";
    public final static String APP_NAME = "pipi";

    public static boolean register(String account,String name){

        final boolean[] isSuccess = {false};

        Map<String,String> map = new HashMap<>();
        map.put("username",account);
        map.put("password",account);
        map.put("nickname",name);

        HttpHelper.getInstance().getServiceApi()
                .register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("注册失败",""+e.toString());
                        Log.e("注册失败",""+e.getMessage());
                        ToastUtils.showShort("注册失败！");
                    }

                    @Override
                    public void onNext(Response response) {
                        Log.e("响应码",""+response.code());
                        if(response.code() == 200){
                            //注册成功
                            isSuccess[0] = true;
                        }
                    }
                });
        return isSuccess[0];
    }
}
