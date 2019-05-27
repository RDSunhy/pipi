package com.study.shy.pipi.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blankj.utilcode.util.ToastUtils;
import com.study.shy.pipi.R;
import com.study.shy.pipi.bean.event.SetIconBean;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class MenuDialog extends DialogFragment implements View.OnClickListener {

    public int RESULT_LOAD_IMAGE = 0x1;
    public int RESULT_CROP_IMAGE = 0x2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置style
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MenuDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置 dialog 的宽高
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置 dialog 的背景为 null
        getDialog().getWindow().setBackgroundDrawable(null);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; //底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);

        return createView(inflater, container);
    }

    //重写此方法,设置布局文件
    protected View createView(LayoutInflater inflater, ViewGroup container){
        View v = inflater.inflate(R.layout.dialog_menu,container);
        TextView tvOp1 = v.findViewById(R.id.tv_op1);
        TextView tvOp2 = v.findViewById(R.id.tv_op2);
        TextView tvOp3 = v.findViewById(R.id.tv_op3);
        tvOp1.setOnClickListener(this);
        tvOp2.setOnClickListener(this);
        tvOp3.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_op1:
                break;
            case R.id.tv_op2:
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            break;
            case R.id.tv_op3:
                dismiss();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && data != null && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            ToastUtils.showShort(""+selectedImage.toString());
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor =getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            ToastUtils.showShort(""+picturePath);
            cursor.close();

            cropImage(Uri.parse(picturePath));
            //Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
        }else if(requestCode == RESULT_CROP_IMAGE && data != null && resultCode == RESULT_OK){
            ToastUtils.showShort("裁剪完毕");
            Bundle bundle = data.getExtras();

            if (bundle != null) {
                //在这里获得了剪裁后的Bitmap对象，可以用于上传
                Bitmap bitmap = bundle.getParcelable("data");
                //通过EventBus 通知fragment更新头像
                EventBus.getDefault().post(new SetIconBean(bitmap));
            }

        }
    }

    public void cropImage(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        //uri = Uri.parse(toFile(getContext(),uri));
        //Log.e("传入的url",""+uri);
        intent.setDataAndType(uri, "image/*");
        //下面这个crop = true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra( "crop", "true");
        // aspectX aspectY是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY",1);
        // outputX outputY是裁剪图片宽高
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,RESULT_CROP_IMAGE);
    }
}
