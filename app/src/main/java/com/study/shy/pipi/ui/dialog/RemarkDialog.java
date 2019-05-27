package com.study.shy.pipi.ui.dialog;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.Constants;
import com.study.shy.pipi.bean.event.RefreshBean;

import org.greenrobot.eventbus.EventBus;

public class RemarkDialog extends DialogFragment implements View.OnClickListener {

    Button bnOk,bnCancel;
    EditText etRemark;

    String saveKey;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        /*etRemark.setText(bundle.getString("remark"));
        etRemark.setSelectAllOnFocus(true);*/
        saveKey = SPUtils.getInstance(Constants.FILENAME).getString(Constants.USER_ACCOUNT)+bundle.getString("remark");
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
        View v = inflater.inflate(R.layout.dialog_addremark,container);
        bnOk = v.findViewById(R.id.bn_ok);
        bnCancel = v.findViewById(R.id.bn_cancel);
        etRemark = v.findViewById(R.id.et_remark);
        bnOk.setOnClickListener(this);
        bnCancel.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bn_ok:
                //保存备注
                SPUtils.getInstance(Constants.FILENAME).put(saveKey,etRemark.getText().toString());
                EventBus.getDefault().post(new RefreshBean(true));
                ToastUtils.showShort("修改备注成功");
                break;
            case R.id.bn_cancel:
                break;
        }
        dismiss();
    }
}
