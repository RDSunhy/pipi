package com.study.shy.pipi.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseActivity;
import com.study.shy.pipi.ui.mainfragment.FindFragment;
import com.study.shy.pipi.ui.mainfragment.MainFragment;
import com.study.shy.pipi.ui.mainfragment.MessageFragment;
import com.study.shy.pipi.ui.mainfragment.MineFragment;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.iv_main)
    ImageView ivMain;
    @BindView(R.id.rv_main)
    LinearLayout rvMain;
    @BindView(R.id.iv_find)
    ImageView ivFind;
    @BindView(R.id.rv_find)
    LinearLayout rvFind;
    @BindView(R.id.iv_post)
    ImageView ivPost;
    @BindView(R.id.rv_post)
    LinearLayout rvPost;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.rv_message)
    LinearLayout rvMessage;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.rv_mine)
    LinearLayout rvMine;

    MainFragment mainFragment;
    FindFragment findFragment;
    MessageFragment messageFragment;
    MineFragment mineFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        rvMain.setSelected(true);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content,mainFragment = new MainFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.rv_main, R.id.rv_find, R.id.rv_post, R.id.rv_message, R.id.rv_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rv_main:
                showFragment(1);
                break;
            case R.id.rv_find:
                showFragment(2);
                break;
            case R.id.rv_post:
                break;
            case R.id.rv_message:
                showFragment(3);
                break;
            case R.id.rv_mine:
                showFragment(4);
                break;
        }
    }

    private void showFragment(int index) {
        fragmentTransaction = fragmentManager.beginTransaction(); //开启事务
        hideAllFragment(fragmentTransaction);
        removeSeleted();
        switch (index){
            case 1:
                rvMain.setSelected(true);
                if (mainFragment == null){
                    mainFragment = new MainFragment();
                    fragmentTransaction.add(R.id.fragment_content,mainFragment);
                }else {
                    fragmentTransaction.show(mainFragment);
                }
                break;
            case 2:
                rvFind.setSelected(true);
                if (findFragment == null){
                    findFragment = new FindFragment();
                    fragmentTransaction.add(R.id.fragment_content,findFragment);
                }else {
                    fragmentTransaction.show(findFragment);
                }
                break;
            case 3:
                rvMessage.setSelected(true);
                if (messageFragment == null){
                    messageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.fragment_content,messageFragment);
                }else {
                    fragmentTransaction.show(messageFragment);
                }
                break;
            case 4:
                rvMine.setSelected(true);
                if (mineFragment == null){
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.fragment_content,mineFragment);
                }else {
                    fragmentTransaction.show(mineFragment);
                }
                break;
           default:
                break;
        }
        fragmentTransaction.commit();
    }

    //设置所有按钮都是默认都不选中
    private void removeSeleted() {
        rvMain.setSelected(false);
        rvFind.setSelected(false);
        rvMessage.setSelected(false);
        rvMine.setSelected(false);
        rvPost.setSelected(false);
    }

    //删除所有Fragment
    private void hideAllFragment(FragmentTransaction transaction) {
        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    @Override
    public void onBackPressed() {
        if(Jzvd.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        Jzvd.releaseAllVideos();
        super.onPause();
    }
}
