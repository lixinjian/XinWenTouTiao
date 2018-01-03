package com.xinjian.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xinjian.R;
import com.xinjian.golbal.MyApplication;


public class ToastUtils {
    private static ToastUtils instance;

    private Toast mToast;
    private MyHandler handler;

    private static final int SHOW = 1;

    private ToastUtils(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        mToast = new Toast(context);
        mToast.setGravity(Gravity.CENTER_HORIZONTAL /*| Gravity.TOP*/, 0, DensityUtils.dp2px(context, 240));
        mToast.setView(view);
    }

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case SHOW:
                    removeCallbacks(null);
                    int temp = msg.arg1;
                    if (temp > 0) {
                        instance.mToast.setDuration(Toast.LENGTH_SHORT);
                        instance.mToast.show();
                        temp -= 1000;
                        Message message = Message.obtain();
                        message.what = SHOW;
                        message.arg1 = temp;
                        instance.handler.sendMessageDelayed(message, 1000);
                    }
                    break;
            }
        }
    }

    public static void showToast(String msg, int dur) {
        msg = String.valueOf(msg);
        initToast(MyApplication.getContext());
        //获取TextView
        TextView content = instance.mToast.getView().findViewById(R.id.dj_toast_text);
        content.setText(msg);

        switch (dur) {
            case Toast.LENGTH_SHORT:
                instance.mToast.setDuration(Toast.LENGTH_SHORT);
                instance.mToast.show();
                break;
            case Toast.LENGTH_LONG:
                instance.mToast.setDuration(Toast.LENGTH_LONG);
                instance.mToast.show();
                break;
            default:
                showCustomDurToast(dur);
                break;
        }
    }

    private static void initToast(Context context) {
        if (instance == null) {
            synchronized (ToastUtils.class) {
                if (instance == null) {
                    instance = new ToastUtils(context);
                }
            }
        }
    }

    private static void showCustomDurToast(int dur) {
        if (instance.handler == null) instance.handler = new MyHandler();

        Message msg = Message.obtain();
        msg.what = SHOW;
        msg.arg1 = dur;
        instance.handler.sendMessage(msg);
    }

    public static void showToastShort(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    public static void showToastLong(String msg) {
        showToast(msg, Toast.LENGTH_LONG);
    }
}