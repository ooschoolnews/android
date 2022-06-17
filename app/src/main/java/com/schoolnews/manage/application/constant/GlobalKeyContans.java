package com.schoolnews.manage.application.constant;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 全局量
 */
public class GlobalKeyContans {


    public static final String MESSAGE_FINISH_ACTION = "com.crm.kd.kdapplication.MESSAGE_FINISH_ACTION";//关闭
    //首页底部tab的index
    public static final int MAIN_HOMEPAGE_INDEX = 0;
    public static final int MAIN_SINGLE_INDEX = 1;
    public static final int MAIN_CONSULT = 2;
    public static final int MAIN_FORUM = 3;
    public static final int MAIN_MY_INDEX = 4;

    @IntDef({MAIN_HOMEPAGE_INDEX, MAIN_SINGLE_INDEX, MAIN_CONSULT, MAIN_FORUM, MAIN_MY_INDEX})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndex {
    }

    //用户信息修改类型
    public static final String TYPE_PERSONAL_DATA_PHONE = "phone";
    public static final String TYPE_PERSONAL_DATA_MOBILEPHONE = "mobilePhone";
    public static final String TYPE_PERSONAL_DATA_EMAIL = "email";
    public static final String TYPE_PERSONAL_DATA_QQ = "qq";
    public static final String TYPE_PERSONAL_DATA_WECHAT = "wechat";



    public static final int CODE_SUCCESS = 0;      //接口请求成功"),
    public static final int CODE_FAIL = 1;      //接口请求失败"),

}
