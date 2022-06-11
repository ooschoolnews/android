package com.schoolnews.manage.application.ui.dialog;

import com.schoolnews.manage.application.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author leo.li
 * @description:
 * @date :2019/7/25 13:59
 */
public class DialogListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public DialogListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public DialogListAdapter(List data) {
        super(data);
    }

    public DialogListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.num_tv, item);
    }
}
