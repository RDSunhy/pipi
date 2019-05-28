package com.study.shy.pipi.util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.king.zxing.CaptureActivity;
import com.study.shy.pipi.R;

public class ScanActivity extends CaptureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    public int getSurfaceViewId() {
        return R.id.sfv_bg;
    }

    @Override
    public int getViewfinderViewId() {
        return R.id.vfv_scan;
    }
}
