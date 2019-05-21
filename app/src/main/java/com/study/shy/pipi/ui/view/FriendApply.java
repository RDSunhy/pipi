package com.study.shy.pipi.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.study.shy.pipi.R;

public class FriendApply extends Dialog {

    //在构造方法里提前加载了样式
    private Context context;//上下文
    private int layoutResID;//布局文件id

    EditText etApplyUser,etApplyReason;

    public FriendApply(Context context){
        super(context,R.style.AddFriend);//加载dialog的样式
        this.context = context;
        this.layoutResID = R.layout.dialog_friend_apply;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中
        //dialogWindow.setWindowAnimations();设置动画效果
        setContentView(layoutResID);

        WindowManager windowManager = ((Activity)context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*2/3;// 设置dialog宽度为屏幕的4/5
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失
        etApplyUser = findViewById(R.id.et_apply_user);
        etApplyReason = findViewById(R.id.et_apply_reason);
        findViewById(R.id.bn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etApplyUser.getText().toString();
                String reason = etApplyReason.getText().toString();
                try {
                    EMClient.getInstance().contactManager().addContact(username, reason);
                    ToastUtils.showShort("请等待对方验证");
                    dismiss();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    ToastUtils.showShort("添加好友失败！");
                    Log.e("添加好友失败",""+e.getDescription()+"|"+e.getErrorCode());
                }
            }
        });
    }



}
