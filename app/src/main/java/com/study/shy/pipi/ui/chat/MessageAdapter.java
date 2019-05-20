package com.study.shy.pipi.ui.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.hyphenate.chat.EMMessage;
import com.study.shy.pipi.R;
import com.study.shy.pipi.ui.login.LoginActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    List<EMMessage> mList;



    public MessageAdapter(Context context, List<EMMessage> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType == 1){
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.rv_item_talk_left, viewGroup, false);
            ReceiveViewHolder receiveViewHolder = new ReceiveViewHolder(view);
            AutoUtils.autoSize(view);
            return receiveViewHolder;
        }else {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.rv_item_talk_right, viewGroup, false);
            SendViewHolder sendViewHolder = new SendViewHolder(view);
            AutoUtils.autoSize(view);
            return sendViewHolder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).getTo().equals(SPUtils.getInstance("USER").getString("user"))) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //Log.e("onBind",""+mList.get(i).toString());
        String msg = mList.get(i).getBody().toString();
        if(viewHolder instanceof ReceiveViewHolder){
            ((ReceiveViewHolder) viewHolder).tvTalkContent.setText(msg.substring(5,msg.length()-1));
        }else if (viewHolder instanceof SendViewHolder){
            ((SendViewHolder) viewHolder).tvTalkContent.setText(msg.substring(5,msg.length()-1));
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void addMsg(EMMessage message){
        //Log.e("调用addMsg",""+message.toString());
        mList.add(message);
        notifyDataSetChanged();
    }

    public class ReceiveViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_talk_content)
        TextView tvTalkContent;

        public ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class SendViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_talk_content)
        TextView tvTalkContent;

        public SendViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
