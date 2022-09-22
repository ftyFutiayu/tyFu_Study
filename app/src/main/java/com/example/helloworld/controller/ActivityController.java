package com.example.helloworld.controller;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Tianyu.Fu
 * @CreateDate: 2022/8/3
 * @Description: This is ActivityController
 * 管理所有activity 比如退出程序等
 */
public class ActivityController {
    //Activity集合
    public static List<Activity> activities = new ArrayList<>();

    /**
     * 向集合增加Activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 从集合中移除Activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 移除集合所有的activity,一键退出程序
     */
    public static void removeAllActivities() {
        for (Activity activity : activities) {
            //如果集合中activity没有关闭
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}
