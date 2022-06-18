package com.schoolnews.manage.application.manager;

import android.app.Activity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 Description:管理所有的栈中的Activity
 */
public class ActivityCollector {
    /**
     * 存放activity的列表
     */
    public static HashMap<Class<?>, Activity> activities = new LinkedHashMap<>();

    /**
     * 添加Activity * * @param activity
     */
    public static void addActivity(Activity activity, Class<?> clz) {
        activities.put(clz, activity);
    }

    /**
     * 移除activity,代替finish * * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activities.containsValue(activity)) {
            activities.remove(activity.getClass());
        }
    }

    /**
     * 移除所有的Activity
     */
    public static void removeAllActivity() {
        if (activities != null && activities.size() > 0) {
            Set<Entry<Class<?>, Activity>> sets = activities.entrySet();
            for (Entry<Class<?>, Activity> s : sets) {
                if (!s.getValue().isFinishing()) {
                    s.getValue().finish();
                }
            }
        }
        activities.clear();
    }

}



