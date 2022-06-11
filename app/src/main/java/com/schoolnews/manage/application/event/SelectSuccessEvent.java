package com.schoolnews.manage.application.event;

/**

 */
public class SelectSuccessEvent {

    private String mNum;

    public String getmNum() {
        return mNum;
    }

    public void setmNum(String mNum) {
        this.mNum = mNum;
    }

    public SelectSuccessEvent(String num) {
        this.mNum = num;
    }
}

