package com.schoolnews.manage.application.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.WindowManager;

import com.schoolnews.manage.application.JlhxApplication;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.utils.dialog.DialogMaker;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.lang.ref.WeakReference;



public class ShareManager {

    private static WeakReference<ShareManager> instance = null;
    private static WeakReference<ShareBoardConfig> config;
    private Activity activity;


    public static ShareManager getInstance() {
        if (instance == null || instance.get() == null) {
            instance = new WeakReference<>(new ShareManager());
            UMShareAPI.get(JlhxApplication.getApplication());
        }
        return instance.get();
    }



    /**
     * 分享 QQ
     *
     * @param activity
     * @param url      链接
     * @param title    标题
     * @param img      图片链接
     * @param des      描述
     */
    public void shareQQAction(Activity activity, String url, String title, String img, String des) {
        UMWeb web = getUMWeb(activity, url, title, img, des);
        if (web == null)
            return;
        this.activity = activity;
        new ShareAction(activity).withMedia(web).
                setPlatform(SHARE_MEDIA.QQ)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 分享新浪
     *
     * @param activity
     * @param url      链接
     * @param title    标题
     * @param img      图片链接
     * @param des      描述
     */
    public void shareSinaAction(Activity activity, String url, String title, String img, String des) {
        UMWeb web = getUMWeb(activity, url, title, img, des);
        if (web == null)
            return;
        this.activity = activity;
        new ShareAction(activity).withMedia(web).
                setPlatform(SHARE_MEDIA.SINA)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 分享微信
     *
     * @param activity
     * @param url      链接
     * @param title    标题
     * @param img      图片链接
     * @param des      描述
     */
    public void shareWeiXinAction(Activity activity, String url, String title, String img, String des) {
        UMWeb web = getUMWeb(activity, url, title, img, des);
        if (web == null)
            return;
        this.activity = activity;
        new ShareAction(activity).withMedia(web).
                setPlatform(SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener)
                .share();
    }

    private UMWeb getUMWeb(Activity activity, String url, String title, String img, String des) {
        UMImage thumb;
        if (StringUtils.isEmpty(url)) {
            ToastUtils.showShortToast("分享链接地址为空");
            return null;
        }
        if (StringUtils.isEmpty(title)) {
            title = "校园新闻";
        }
        if (StringUtils.isEmpty(img)) {
            thumb = new UMImage(activity, R.mipmap.ic_launcher);
        } else {
            thumb = new UMImage(activity, img);
        }
        if (StringUtils.isEmpty(des)) {
            des = "校园新鲜事早知道";
        }

        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(des);//描述

        return web;
    }




    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            if (platform == SHARE_MEDIA.WEIXIN && !UMShareAPI.get(JlhxApplication.getApplication()).isInstall(activity, SHARE_MEDIA.WEIXIN)) {
                ToastUtils.showShortToast("没有安装微信");
            }  else if (platform == SHARE_MEDIA.SINA && !UMShareAPI.get(JlhxApplication.getApplication()).isInstall(activity, SHARE_MEDIA.SINA)) {
                ToastUtils.showShortToast("没有安装新浪微博");
            } else if (platform == SHARE_MEDIA.QQ && !UMShareAPI.get(JlhxApplication.getApplication()).isInstall(activity, SHARE_MEDIA.QQ)) {
                ToastUtils.showShortToast("没有安装QQ");
            } else {
//                EventBus.getDefault().post(new ShareEvent(GlobalKeyContans.EVENT_KEY_SHARE_ONSTART,platform,null));
            }


            DialogMaker.showProgressDialog(activity, "", true, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                }
            }).setCanceledOnTouchOutside(false);
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            DialogMaker.dismissProgressDialog();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            DialogMaker.dismissProgressDialog();
        }
    };

}
