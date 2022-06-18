package com.schoolnews.manage.application.utils;


import android.os.Handler;
import android.os.Looper;

import android.widget.Toast;


//吐司相关工具类

public final class ToastUtils {

    private static Toast sToast;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 显示短时吐司
     */
    public static void showShortToast(CharSequence text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示长时吐司
     */
    public static void showLongToast(CharSequence text) {
        showToast(text, Toast.LENGTH_LONG);
    }

    /**
     * 显示吐司
     *
     * @param text     文本
     * @param duration 显示时长
     */
    private static void showToast(CharSequence text, int duration) {
        if (sToast == null) {
            sToast = Toast.makeText(Utils.getContext(), null, duration);
            sToast.setText(text);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        sToast.show();
    }

}