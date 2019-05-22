package com.study.shy.pipi.base;

import android.app.Application;
import android.content.IntentFilter;
import android.support.multidex.MultiDex;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.mob.MobSDK;
import com.study.shy.pipi.receive.CallReceiver;

public class BaseApplication extends Application {

    CallReceiver callReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        MobSDK.init(this);

        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        //options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        //options.setAutoDownloadThumbnail(true);
        options.setAutoLogin(false);
        //初始化
        EMClient.getInstance().init(getApplicationContext(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

        // 设置通话广播监听器
        IntentFilter callFilter = new IntentFilter(EMClient.getInstance()
                .callManager()
                .getIncomingCallBroadcastAction());
        if (callReceiver == null) {
            callReceiver = new CallReceiver();
        }
        //注册通话广播接收者
        getApplicationContext().registerReceiver(callReceiver, callFilter);
    }
}
