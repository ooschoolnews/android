package com.schoolnews.manage.application.ui.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.http.HttpHelper;
import com.schoolnews.manage.application.http.JsonCallback;
import com.schoolnews.manage.application.http.LzyResponse;
import com.schoolnews.manage.application.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class RegistActivity extends BaseActivity {

    @BindView(R.id.account_tv)
    TextView accountTv;
    @BindView(R.id.input_phone_et)
    EditText inputPhoneEt;
    @BindView(R.id.clear_iv)
    ImageView clearIv;
    @BindView(R.id.vertify_tv)
    TextView vertifyTv;
    @BindView(R.id.vertify_et)
    EditText vertifyEt;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.regist_ll)
    LinearLayout registLl;

    public static void start(Context context) {
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean isShowTitle() {
        return false;
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
    protected void onDestroy() {
        super.onDestroy();
    }


}

