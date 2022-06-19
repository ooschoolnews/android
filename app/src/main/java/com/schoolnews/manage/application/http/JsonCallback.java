package com.schoolnews.manage.application.http;


import com.schoolnews.manage.application.utils.Preferences;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.schoolnews.manage.application.constant.GlobalKeyContans;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.BaseRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 解析回掉
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        if (Preferences.isLogin()) {
            request.headers("Authorization", Preferences.getUserToken());
        }
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     */

    @Override
    public T convertSuccess(Response response) throws Exception {

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments(); //第一次泛型Lzyresponse
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");
        Type rawType = ((ParameterizedType) type).getRawType();
//        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];//第二次泛型data类型
//        String json = response.body().string();
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType == LzyResponse.class) {
            LzyResponse lzyResponse = new Gson().fromJson(jsonReader, type); //从fastjson换成Gson解析，
            response.close();
            if (lzyResponse == null) {
                throw new IllegalStateException("基类错误无法解析!");
            }
            switch (lzyResponse.code) {
                case GlobalKeyContans.CODE_SUCCESS:
                    return (T) lzyResponse;
                case 200:
                    return (T) lzyResponse;
                default:
                    throw new IllegalStateException(lzyResponse.msg);
            }
        } else {
            response.close();
            throw new IllegalStateException("基类错误无法解析!");
        }
    }
}