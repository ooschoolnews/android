package com.schoolnews.manage.application.bean;

import java.io.Serializable;
import java.util.List;

public class FeeListBean implements Serializable {


    private int size;
    private int current;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

}
