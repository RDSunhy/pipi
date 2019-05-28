package com.study.shy.pipi.ui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.study.shy.pipi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendMenuPop extends PopupWindow {

    Context mContext;
    @BindView(R.id.tv_add_friend)
    TextView tvAddFriend;
    @BindView(R.id.tv_add_scan)
    TextView tvAddScan;
    @BindView(R.id.tv_qr_code)
    TextView tvQrCode;


    public FriendMenuPop(Context context) {
        super(context);
        this.mContext = context;
    }

    public void initView() {
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.dialog_pop_friendmenu, null, false);
        setContentView(view);

    }

    @OnClick({R.id.tv_add_friend, R.id.tv_add_scan, R.id.tv_qr_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add_friend:
                ToastUtils.showShort("单击");
                break;
            case R.id.tv_add_scan:
                break;
            case R.id.tv_qr_code:
                break;
        }
    }
}
