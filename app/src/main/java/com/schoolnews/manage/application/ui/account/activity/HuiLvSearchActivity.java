package com.schoolnews.manage.application.ui.account.activity;

import android.os.Bundle;
import android.view.View;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.ui.dialog.OnConfirmWithTagListener;

public class HuiLvSearchActivity extends BaseActivity implements OnConfirmWithTagListener {


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return null;
    }

    @Override
    protected View getRightView() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }


    @Override
    public void onButtonClick(View v, String tag) {

    }
}
