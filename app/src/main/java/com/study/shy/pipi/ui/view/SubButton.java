package com.study.shy.pipi.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.study.shy.pipi.R;

@SuppressLint("AppCompatCustomView")
public class SubButton extends Button {

    public boolean isSubscibe = false;

    public SubButton(Context context) {
        super(context);
    }

    public SubButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SubButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSelect(boolean select) {
        isSubscibe = select;
        if (isSubscibe){
            setText("已订阅");
        }else{
            setText("订阅");
        }
    }
    public boolean getSelect() {
        return isSubscibe;
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
                if (isSubscibe){
                    setText("订阅");
                    ToastUtils.showShort("已取消订阅此内容！");
                }else{
                    setText("已订阅");
                    ToastUtils.showShort("订阅成功！");
                }
                isSubscibe = !isSubscibe;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
