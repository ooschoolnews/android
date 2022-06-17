package com.schoolnews.manage.application.utils.image;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.schoolnews.manage.application.utils.DipUtils;
import com.schoolnews.manage.application.utils.StringUtils;


/**
 * Description: ImageLoader
 */
public class GlideLoader {

    public static void load(Context context, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if (!StringUtils.isEmpty(url)) {
            Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    public static void load(Context context, int id, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        Glide.with(context).load(id).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    public static void loadRound(Context context, String url, ImageView iv, int holderResouce, int round) {    //，使用占位图,圆角

        if (!StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .transform(new GlideRoundTransform(context, round))
                    .placeholder(holderResouce)
                    .error(holderResouce)
                    .dontAnimate()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        } else {
            iv.setImageResource(holderResouce);
        }

    }

    public static void loadNoCenter(Context context, String url, ImageView iv, int holderResouce) {    //，使用占位图
        if (!StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .placeholder(holderResouce)
                    .error(holderResouce)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        } else {
            iv.setImageResource(holderResouce);
        }

    }

}
