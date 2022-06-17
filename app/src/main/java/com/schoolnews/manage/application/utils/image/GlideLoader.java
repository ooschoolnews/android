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
