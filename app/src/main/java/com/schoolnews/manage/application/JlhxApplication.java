package com.schoolnews.manage.application;


import android.support.multidex.MultiDexApplication;
import com.schoolnews.manage.application.utils.Utils;
import com.lzy.okgo.OkGo;

import java.util.logging.Level;

public class JlhxApplication extends MultiDexApplication {
    private static JlhxApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        //初始化utils
        Utils.init(this);
        //网络请求框架
        OkGo.init(this);
        OkGo.getInstance().setConnectTimeout(15 * 1000);   //超时事件15秒
        OkGo.getInstance().setRetryCount(1);   //重复请求1次

        if (BuildConfig.DEBUG) {
            OkGo.getInstance().debug("OkGo", Level.INFO, true);   //日志打印
        }
    }

    public static JlhxApplication getApplication() {
        return mApplication;
    }
}