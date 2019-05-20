package com.study.shy.pipi.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.study.shy.pipi.R;
import com.study.shy.pipi.util.LoadingDialog;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.compiler.ButterKnifeProcessor;

public abstract class BaseActivity extends AutoLayoutActivity {

    /***网络状态*/
    public static final int TYPE_NO = -1;
    public static final int TYPE_MOBILE = 0;
    public static final int TYPE_WIFI = 1;
    /***是否显示标题栏*/
    private  boolean isshowtitle = true;
    /***是否显示状态*/
    private  boolean isshowstate = false;
    /***网络状态code**/
    private int netWorkStatus;
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();
    private IntentFilter intentFilter;
    private NetworkStateReceiver networkStateReceiver;
    /**加载动画**/
    LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EventBus.getDefault().register(this);
        ActivityCollector.addActivity(this);
        intentFilter = new IntentFilter();
        loadingDialog = new LoadingDialog(this,R.style.ConfigDialog);
        networkStateReceiver = new NetworkStateReceiver();
        if(!isshowtitle){
            //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if(isshowstate){
            getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                    WindowManager.LayoutParams. FLAG_FULLSCREEN);
        }
        //获取网络状态
        getNetWorkStatus();
        //设置布局
        setContentView(intiLayout());
        ButterKnife.bind(this);
        //初始化控件
        initView();
        //设置数据
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        /*if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkStateReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkStateReceiver,intentFilter);
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int intiLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

    /**
     * 加载动画
     */
    public void showLoading() {
        loadingDialog.show();
    }
    /**
     * 关闭动画
     */
    public void dismissLoading() {
        loadingDialog.dismiss();
    }
    /**
     * 是否设置标题栏
     *
     * @return
     */
    public void setTitle(boolean ishow) {
        isshowtitle=ishow;
    }

    /**
     * 设置是否显示状态栏
     * @param ishow
     */
    public void setState(boolean ishow) {
        isshowstate=ishow;
    }
    /**
     * 设置网络状态
     *
     */
    public void setNetWorkStatus(int code){
        netWorkStatus = code;
    }

    /**
     * 获取网络状态
     */
    public void getNetWorkStatus(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isAvailable()){
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                //wifi
                setNetWorkStatus(TYPE_WIFI);
            }else {
                setNetWorkStatus(TYPE_MOBILE);
            }
        }else {
            setNetWorkStatus(TYPE_NO);
        }
    }

    class NetworkStateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            Activity activity = (Activity)context;
            /*if(context instanceof LoginActivity){
                return;
            }*/
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable()){
                if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                    //流量
                    setNetWorkStatus(TYPE_MOBILE);
                }else if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                    //wifi
                    setNetWorkStatus(TYPE_WIFI);
                }
            }else {
                setNetWorkStatus(TYPE_NO);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("网络中断");
                builder.setMessage("检测到您的网络断开连接,请检查网络是否连接正常！");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*ActivityCollector.finishAll();
                        Intent intent = new Intent(context,SplashActivity.class);
                        context.startActivity(intent);*/
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                //放在show()之后，不然有些属性是没有效果的，比如height和width
                Window dialogWindow = dialog.getWindow();
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                // 设置高度和宽度
                p.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.65
                p.gravity = Gravity.CENTER;//设置位置
                //p.alpha = 0.8f;//设置透明度
                dialogWindow.setAttributes(p);
            }
        }
    }

}

