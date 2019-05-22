package com.study.shy.pipi.ui.chat;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.EMServiceNotReadyException;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import rx.functions.Action1;

public class ChatActivity extends BaseActivity {

    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.rv_msg)
    RecyclerView rvMsg;
    @BindView(R.id.et_msg)
    EditText etMsg;
    @BindView(R.id.bn_send)
    Button bnSend;
    @BindView(R.id.bn_video_call)
    Button bnVideoCall;

    String msg;
    String username;
    MessageAdapter adapter;
    List<EMMessage> messages = new ArrayList<>();
    @Override
    public int intiLayout() {
        return R.layout.activity_chat;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        Intent i = getIntent();
        username = i.getStringExtra("username");
        Log.e("聊天对象",""+username);
        tvUsername.setText(username);
        bnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = etMsg.getText().toString();
                if (msg==null||msg.equals("")){
                    ToastUtils.showShort("不能发送空消息！");
                }else {
                    //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                    EMMessage message = EMMessage.createTxtSendMessage(msg, username);
                    //如果是群聊，设置chattype，默认是单聊
                    //if (chatType == CHATTYPE_GROUP)
                    //    message.setChatType(ChatType.GroupChat);
                    //发送消息
                    EMClient.getInstance().chatManager().sendMessage(message);
                    adapter.addMsg(message);
                    rvMsg.scrollToPosition(messages.size()-1);
                    etMsg.setText("");
                }
            }
        });
        bnVideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions.getInstance(ChatActivity.this)
                        .request(Manifest.permission.CAMERA)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if(aBoolean){
                                    try {//单参数
                                        EMClient.getInstance().callManager().makeVideoCall(username);
                                        Intent intent = new Intent(ChatActivity.this,VideoActivity.class);
                                        intent.putExtra("type","1");
                                        startActivity(intent);
                                    } catch (EMServiceNotReadyException e) {
                                        // TODO Auto-generated catch block
                                        ToastUtils.showShort("拨打失败！");
                                        Log.e("拨打失败",e.getErrorCode()+"|"+e.getDescription());
                                    }
                                }else {
                                    ToastUtils.showShort("请赋予相机权限！");
                                }
                            }
                        });
            }
        });

    }

    @Override
    public void initData() {
        //获取最近聊天记录
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
        //获取此会话的所有消息
        try{
            messages = conversation.getAllMessages();
            Log.e("此回话所有消息",messages.toString());
        }catch (Exception e){
            Log.e("异常信息",e.getMessage());
        }
        //SDK初始化加载的聊天记录为20条，到顶时需要去DB里获取更多
        //获取startMsgId之前的pagesize条消息，此方法获取的messages SDK会自动存入到此会话中，APP中无需再次把获取到的messages添加到会话中
        // List<EMMessage> messages = conversation.loadMoreMsgFromDB(startMsgId, pagesize);
        adapter = new MessageAdapter(this,messages);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvMsg.setLayoutManager(manager);
        rvMsg.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void receiverMsg(List<EMMessage> msgList){
        Log.e("eventBus",msgList.toString());
        for(int i = 0;i<msgList.size();i++){
            adapter.addMsg(msgList.get(i));
            rvMsg.scrollToPosition(messages.size()-1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
