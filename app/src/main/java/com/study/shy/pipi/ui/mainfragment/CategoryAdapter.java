package com.study.shy.pipi.ui.mainfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.study.shy.pipi.R;
import com.study.shy.pipi.bean.CategoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context mContext;
    List<CategoryBean.ItemListBean> mList;


    public CategoryAdapter(Context context, List<CategoryBean.ItemListBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_item_category, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(mList.get(i).getType().equals("rectangleCard")){
            /**设置这个item横跨两列**/
            StaggeredGridLayoutManager.LayoutParams layoutParams =
                    new StaggeredGridLayoutManager.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setFullSpan(true);
            viewHolder.itemView.setLayoutParams(layoutParams);
            /**设置布局**/
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);//两个400分别为添加图片的大小
            viewHolder.rlCategory.setLayoutParams(params);
            /**设置控件**/
            RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    540);//两个400分别为添加图片的大小
            viewHolder.ivBg.setLayoutParams(ivParams);
        }
        Glide.with(mContext)
                .load(mList.get(i).getData().getImage())
                .into(viewHolder.ivBg);
        viewHolder.tvCategory.setText(""+mList.get(i).getData().getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_bg)
        ImageView ivBg;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.rl_category)
        RelativeLayout rlCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
