package com.study.shy.pipi.bean.event;

import android.graphics.Bitmap;

public class SetIconBean {

    Bitmap bitmap;

    public SetIconBean(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
