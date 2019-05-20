package com.study.shy.pipi.ui.mainfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseFragment;
import com.study.shy.pipi.bean.event.UserBean;
import com.study.shy.pipi.ui.view.AddFriend;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_add_friend)
    TextView tvAddFriend;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void setUserInfo(UserBean bean) {
        Glide.with(getActivity()).load(bean.getIcon())
                .centerCrop()
                .error(R.mipmap.img_login_qq)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(ivUserIcon);
        tvAccount.setText("ID:" + bean.getAccount());
        tvUsername.setText(bean.getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.tv_msg,R.id.tv_add_friend})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_msg:

                break;
            case R.id.tv_add_friend:
                AddFriend dialog = new AddFriend.Builder(getContext()).create();
                dialog.show();
                break;
        }
    }
}
