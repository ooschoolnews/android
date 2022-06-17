package com.schoolnews.manage.application.ui.person.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.utils.Preferences;
import butterknife.BindView;


//个人信息

public class PersonInfoActivity extends BaseActivity {

    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.age_et)
    EditText ageEt;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.modify_tv)
    TextView modifyTv;

    public static void start(Context context) {
        Intent intent = new Intent(context, PersonInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "修改个人信息";
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
        nameEt.setText(Preferences.getUsername());
        phoneEt.setText(Preferences.getKeyUserMobile());
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

}
