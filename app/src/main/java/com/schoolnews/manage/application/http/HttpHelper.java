package com.schoolnews.manage.application.http;

import com.schoolnews.manage.application.constant.AddressContants;
import com.schoolnews.manage.application.utils.Preferences;
import com.lzy.okgo.OkGo;

import java.io.File;
import java.util.List;

public class HttpHelper {

    /**
     * @param tag 取消请求标识
     */
    public static void cancelTag(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }


    /**
     * 活动列表
     *
     * @param tag
     * @param callback
     */
    public static void getFeeList(Object tag, JsonCallback callback) {

        OkGo.get(AddressContants.API_SERVER_FEE_LIST + "?userId=" + Preferences.getUserId())
                .tag(tag)
                .execute(callback);
    }


    public static void getCommonList(Object tag, int type, JsonCallback callback) {
        OkGo.get(AddressContants.API_SERVER_ZHAUNZHANG_LIST + "?type=" + type + "&userId=" + Preferences.getUserId())
                .tag(tag)
                .execute(callback);
    }


    /**
     * 登陆
     *
     * @param tag
     * @param callback
     */
    public static void login(Object tag, String name, String psd, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_LOGIN)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("mobile", name)
                .params("password", psd)
                .execute(callback);
    }

    /**
     * 收藏
     *
     * @param tag
     * @param callback
     */
    public static void collect(Object tag, int id, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_COLLECT)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("id", id)
                .execute(callback);
    }

    /**
     * 取消收藏
     *
     * @param tag
     * @param callback
     */
    public static void cacelCollect(Object tag, int id, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_CANCEL_COLLECT)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("id", id)
                .execute(callback);
    }


    /**
     * 点赞
     *
     * @param tag
     * @param callback
     */
    public static void like(Object tag, int id, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_LIKE)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("id", id)
                .execute(callback);
    }

    /**
     * 取消点赞
     *
     * @param tag
     * @param callback
     */
    public static void noLike(Object tag, int id, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_NO_LIKE)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("id", id)
                .execute(callback);
    }


}
