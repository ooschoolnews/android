package com.schoolnews.manage.application.ui.person.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.bean.CommonListBean;
import com.schoolnews.manage.application.bean.FeeListBean;
import com.schoolnews.manage.application.http.HttpHelper;
import com.schoolnews.manage.application.http.JsonCallback;
import com.schoolnews.manage.application.http.LzyResponse;
import com.schoolnews.manage.application.ui.home.activity.SchoolNewsDetailActivity;
import com.schoolnews.manage.application.ui.home.adapter.ToIssueBillsListAdapter;
import com.schoolnews.manage.application.utils.DipUtils;
import com.schoolnews.manage.application.utils.ScreenUtils;
import com.schoolnews.manage.application.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2021/4/1 10:35
 */
public class MyCollectionActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.black_list_rv)
    RecyclerView blackListRv;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;

    private PopupWindow mPopupWindow;
    private int downX;
    private int downY;

    private ToIssueBillsListAdapter mBlackListAdapter;
    List<FeeListBean.RecordsBean> recordsBeans = new ArrayList<>();

    public static void start(Context context) {
        Intent intent = new Intent(context, MyCollectionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_list;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "我的收藏";
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
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getApplicationContext(), true));
        refreshLayout.setPullDownRefreshEnable(true);

        mBlackListAdapter = new ToIssueBillsListAdapter(R.layout.news_list_item, 1);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        blackListRv.setLayoutManager(manager);
        blackListRv.setAdapter(mBlackListAdapter);
    }

    @Override
    protected void setListener() {
        mBlackListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SchoolNewsDetailActivity.startAction(mActivity, mBlackListAdapter.getData().get(position));
            }
        });
    }

    /**
     * 根据点击item 设置pop显示位置  显示在item正中间
     *
     * @param anchorView
     */
    private void showPopupWindow(final View anchorView) {
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.pop_cancel_collect, null);
        View.OnClickListener menuItemOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noLike();
            }
        };
        contentView.findViewById(R.id.cancel_collect_btn).setOnClickListener(menuItemOnClickListener);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());


        int[] location = new int[2];
        anchorView.getLocationInWindow(location);
        mPopupWindow.showAsDropDown(anchorView, ScreenUtils.getScreenWidth(mActivity) / 2 - (int) ScreenUtils.dip2px(mActivity, 60),
                -anchorView.getHeight() + ((anchorView.getHeight() - DipUtils.dp2px(mActivity, 40)) / 2));

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                anchorView.setBackgroundResource(R.drawable.message_list_item_bg);
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        getMessageListData();
    }


    private void getMessageListData() {
        showLoadingDialog();
        HttpHelper.getFeeList(TAG, new JsonCallback<LzyResponse<List<CommonListBean>>>() {
            @Override
            public void onSuccess(LzyResponse<List<CommonListBean>> lzyResponse, Call call, Response response) {

                List<CommonListBean> feeListBean = lzyResponse.list;
                mBlackListAdapter.setNewData(feeListBean);
                refreshLayout.endRefreshing();
                dismissLoadingDialog();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                dismissLoadingDialog();
                ToastUtils.showLongToast(e.getMessage());
                refreshLayout.endRefreshing();
                dismissLoadingDialog();
            }
        });
    }

    /**
     * 取消点赞
     */
    private void noLike() {

        HttpHelper.noLike(TAG, 11, new JsonCallback() {
            @Override
            public void onSuccess(Object o, Call call, Response response) {
                ToastUtils.showShortToast("删除成功");
                dismissLoadingDialog();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                dismissLoadingDialog();
                ToastUtils.showLongToast("删除失败");
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getMessageListData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
