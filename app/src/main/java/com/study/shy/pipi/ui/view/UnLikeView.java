package com.study.shy.pipi.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.study.shy.pipi.R;

public class UnLikeView extends android.support.v7.widget.AppCompatImageView {

    private boolean isSelect = false;
    private Context mContext;
    private ObjectAnimator objectAnimator;

    public UnLikeView(Context context) {
        super(context);
        mContext = context;
    }

    public UnLikeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public UnLikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setSelect(boolean select) {
        isSelect = select;
        if (isSelect){
            setBackgroundResource(R.mipmap.img_unlike_h);
        }else{
            setBackgroundResource(R.mipmap.img_unlike);
        }
    }

    @Override
    public void setAnimation(Animation animation) {
        super.setAnimation(animation);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * ACTION_DOWN	        第一个 手指 初次接触到屏幕 时触发。
         * ACTION_MOVE	        手指 在屏幕上滑动 时触发，会多次触发。
         * ACTION_UP	        最后一个 手指 离开屏幕 时触发。
         * ACTION_POINTER_DOWN	有非主要的手指按下(即按下之前已经有手指在屏幕上)。
         * ACTION_POINTER_UP	有非主要的手指抬起(即抬起之后仍然有手指在屏幕上)。
         * */
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (isSelect){
                    setBackgroundResource(R.mipmap.img_unlike);
                }else{
                    setBackgroundResource(R.mipmap.img_unlike_h);
                    Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.iv_like_large);
                    this.startAnimation(animation);
                }
                isSelect = !isSelect;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

}
