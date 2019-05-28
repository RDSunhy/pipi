package com.study.shy.pipi.ui.dialog;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.king.zxing.util.CodeUtils;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.Constants;
import com.study.shy.pipi.bean.event.RefreshBean;

import org.greenrobot.eventbus.EventBus;

public class QRCodeDialog extends DialogFragment implements View.OnClickListener {

    ImageView ivQrCode;
    Button bnOk;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER; //中间
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);

        return createView(inflater, container);
    }

    //重写此方法,设置布局文件
    protected View createView(LayoutInflater inflater, ViewGroup container){
        View v = inflater.inflate(R.layout.dialog_qr_code,container);
        ivQrCode = v.findViewById(R.id.iv_qr_code);
        bnOk = v.findViewById(R.id.bn_ok);
        bnOk.setOnClickListener(this::onClick);
        String account = SPUtils.getInstance(Constants.FILENAME).getString(Constants.USER_ACCOUNT,null);
        if (account != null){
            ivQrCode.setImageBitmap(CodeUtils.createQRCode(account,600,null));
        }else{
            ToastUtils.showShort("生成失败！请重新登录");
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bn_ok:
                break;
        }
        dismiss();
    }
}
