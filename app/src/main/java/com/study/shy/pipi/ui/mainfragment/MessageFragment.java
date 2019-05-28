package com.study.shy.pipi.ui.mainfragment;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.king.zxing.CaptureActivity;
import com.king.zxing.util.CodeUtils;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseFragment;
import com.study.shy.pipi.bean.event.RefreshBean;
import com.study.shy.pipi.ui.dialog.FriendApplyDialog;
import com.study.shy.pipi.ui.dialog.FriendMenuPop;
import com.study.shy.pipi.ui.dialog.QRCodeDialog;
import com.study.shy.pipi.util.ScanActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

public class MessageFragment extends BaseFragment {

    private int RESULT_SCAN = 1;

    @BindView(R.id.rv_friend)
    RecyclerView rvFriend;
    @BindView(R.id.iv_add_friend)
    ImageView ivAddFriend;

    List<String> friendList;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //Log.e("获取的好友列表",""+friendList.toString());
                    FriendListAdapter adapter = new FriendListAdapter(getContext(),friendList,getFragmentManager());
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    rvFriend.setLayoutManager(manager);
                    rvFriend.setAdapter(adapter);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        ivAddFriend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vPopupWindow = inflater.inflate(R.layout.dialog_pop_friendmenu, null, false);//引入弹窗布局
                PopupWindow popupWindow = new PopupWindow(vPopupWindow, 250, ActionBar.LayoutParams.WRAP_CONTENT, true);
                //引入依附的布局
                View parentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_message, null);
                //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
                popupWindow.showAsDropDown(ivAddFriend, Gravity.BOTTOM, 0, 0);
                vPopupWindow.findViewById(R.id.tv_add_friend).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        FriendApplyDialog friendApplyDialog = new FriendApplyDialog(getContext());
                        friendApplyDialog.show();
                    }
                });
                vPopupWindow.findViewById(R.id.tv_add_scan).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        getActivity().startActivityForResult(new Intent(getContext(),ScanActivity.class),RESULT_SCAN);
                    }
                });
                vPopupWindow.findViewById(R.id.tv_qr_code).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        QRCodeDialog dialog = new QRCodeDialog();
                        dialog.show(getFragmentManager(),"MessageFragment");
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {
        refreshFriendList();
    }

    public void refreshFriendList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    friendList = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.e("获取好友列表失败",e.getErrorCode()+"|"+e.getDescription());
                    ToastUtils.showShort("获取好友列表失败！");
                }
            }
        }).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshFriendList();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            refreshFriendList();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(RefreshBean bean){
        if (bean.isRefresh()){
            refreshFriendList();
        }
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("进入onActivityResult","onActivityResult");
        if(requestCode == RESULT_SCAN && resultCode == RESULT_OK){
            Log.e("进入resultif语句","进入resultif语句");
            String result = data.getStringExtra(CaptureActivity.KEY_RESULT);
            if (result.equals("admin")||result.length()==8){
                try {
                    EMClient.getInstance().contactManager().addContact(result, "对方通过扫一扫方式请求添加您为好友");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    ToastUtils.showShort("添加好友失败！");
                    Log.e("添加好友失败",""+e.getDescription()+"|"+e.getErrorCode());
                }
            }else {
                ToastUtils.showShort("您扫描的码不是ID哦");
            }
        }
    }*/
}
