package com.study.shy.pipi.ui.subscibe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.study.shy.pipi.R;
import com.study.shy.pipi.bean.HotBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JzvdStd;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {

    Context mContext;
    List<HotBean.IssueListBean.ItemListBean> mList;

    public DailyAdapter(Context context, List<HotBean.IssueListBean.ItemListBean> list) {
        mContext = context;
        mList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals("video")) {
                mList.add(list.get(i));
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_itme_daily, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int randomnum = (int) (Math.random() * 1000);
        viewHolder.tvUserName.setText(""+mList.get(i).getData().getAuthor().getName());
        viewHolder.tvTitle.setText(mList.get(i).getData().getTitle());
        viewHolder.tvLikeNum.setText("" + mList.get(i).getData().getConsumption().getCollectionCount());
        viewHolder.tvTalkNum.setText("" + randomnum);
        viewHolder.tvTalkNum.setText("" + mList.get(i).getData().getConsumption().getReplyCount());
        viewHolder.tvShareNum.setText("" + mList.get(i).getData().getConsumption().getShareCount());
        viewHolder.jzPlayer.setUp(
                mList.get(i).getData().getPlayUrl(),
                mList.get(i).getData().getTitle(),
                JzvdStd.SCREEN_WINDOW_NORMAL);
        Glide.with(mContext)
                .load(mList.get(i).getData().getCover().getFeed())
                .skipMemoryCache(true)
                .into(viewHolder.jzPlayer.thumbImageView);
        Glide.with(mContext)
                .load(mList.get(i).getData().getAuthor().getIcon())
                .skipMemoryCache(true)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(viewHolder.ivUserIcon);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.jz_player)
        JzvdStd jzPlayer;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_like)
        ImageView ivLike;
        @BindView(R.id.tv_like_num)
        TextView tvLikeNum;
        @BindView(R.id.iv_unlike)
        ImageView ivUnlike;
        @BindView(R.id.tv_unlike_num)
        TextView tvUnlikeNum;
        @BindView(R.id.iv_talk)
        ImageView ivTalk;
        @BindView(R.id.tv_talk_num)
        TextView tvTalkNum;
        @BindView(R.id.iv_share)
        ImageView ivShare;
        @BindView(R.id.tv_share_num)
        TextView tvShareNum;
        @BindView(R.id.iv_user_icon)
        ImageView ivUserIcon;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
