package com.study.shy.pipi.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.WindowManager;

import com.study.shy.pipi.R;
import com.wang.avi.AVLoadingIndicatorView;


public class LoadingDialog extends Dialog {

    Context context;
    AVLoadingIndicatorView avi;

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context,themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        //空白处不能取消动画
        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams lp=this.getWindow().getAttributes();
        lp.dimAmount=0.5f;
        this.getWindow().setAttributes(lp);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        avi = findViewById(R.id.avi);
        avi.show();
    }
}