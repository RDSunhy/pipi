package com.study.shy.pipi.ui.chat;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.hyphenate.media.EMLocalSurfaceView;
import com.hyphenate.media.EMOppositeSurfaceView;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoActivity extends BaseActivity {

    @BindView(R.id.bn_end)
    Button bnEnd;
    @BindView(R.id.bn_answer)
    Button bnAnswer;
    @BindView(R.id.sv_local)
    EMLocalSurfaceView svLocal;
    @BindView(R.id.sv_opposite)
    EMOppositeSurfaceView svOpposite;

    @Override
    public int intiLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void initView() {
        Intent i = getIntent();
        String type = i.getStringExtra("type");
        if(type.equals("1")){
            //拨打
            bnAnswer.setVisibility(View.GONE);
        }else if(type.equals("2")){
            //接听
            bnEnd.setVisibility(View.GONE);
        }
        //EMClient.getInstance().callManager().getCallOptions().setMaxVideoFrameRate(30);
        EMClient.getInstance().callManager().addCallStateChangeListener(new EMCallStateChangeListener() {
            @Override
            public void onCallStateChanged(CallState callState, CallError error) {
                switch (callState) {
                    case IDLE:
                        break;
                    case RINGING:
                        break;
                    case ANSWERING:
                        break;
                    case CONNECTING: // 正在连接对方
                        ToastUtils.showShort("正在连接对方");
                        break;
                    case CONNECTED: // 双方已经建立连接
                        ToastUtils.showShort("双方已经建立连接");
                        break;
                    case ACCEPTED: // 电话接通成功
                        ToastUtils.showShort("电话接通成功");
                        EMClient.getInstance().callManager().setSurfaceView(svLocal, svOpposite);
                        break;
                    case DISCONNECTED: // 电话断了
                        ToastUtils.showShort("电话已经挂断");
                        finish();
                        break;
                    case VOICE_PAUSE:
                        break;
                    case VOICE_RESUME:
                        break;
                    case VIDEO_PAUSE:
                        break;
                    case VIDEO_RESUME:
                        break;
                    case NETWORK_UNSTABLE: //网络不稳定
                        if (error == CallError.ERROR_NO_DATA) {
                            //无通话数据
                            ToastUtils.showShort("无通话数据");
                        } else {
                            ToastUtils.showShort("网络不稳定");
                        }
                        break;
                    case NETWORK_NORMAL: //网络恢复正常

                        break;
                    case NETWORK_DISCONNECTED:
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.bn_end, R.id.bn_answer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bn_end:
                try {
                    EMClient.getInstance().callManager().endCall();
                } catch (EMNoActiveCallException e) {
                    e.printStackTrace();
                    ToastUtils.showShort("挂断失败");
                    Log.e("挂断失败", e.getErrorCode() + "|" + e.getDescription());
                }
                break;
            case R.id.bn_answer:
                try {
                    EMClient.getInstance().callManager().answerCall();
                    bnAnswer.setVisibility(View.GONE);
                    bnEnd.setVisibility(View.VISIBLE);
                } catch (EMNoActiveCallException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    ToastUtils.showShort("接听失败");
                    Log.e("接听失败！",""+e.toString());
                    Log.e("接听失败！",""+e.getMessage());
                    Log.e("接听失败", e.getErrorCode() + "|" + e.getDescription());
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        ToastUtils.showShort("请挂断电话！");
    }
}
