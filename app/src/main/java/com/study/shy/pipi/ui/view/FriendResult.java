package com.study.shy.pipi.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.study.shy.pipi.R;

public class FriendResult extends Dialog {

    //在构造方法里提前加载了样式
    private Context context;//上下文
    private int layoutResID;//布局文件id

    String user,content;
    TextView tvUser,tvReason;

    public FriendResult(Context context, String user, String content){
        super(context,R.style.AddFriend);//加载dialog的样式
        this.context = context;
        this.layoutResID = R.layout.dialog_result;
        this.user = user;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中
        //dialogWindow.setWindowAnimations();设置动画效果
        setContentView(layoutResID);

        tvUser = findViewById(R.id.tv_username);
        tvReason = findViewById(R.id.tv_content);

        tvUser.setText(""+user);
        tvReason.setText(""+content);

        findViewById(R.id.bn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        WindowManager windowManager = ((Activity)context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*2/3;// 设置dialog宽度为屏幕的4/5
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失

    }

}
