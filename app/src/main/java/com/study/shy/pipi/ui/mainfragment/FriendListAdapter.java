package com.study.shy.pipi.ui.mainfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.exceptions.HyphenateException;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.Constants;
import com.study.shy.pipi.ui.chat.ChatActivity;
import com.study.shy.pipi.ui.dialog.RemarkDialog;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {

    Context mContext;
    List<String> mList;
    FragmentManager manager;
    String key;

    public FriendListAdapter(Context context, List<String> list, FragmentManager manager) {
        mContext = context;
        mList = list;
        this.manager = manager;
        key = SPUtils.getInstance(Constants.FILENAME).getString(Constants.USER_ACCOUNT);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_itme_friend_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        AutoUtils.autoSize(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvUserName.setText(""+mList.get(i));
        //是否有备注
        String remark = SPUtils.getInstance(Constants.FILENAME).getString(key+mList.get(i),null);
        if(remark!=null){
            viewHolder.tvUserRemark.setText("("+remark+")");
        }
        //获取最近聊天记录
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(mList.get(i));
        String msg;
        try{
            msg = conversation.getLastMessage().getBody().toString();
            viewHolder.tvUserMsg.setText(msg.substring(5,msg.length()-1));
        }catch (Exception e){
            msg = "";
            Log.e("最近聊天记录为空",""+e.getMessage());
        }
        viewHolder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ChatActivity.class);
                intent.putExtra("username",mList.get(i));
                mContext.startActivity(intent);
            }
        });
        viewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EMClient.getInstance().contactManager().deleteContact(mList.get(i));
                    ToastUtils.showShort("删除好友成功！");
                    mList.remove(i);
                    notifyDataSetChanged();
                } catch (HyphenateException e) {
                    ToastUtils.showShort("删除好友失败！");
                    Log.e("删除好友失败",e.getErrorCode()+""+e.getDescription());
                }
            }
        });
        viewHolder.tvRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemarkDialog dialog = new RemarkDialog();
                Bundle bundle = new Bundle();
                bundle.putString("remark",mList.get(i));
                dialog.setArguments(bundle);
                dialog.show(manager,"MessageFragment");
            }
        });
        //set show mode.
        viewHolder.slItem.setShowMode(SwipeLayout.ShowMode.PullOut);
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    /*@Override
    public int getSwipeLayoutResourceId(int position) {
        return position;
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_user_icon)
        public ImageView ivUserIcon;
        @BindView(R.id.tv_user_name)
        public TextView tvUserName;
        @BindView(R.id.tv_user_remark)
        public TextView tvUserRemark;
        @BindView(R.id.tv_user_msg)
        public TextView tvUserMsg;
        @BindView(R.id.tv_delete)
        public TextView tvDelete;
        @BindView(R.id.tv_remark)
        public TextView tvRemark;
        @BindView(R.id.ll_delete)
        public LinearLayout llDelete;
        @BindView(R.id.ll_content)
        public LinearLayout llContent;
        @BindView(R.id.sl_item)
        public SwipeLayout slItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
