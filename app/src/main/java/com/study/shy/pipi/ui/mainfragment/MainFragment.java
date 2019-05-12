package com.study.shy.pipi.ui.mainfragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseFragment;
import com.study.shy.pipi.eventbean.SubBean;
import com.study.shy.pipi.ui.main.FragmentAdapter;
import com.study.shy.pipi.ui.subscibe.AdFragment;
import com.study.shy.pipi.ui.subscibe.DailyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jzvd.Jzvd;

public class MainFragment extends BaseFragment {

    @BindView(R.id.tab_Layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;

    List<Fragment> fragmentList;
    FragmentAdapter adapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        fragmentList = new ArrayList<>();
        fragmentList.add(new DailyFragment());
        adapter = new FragmentAdapter(getFragmentManager(),fragmentList);
        vpFragment.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpFragment);
        tabLayout.getTabAt(0).setText("推荐");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            //ToastUtils.showShort("隐藏");
            Jzvd.releaseAllVideos();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addSub(SubBean subBean){
        Log.e("进入addSub",""+subBean.toString());
        if(subBean.isSub()){
            fragmentList.add(new AdFragment());
            tabLayout.getTabAt(0).setText("推荐");
            tabLayout.getTabAt(fragmentList.size()).setText(subBean.getCategoryName());
        }else {
            return;
        }
        adapter.updateData(fragmentList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
