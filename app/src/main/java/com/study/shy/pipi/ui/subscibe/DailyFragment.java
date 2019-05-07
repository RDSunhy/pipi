package com.study.shy.pipi.ui.subscibe;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseFragment;
import com.study.shy.pipi.bean.HotBean;
import com.study.shy.pipi.http.HttpManager;
import com.study.shy.pipi.util.TimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DailyFragment extends BaseFragment {

    @BindView(R.id.rv_context)
    RecyclerView rvContext;

    DailyAdapter adapter;
    List<HotBean.IssueListBean.ItemListBean> list;
    long nextPager;
    Date date;
    int num=0;
    @Override
    protected int setLayout() {
        return R.layout.fragment_common_main;
    }

    @Override
    protected void initView() {
        date = TimeUtils.getNowDate();
    }

    @Override
    protected void initData() {
        String timeStamp = String.valueOf(TimeUtils.getNowMills());
        //Log.e("当前时间戳",""+timeStamp);
        Map<String,String> map = new HashMap<>();
        map.put("date","1556467200000");
        HttpManager.getInstance().getDaily(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HotBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),"请求失败！",Toast.LENGTH_SHORT).show();
                        Log.e("失败异常",""+e.getMessage());
                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        getDailySuccess(hotBean);
                    }
                });
    }

    public void getDailySuccess(HotBean hotBean){
        num++;
        date = TimeUtils.getSubtractDay(num);
        list = new ArrayList<>();
        for (int i=0;i<hotBean.getIssueList().size();i++){
            for (int j=0 ;j<hotBean.getIssueList().get(i).getItemList().size(); j++){
                list.add(hotBean.getIssueList().get(i).getItemList().get(j));
            }
        }
        Log.e("getDailySuccess",""+hotBean.toString());
        Log.e("getDailySuccess",""+list.toString());
        adapter = new DailyAdapter(getContext(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvContext.setLayoutManager(manager);
        rvContext.setAdapter(adapter);
        rvContext.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                Map<String,String> map2 = new HashMap<>();
                map2.put("date",String.valueOf(TimeUtils.date2Millis(date)));
                Log.e("date",""+map2.toString());
                Log.e("vc","83");
                HttpManager.getInstance().getDaily(map2)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<HotBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getContext(),"请求失败！",Toast.LENGTH_SHORT).show();
                                Log.e("失败异常",""+e.getMessage());
                            }

                            @Override
                            public void onNext(HotBean bean) {
                                Log.e("加载更多",""+bean.toString());
                                num++;
                                date = TimeUtils.getSubtractDay(num);
                                Log.e("减一天的时间",""+date.toString());
                                adapter.updateData(bean);
                            }
                        });
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

}
