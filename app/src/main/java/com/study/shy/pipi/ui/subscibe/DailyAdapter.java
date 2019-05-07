package com.study.shy.pipi.ui.subscibe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.study.shy.pipi.ui.view.LikeView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    List<HotBean.IssueListBean.ItemListBean> mList;

    public DailyAdapter(Context context, List<HotBean.IssueListBean.ItemListBean> list) {
        mContext = context;
        mList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().contains("video")) {
                mList.add(list.get(i));
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.rv_itme_daily, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            AutoUtils.autoSize(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.rv_item_footer, viewGroup, false);
            FooterHolder footerHolder = new FooterHolder(view);
            AutoUtils.autoSize(view);
            return footerHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            //加载作者信息 视频点赞量等
            initContext((ViewHolder) viewHolder, i);
            //视频播放器加载
            initPlayer((ViewHolder) viewHolder, i);
        }else if(viewHolder instanceof FooterHolder){
            Glide.with(mContext)
                    .load(R.mipmap.img_load)
                    .into(((FooterHolder) viewHolder).ivLoad);
        }
    }

    private void initContext(ViewHolder viewHolder, int i) {
        /** 点赞/踩（随机生成）/评论**/
        viewHolder.tvTitle.setText(mList.get(i).getData().getTitle());
        viewHolder.tvLikeNum.setText("" + mList.get(i).getData().getConsumption().getCollectionCount());
        viewHolder.tvUnlikeNum.setText("" + (int) (Math.random() * 100));
        viewHolder.tvTalkNum.setText("" + mList.get(i).getData().getConsumption().getReplyCount());
        viewHolder.tvShareNum.setText("" + mList.get(i).getData().getConsumption().getShareCount());
        /**用户头像 名称**/
        viewHolder.tvUserName.setText("" + mList.get(i).getData().getAuthor().getName());
        Glide.with(mContext)
                .load(mList.get(i).getData().getAuthor().getIcon())
                .skipMemoryCache(true)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(viewHolder.ivUserIcon);
        /**是否点过赞 后期加入数据库**/
        /**点赞 踩 动画**/
    }

    private void initPlayer(ViewHolder viewHolder, int i) {
        /**解决黑边问题 设置视频充满容器**/
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
        /**视频缩略图全屏**/
        viewHolder.jzPlayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        /**视频缩略图**/
        Glide.with(mContext)
                .load(mList.get(i).getData().getCover().getFeed())
                .skipMemoryCache(true)
                .into(viewHolder.jzPlayer.thumbImageView);
        /**播放器设置 设置url 标题 **/
        viewHolder.jzPlayer.setUp(
                mList.get(i).getData().getPlayUrl(),
                mList.get(i).getData().getTitle(),
                JzvdStd.SCREEN_WINDOW_NORMAL);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mList.size()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void updateData(HotBean hotBean){
        for (int i=0;i<hotBean.getIssueList().size();i++){
            for (int j=0 ;j<hotBean.getIssueList().get(i).getItemList().size(); j++){
                if (hotBean.getIssueList().get(i).getItemList().get(j).getType().contains("video")) {
                    mList.add(hotBean.getIssueList().get(i).getItemList().get(j));
                }
            }
        }
        Log.e("更新后数据",""+mList.size());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.jz_player)
        JzvdStd jzPlayer;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_like)
        LikeView ivLike;
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

    public class FooterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_load)
        ImageView ivLoad;

        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
