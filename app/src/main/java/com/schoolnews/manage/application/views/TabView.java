package com.schoolnews.manage.application.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.constant.GlobalKeyContans;


public class TabView extends LinearLayout {
    private ImageView iconIv;
    private TextView textTv/*,markNum*/;
    private @GlobalKeyContans.TabIndex int index;
    public TabView(Context context) {
        super(context);
        initView(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.view_tab, this);
        iconIv = (ImageView) findViewById(R.id.iv);
        textTv = (TextView) findViewById(R.id.tv_text);
    }
    /**
     * 设置默认值
     * @param iconResId
     * @param textResId
     * @return
     */
    public TabView setDefaultData(@DrawableRes int iconResId, @StringRes int textResId){
        setIcon(iconResId);
        setText(textResId);
        return this;
    }


    /**
     * 设置图片
     * @param resId
     * @return
     */
    public TabView setIcon(int resId){
        iconIv.setImageResource(resId);
        return this;
    }


    /**
     * 设置下方文字
     * @param resId
     * @return
     */
    public TabView setText(@StringRes int resId){
        textTv.setText(resId);

        return this;
    }

    /**
     * 设置下方文字
     * @param text
     * @return
     */
    public TabView setText(CharSequence text){
        textTv.setText(text);
        textTv.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        return this;
    }

    public void setChecked(boolean isChecked){
//        textCtv.setChecked(isChecked);
        textTv.setSelected(isChecked);
        iconIv.setSelected(isChecked);
    }

    public void setIndex(@GlobalKeyContans.TabIndex int index) {
        this.index = index;
    }

    public @GlobalKeyContans.TabIndex int getIndex() {
        return index;
    }

}
