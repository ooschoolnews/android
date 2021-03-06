package com.schoolnews.manage.application.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.schoolnews.manage.application.JlhxApplication;
import com.schoolnews.manage.application.http.HttpHelper;
import com.schoolnews.manage.application.utils.LogUtil;
import com.schoolnews.manage.application.utils.dialog.DialogMaker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected String TAG;
    protected View mContentView;
    protected BaseActivity mActivity;
    protected JlhxApplication mApp;
    protected boolean mIsLoadedData = false;
    protected Unbinder unbinder;
    protected boolean isVisibleToUser = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Object myEvent) {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG = this.getClass().getSimpleName();
        mApp = JlhxApplication.getApplication();
        mActivity = (BaseActivity) getActivity();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isResumed()) {
            handleOnVisibilityChangedToUser(isVisibleToUser);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            handleOnVisibilityChangedToUser(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            handleOnVisibilityChangedToUser(false);
        }
    }

    /**
     * ???????????????????????????
     *
     * @param isVisibleToUser
     */
    private void handleOnVisibilityChangedToUser(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            // ???????????????
            if (!mIsLoadedData) {
                mIsLoadedData = true;
                onLazyLoadOnce();
            }
            onVisibleToUser();
        } else {
            // ??????????????????
            onInvisibleToUser();
        }
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    protected void onLazyLoadOnce() {
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    protected void onVisibleToUser() {
        isVisibleToUser = true;
    }

    /**
     * ????????????????????????????????????
     */
    protected void onInvisibleToUser() {
        isVisibleToUser = false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ???????????????xml?????????????????????
        if (mContentView == null) {
            mContentView = inflater.inflate(getLayoutId(), container, false);
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        unbinder = ButterKnife.bind(this, mContentView);
        initView(savedInstanceState);
        setListener();
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        processLogic(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        HttpHelper.cancelTag(TAG);
    }

    public void showLoadingDialog() {
        LogUtil.i(TAG, "showLoadingDialog");
        DialogMaker.showProgressDialog(mActivity, null);
    }

    public void dismissLoadingDialog() {
        DialogMaker.dismissProgressDialog();
    }

    /**
     * ??????layoutId
     */
    protected abstract int getLayoutId();

    /**
     * ???????????????
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * ?????????View??????
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * ???View???????????????????????????
     */
    protected abstract void setListener();

    /**
     * ??????????????????????????????????????????
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

}
