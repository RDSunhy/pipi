package com.study.shy.pipi.ui.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.LinearGradient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMServiceNotReadyException;
import com.hyphenate.exceptions.HyphenateException;
import com.mob.MobSDK;
import com.mob.imsdk.MobIM;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseActivity;
import com.study.shy.pipi.base.Constants;
import com.study.shy.pipi.bean.SaveResult;
import com.study.shy.pipi.bean.event.UserBean;
import com.study.shy.pipi.http.HttpHelper;
import com.study.shy.pipi.ui.chat.ChatActivity;
import com.study.shy.pipi.ui.chat.VideoActivity;
import com.study.shy.pipi.ui.main.MainActivity;
import com.study.shy.pipi.util.DBUtils;
import com.study.shy.pipi.util.EncodeUtils;
import com.study.shy.pipi.util.GsonUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_login_qq)
    ImageView ivLoginQq;
    @BindView(R.id.iv_login_wechat)
    ImageView ivLoginWechat;
    @BindView(R.id.tv_test)
    TextView tvTest;

    String account;
    PlatformDb platDB;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    /**注册完成后 尝试登录**/
                    EMClient.getInstance().login(account,account,new EMCallBack() {//回调
                        @Override
                        public void onSuccess() {
                            Log.e("登录2", "登录聊天服务器成功！");
                            loginSuccess(account,platDB.getUserName(),platDB.getUserIcon());
                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }

                        @Override
                        public void onError(int code, String message) {
                            Log.e("登录2", "登录聊天服务器成功！");
                            ToastUtils.showShort("别登了！这APP不适合你！");
                        }
                    });
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    public int intiLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_login_qq, R.id.iv_login_wechat,R.id.tv_test})
    public void onViewClicked(View view) {
        showLoading();
        Platform platform;
        switch (view.getId()) {
            case R.id.iv_login_qq:
                platform = ShareSDK.getPlatform(QQ.NAME);
                break;
            case R.id.iv_login_wechat:
                platform = ShareSDK.getPlatform(Wechat.NAME);
                break;
            case R.id.tv_test:
                EMClient.getInstance().login("admin","admin",new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Log.e("登录1", "登录聊天服务器成功！");
                        ToastUtils.showShort("登录聊天服务器成功！");
                        SPUtils.getInstance("USER").put("user","admin");
                        dismissLoading();
                        SPUtils.getInstance(Constants.FILENAME).put(Constants.USER_ACCOUNT,"admin");
                        SPUtils.getInstance(Constants.FILENAME).put(Constants.USER_NAME,"admin");
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        Log.e("登录1失败", ""+message);
                        Log.e("登录1失败", ""+code);
                        Log.e("登录1失败", ""+account);
                        Log.e("登录1", "登录聊天服务器失败！");
                    }
                });
            default:
                platform = ShareSDK.getPlatform(QQ.NAME);
                break;
        }
        //ShareSDK.setActivity(LoginActivity.this);//抖音登录适配安卓9.0
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
                ToastUtils.showShort("登录失败！"+arg2.getMessage());
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                arg0.getDb().exportData();
                Iterator ite =arg2.entrySet().iterator();
                while (ite.hasNext()) {
                    Map.Entry entry = (Map.Entry)ite.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    Log.e("平台信息",key+":"+value);
                }
                //用户资源都保存到res
                //通过打印res数据看看有哪些数据是你想要的
                if (arg1 == Platform.ACTION_USER_INFOR) {
                    platDB = arg0.getDb();//获取数平台数据DB
                    //通过DB获取各种数据
                    platDB.getToken();
                    platDB.getUserGender();
                    platDB.getUserIcon();
                    platDB.getUserId();
                    platDB.getUserName();
                    platDB.getTokenSecret();
                    Log.e("QQID",""+platDB.getUserId());
                    /**
                     * 截取前八位 作为登录账号密码
                     * 先登录 登录失败则注册后 再登录
                     */
                    account = platDB.getUserId().substring(0,8).toLowerCase();
                    EMClient.getInstance().login(account,account,new EMCallBack() {//回调
                        @Override
                        public void onSuccess() {
                            EMClient.getInstance().groupManager().loadAllGroups();
                            EMClient.getInstance().chatManager().loadAllConversations();
                            Log.e("登录1", "登录聊天服务器成功！");
                            ToastUtils.showShort("登录聊天服务器成功！");
                            loginSuccess(account,platDB.getUserName(),platDB.getUserIcon());
                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }

                        @Override
                        public void onError(int code, String message) {
                            Log.e("登录1失败", ""+message);
                            Log.e("登录1失败", ""+code);
                            Log.e("登录1失败", ""+account);
                            Log.e("登录1", "登录聊天服务器失败！");
                            /**登录失败 未注册 注册后 继续尝试登录**/
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        EMClient.getInstance().createAccount(account, account);//同步方法
                                        ToastUtils.showShort("检测到您未注册，自动帮您注册成功！");
                                        Message message = new Message();
                                        message.what = 1;
                                        handler.sendMessage(message);
                                    } catch (HyphenateException e) {
                                        e.printStackTrace();
                                        Log.e("注册失败",""+e.getErrorCode());
                                        Log.e("注册失败",""+e.getDescription());
                                        ToastUtils.showShort("注册失败！");
                                    }
                                }
                            }).start();
                        }
                    });

                }
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub
                ToastUtils.showShort("您没有登录哦~");
            }
        });
        platform.showUser(null);
        //authorize
        //qq.authorize();//要功能不要数据，在监听oncomplete中不会返回用户数据
        //想要移除授权状态，在想移除的地方执行下面的方法即可
        //weibo.removeAccount(true);
    }

    public void loginSuccess(String account,String name,String icon){
        SPUtils.getInstance(Constants.FILENAME).put(Constants.USER_ACCOUNT,account);
        SPUtils.getInstance(Constants.FILENAME).put(Constants.USER_NAME,name);
        RxPermissions.getInstance(LoginActivity.this)
                .request(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if(aBoolean){
                            EventBus.getDefault().postSticky(new UserBean(account,name,icon));
                            SPUtils.getInstance("USER").put("user",account);
                            dismissLoading();
                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                        }else {
                            ToastUtils.showShort("请赋予对应的权限！");
                        }
                    }
                });

    }


}
