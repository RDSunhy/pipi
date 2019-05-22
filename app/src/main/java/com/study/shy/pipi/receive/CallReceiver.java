package com.study.shy.pipi.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hyphenate.chat.EMClient;
import com.study.shy.pipi.ui.chat.VideoActivity;

public class CallReceiver extends BroadcastReceiver {

    public CallReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 判断环信是否登录成功
        if (!EMClient.getInstance().isLoggedInBefore()) {
            return;
        }

        // 呼叫方的usernmae
        String callFrom = intent.getStringExtra("from");
        // 呼叫类型，有语音和视频两种
        String callType = intent.getStringExtra("type");
        // 呼叫接收方，
        String callTo = intent.getStringExtra("to");
        // 获取通话时的扩展字段
        String callExt = EMClient.getInstance().callManager().getCurrentCallSession().getExt();
        Intent callIntent = new Intent();
        // 根据通话类型跳转到语音通话或视频通话界面
        if (callType.equals("video")) {
            // 设置当前通话类型为视频通话
        } else if (callType.equals("voice")) {
            // 设置当前通话类型为语音通话
        }
        callIntent.setClass(context, VideoActivity.class);
        // 设置 activity 启动方式
        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callIntent.putExtra("type","2");
        context.startActivity(callIntent);
    }
}
