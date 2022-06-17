package com.schoolnews.manage.application.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseFragment;
import com.schoolnews.manage.application.bean.CommonListBean;
import com.schoolnews.manage.application.http.HttpHelper;
import com.schoolnews.manage.application.http.JsonCallback;
import com.schoolnews.manage.application.http.LzyResponse;
import com.schoolnews.manage.application.ui.home.activity.SchoolNewsDetailActivity;
import com.schoolnews.manage.application.ui.home.adapter.SchoolNewsAdapter;
import com.schoolnews.manage.application.utils.ToastUtils;
import com.schoolnews.manage.application.utils.dialog.DialogMaker;


import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.Call;
import okhttp3.Response;


public class CLassListFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private static final String KEY_TABID = "tabid";
    @BindView(R.id.black_list_rv)
    RecyclerView blackListRv;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;


    private int mTabId;
    private SchoolNewsAdapter mOrderListAdapter;

    public static CLassListFragment newInstance(int tabId) {
        CLassListFragment fragment = new CLassListFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_TABID, tabId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTabId = getArguments().getInt(KEY_TABID);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getContext(), true));
        refreshLayout.setPullDownRefreshEnable(true);

        mOrderListAdapter = new SchoolNewsAdapter(R.layout.news_list_item);
        blackListRv.setLayoutManager(new LinearLayoutManager(mActivity));
        blackListRv.setAdapter(mOrderListAdapter);

        mOrderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SchoolNewsDetailActivity.startAction(mActivity, mOrderListAdapter.getData().get(position));
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        getListData(mTabId);
    }

    private void getListData(int tabId) {
        showLoadingDialog();
        HttpHelper.getCommonList(TAG, tabId, new JsonCallback<LzyResponse<List<CommonListBean>>>() {
            @Override
            public void onSuccess(LzyResponse<List<CommonListBean>> listLzyResponse, Call call, Response response) {

                mOrderListAdapter.setNewData(listLzyResponse.list);
                DialogMaker.dismissProgressDialog();
                refreshLayout.endRefreshing();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                ToastUtils.showShortToast("加载失败");
                dismissLoadingDialog();
                refreshLayout.endRefreshing();
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getListData(mTabId);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
