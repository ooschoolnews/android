package com.schoolnews.manage.application.views.wheelview.listener;


import com.schoolnews.manage.application.bean.AddressBean;

public interface OnAddressChangeListener {
	void onAddressChange(AddressBean province, AddressBean.ChildrenBeanX city, AddressBean.ChildrenBeanX.ChildrenBean district);
}
