package com.study.shy.pipi.ui.mainfragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseFragment;
import com.study.shy.pipi.bean.CategoryBean;
import com.study.shy.pipi.http.HttpManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FindFragment extends BaseFragment {

    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;

    @Override
    protected int setLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        HttpManager.getInstance().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CategoryBean categoryBeans) {
                        getCategoriesSuccess(categoryBeans.getItemList());
                    }
                });
    }

    public void getCategoriesSuccess(List<CategoryBean.ItemListBean> list) {
        Log.e("获得的分类bean",""+list.toString());
        CategoryAdapter adapter = new CategoryAdapter(getContext(), list);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rvCategories.setLayoutManager(manager);
        rvCategories.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }

}
