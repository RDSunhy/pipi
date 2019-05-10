package com.study.shy.pipi.ui.mainfragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseFragment;
import com.study.shy.pipi.ui.main.FragmentAdapter;
import com.study.shy.pipi.ui.subscibe.AdFragment;
import com.study.shy.pipi.ui.subscibe.DailyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment extends BaseFragment {

    @BindView(R.id.tab_Layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    @BindView(R.id.fbn_refresh)
    FloatingActionButton fbnRefresh;

    List<Fragment> fragmentList;
    @Override
    protected int setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new DailyFragment());
        fragmentList.add(new AdFragment());
        fragmentList.add(new DailyFragment());
        fragmentList.add(new AdFragment());
        FragmentAdapter adapter = new FragmentAdapter(getFragmentManager(),fragmentList);
        vpFragment.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpFragment);
        tabLayout.getTabAt(0).setText("推荐");
        tabLayout.getTabAt(1).setText("广告");
        tabLayout.getTabAt(2).setText("推荐2");
        tabLayout.getTabAt(3).setText("广告2");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.fbn_refresh)
    public void onViewClicked() {
    }

}
