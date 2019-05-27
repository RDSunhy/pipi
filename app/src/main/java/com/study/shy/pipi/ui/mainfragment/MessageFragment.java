package com.study.shy.pipi.ui.mainfragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseFragment;
import com.study.shy.pipi.bean.event.RefreshBean;
import com.study.shy.pipi.ui.dialog.FriendApplyDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class MessageFragment extends BaseFragment {

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
            @Override
            public void onClick(View v) {
                FriendApplyDialog friendApplyDialog = new FriendApplyDialog(getContext());
                friendApplyDialog.show();
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
}
