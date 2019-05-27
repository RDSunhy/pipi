package com.study.shy.pipi.ui.mainfragment;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.ActivityCollector;
import com.study.shy.pipi.base.BaseFragment;
import com.study.shy.pipi.base.Constants;
import com.study.shy.pipi.bean.event.SetIconBean;
import com.study.shy.pipi.bean.event.UserBean;
import com.study.shy.pipi.ui.dialog.FriendApplyDialog;
import com.study.shy.pipi.ui.dialog.MenuDialog;
import com.study.shy.pipi.ui.login.LoginActivity;
import com.study.shy.pipi.util.ConvertUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_quit)
    TextView tvQuit;
    @BindView(R.id.tv_quit_login)
    TextView tvQuitLogin;

    PopupWindow popupWindow;

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
        String imageString = SPUtils.getInstance(Constants.FILENAME).getString(Constants.USER_ICON,null);
        if(imageString != null){
            /*byte[] byteArray= Base64.decode(imageString, Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
            Bitmap bitmap=BitmapFactory.decodeStream(byteArrayInputStream);*/
            Glide.with(getActivity())
                    .load(ConvertUtils.stringToBitmap(imageString))
                    .centerCrop()
                    .error(R.mipmap.img_login_qq)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(ivUserIcon);
        }else {
            Glide.with(getActivity()).load(bean.getIcon())
                    .centerCrop()
                    .error(R.mipmap.img_login_qq)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(ivUserIcon);
        }
        tvAccount.setText("ID:" + bean.getAccount());
        tvUsername.setText(bean.getName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateIcon(SetIconBean bean){
        Glide.with(getActivity())
                .load(bean.getBitmap())
                .centerCrop()
                .error(R.mipmap.img_login_qq)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(ivUserIcon);
       /* ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bean.getBitmap().compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        byte[] byteArray=byteArrayOutputStream.toByteArray();
        String imageString=new String(Base64.encodeToString(byteArray, Base64.DEFAULT));*/
        SPUtils.getInstance(Constants.FILENAME).put(Constants.USER_ICON,ConvertUtils.bitmapToString(bean.getBitmap()));
        ToastUtils.showShort("头像更换成功");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.iv_user_icon,R.id.tv_about,R.id.tv_collect,R.id.tv_author,
            R.id.tv_quit,R.id.tv_quit_login})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_user_icon:
                MenuDialog dialog = new MenuDialog();
                dialog.show(getFragmentManager(),"MineFragment");
                break;
            case R.id.tv_quit:
                ActivityCollector.finishAll();
                break;
            case R.id.tv_quit_login:
                Platform platform;
                platform = ShareSDK.getPlatform(QQ.NAME);
                platform.removeAccount(true);
                getActivity().finish();
                break;
            case R.id.tv_collect:
                break;
            case R.id.tv_about:
                break;
            case R.id.tv_author:
                break;
        }
    }

    public void showPopWindows(){
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_menu,null,false);
        popupWindow = new PopupWindow(view,ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.MATCH_PARENT,true);
        //引入依附的布局
        View popParent = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, null);
        popupWindow.showAtLocation(popParent,Gravity.BOTTOM,0,0);
    }

    private void addBackground() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp =getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;//调节透明度
        getActivity().getWindow().setAttributes(lp);
        //dismiss时恢复原样
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
    }
}
