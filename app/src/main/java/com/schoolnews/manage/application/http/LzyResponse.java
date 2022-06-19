package com.schoolnews.manage.application.http;

import com.schoolnews.manage.application.bean.CommonListBean;

import java.io.Serializable;
import java.util.List;

/**
返回有数据类型
 */


public class LzyResponse<T> implements Serializable {


    public int code;    //0表示成功，1失败
    public String msg;       //日志消息
    public T data;          //数据对象结构
    public T entity;          //数据对象结构
    public List<CommonListBean> list;

}