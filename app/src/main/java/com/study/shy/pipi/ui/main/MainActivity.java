package com.study.shy.pipi.ui.main;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseActivity;
import com.study.shy.pipi.ui.mainfragment.FindFragment;
import com.study.shy.pipi.ui.mainfragment.MainFragment;
import com.study.shy.pipi.ui.mainfragment.MessageFragment;
import com.study.shy.pipi.ui.mainfragment.MineFragment;
import com.study.shy.pipi.ui.view.FriendRequest;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.iv_main)
    ImageView ivMain;
    @BindView(R.id.rv_main)
    LinearLayout rvMain;
    @BindView(R.id.iv_find)
    ImageView ivFind;
    @BindView(R.id.rv_find)
    LinearLayout rvFind;
    @BindView(R.id.iv_post)
    ImageView ivPost;
    @BindView(R.id.rv_post)
    LinearLayout rvPost;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.rv_message)
    LinearLayout rvMessage;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.rv_mine)
    LinearLayout rvMine;

    MainFragment mainFragment;
    FindFragment findFragment;
    MessageFragment messageFragment;
    MineFragment mineFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    EMMessageListener msgListener;
    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        rvMain.setSelected(true);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content,mainFragment = new MainFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void initData() {
        msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                Log.e("收到消息",""+messages.toString());
                //通知聊天列表添加新消息
                EventBus.getDefault().postSticky(messages);
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);


        /**
         * 同意好友申请
         * EMClient.getInstance().contactManager().acceptInvitation(username);
         * 拒绝好友申请
         * EMClient.getInstance().contactManager().declineInvitation(username);
         */
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            @Override
            public void onContactInvited(String username, String reason) {
                //收到好友邀请
                Log.e("ContactListener收到好友请求",username+"|"+reason);
                Looper.prepare();
                showFriendRequest(username,reason);
                Looper.loop();
            }

            @Override
            public void onFriendRequestAccepted(String username) {
                //被接受
                Log.e("ContactListener好友请求被接受",username);
            }

            @Override
            public void onFriendRequestDeclined(String username) {
                //被拒绝
                Log.e("ContactListener好友请求被拒绝",username);
            }

            @Override
            public void onContactDeleted(String username) {
                //被删除时回调此方法
                Log.e("ContactListener被删除",username);
            }


            @Override
            public void onContactAdded(String username) {
                //增加了联系人时回调此方法
                Log.e("ContactListener新增联系人",username);
            }
        });
    }


    @OnClick({R.id.rv_main, R.id.rv_find, R.id.rv_post, R.id.rv_message, R.id.rv_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rv_main:
                showFragment(1);
                break;
            case R.id.rv_find:
                showFragment(2);
                break;
            case R.id.rv_post:
                FriendRequest requestDialog;
                //实例化自定义的dialog
                requestDialog = new FriendRequest(MainActivity.this,"asd","asd",R.layout.dialog_friend_request,new int[]{R.id.bn_agree,R.id.bn_refuse});
                //绑定点击事件
                requestDialog.setOnCenterItemClickListener(new FriendRequest.OnCenterItemClickListener() {
                    @Override
                    public void OnCenterItemClick(FriendRequest dialog, View view) {
                        switch (view.getId()){
                            case R.id.bn_agree:

                                break;
                            case R.id.bn_refuse:

                                break;
                        }
                    }
                });
                //显示
                requestDialog.show();
                break;
            case R.id.rv_message:
                showFragment(3);
                break;
            case R.id.rv_mine:
                showFragment(4);
                break;
        }
    }

    private void showFragment(int index) {
        fragmentTransaction = fragmentManager.beginTransaction(); //开启事务
        hideAllFragment(fragmentTransaction);
        removeSeleted();
        switch (index){
            case 1:
                rvMain.setSelected(true);
                if (mainFragment == null){
                    mainFragment = new MainFragment();
                    fragmentTransaction.add(R.id.fragment_content,mainFragment);
                }else {
                    fragmentTransaction.show(mainFragment);
                }
                break;
            case 2:
                rvFind.setSelected(true);
                if (findFragment == null){
                    findFragment = new FindFragment();
                    fragmentTransaction.add(R.id.fragment_content,findFragment);
                }else {
                    fragmentTransaction.show(findFragment);
                }
                break;
            case 3:
                rvMessage.setSelected(true);
                if (messageFragment == null){
                    messageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.fragment_content,messageFragment);
                }else {
                    fragmentTransaction.show(messageFragment);
                }
                break;
            case 4:
                rvMine.setSelected(true);
                if (mineFragment == null){
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.fragment_content,mineFragment);
                }else {
                    fragmentTransaction.show(mineFragment);
                }
                break;
           default:
                break;
        }
        fragmentTransaction.commit();
    }

    //设置所有按钮都是默认都不选中
    private void removeSeleted() {
        rvMain.setSelected(false);
        rvFind.setSelected(false);
        rvMessage.setSelected(false);
        rvMine.setSelected(false);
        rvPost.setSelected(false);
    }

    //删除所有Fragment
    private void hideAllFragment(FragmentTransaction transaction) {
        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }




    @Override
    public void onBackPressed() {
        if(Jzvd.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        Jzvd.releaseAllVideos();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
        new Thread(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().logout(true);
            }
        }).start();
        super.onDestroy();
    }

    public void showFriendRequest(String username,String reason){
        FriendRequest requestDialog;
        //实例化自定义的dialog
        requestDialog = new FriendRequest(MainActivity.this,username,reason,R.layout.dialog_friend_request,new int[]{R.id.bn_agree,R.id.bn_refuse});
        //绑定点击事件
        requestDialog.setOnCenterItemClickListener(new FriendRequest.OnCenterItemClickListener() {
            @Override
            public void OnCenterItemClick(FriendRequest dialog, View view) {
                switch (view.getId()){
                    case R.id.bn_agree:
                        try {
                            EMClient.getInstance().contactManager().acceptInvitation(username);
                            ToastUtils.showShort("添加成功");
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("添加失败");
                            Log.e("添加好友失败",""+e.getErrorCode()+"|"+e.getDescription());
                        }
                        break;
                    case R.id.bn_refuse:
                        try {
                            EMClient.getInstance().contactManager().declineInvitation(username);
                            ToastUtils.showShort("拒绝成功");
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("拒绝失败");
                            Log.e("拒绝好友失败",""+e.getErrorCode()+"|"+e.getDescription());
                        }
                        break;
                }
            }
        });
        //显示
        requestDialog.show();
    }
}
