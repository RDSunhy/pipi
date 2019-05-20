package com.study.shy.pipi.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.study.shy.pipi.R;

public class AddFriend extends Dialog {

    public AddFriend(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        private View mLayout;

        public EditText etUserName;
        public EditText etReason;
        public Button bnAdd;
        public Button bnClose;

        public View.OnClickListener mButtonClickListener;

        public AddFriend mDialog;

        public Builder(Context context) {
            mDialog = new AddFriend(context, R.style.Theme_AppCompat_Dialog);
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //加载布局文件
            mLayout = inflater.inflate(R.layout.dialog_add_friend, null, false);
            //添加布局文件到 Dialog
            mDialog.addContentView(mLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            etUserName = mLayout.findViewById(R.id.et_username);
            etReason = mLayout.findViewById(R.id.et_reason);
            bnAdd = mLayout.findViewById(R.id.bn_add);
            bnClose = mLayout.findViewById(R.id.bn_close);

        }

        /**
         * 通过 ID 设置 Dialog 图标
         */
        /*public Builder setIcon(int resId) {
            mIcon.setImageResource(resId);
            return this;
        }*/

        /**
         * 用 Bitmap 作为 Dialog 图标
         */
        /*public Builder setIcon(Bitmap bitmap) {
            mIcon.setImageBitmap(bitmap);
            return this;
        }*/

        /**
         * 设置 Dialog 标题
         */
        /*public Builder setTitle(@NonNull String title) {
            mTitle.setText(title);
            mTitle.setVisibility(View.VISIBLE);
            return this;
        }*/

        /**
         * 设置 Message
         */
        /*public Builder setMessage(@NonNull String message) {
            mMessage.setText(message);
            return this;
        }*/

        /**
         * 设置按钮文字和监听
         */
        public Builder setButton(View.OnClickListener listener) {
            bnAdd.setOnClickListener(listener);
            return this;
        }

        public AddFriend create() {
            bnClose.setOnClickListener(view -> {
                mDialog.dismiss();
            });
            bnAdd.setOnClickListener(view -> {
                String username = etUserName.getText().toString();
                String reason = etReason.getText().toString();
                //ToastUtils.showShort(""+username+"|"+reason);
                mDialog.dismiss();
                //参数为要添加的好友的username和添加理由
                try {
                    EMClient.getInstance().contactManager().addContact(username, reason);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    ToastUtils.showShort("添加好友失败！");
                    Log.e("添加好友失败",""+e.getDescription()+"|"+e.getErrorCode());
                }
            });
            mDialog.setContentView(mLayout);
            mDialog.setCancelable(true);                //用户可以点击后退键关闭 Dialog
            mDialog.setCanceledOnTouchOutside(false);   //用户不可以点击外部来关闭 Dialog
            return mDialog;
        }
    }
}
