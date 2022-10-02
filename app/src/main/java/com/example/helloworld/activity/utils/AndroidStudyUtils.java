package com.example.helloworld.activity.utils;

import android.view.View;

/**
 * @Author: tianyu.Fu
 * @CreateDate: 2022/8/29
 * @Description: This is AndroidStudyUtils 计算相关工具类
 */
public class AndroidStudyUtils {

    /**
     * 判断触摸点(x,y)是否在view的区域外
     *
     * @param view
     * @param x
     * @param y
     * @return ： true-在view外面 false-不在view外面
     */
    private static boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        //view.isClickable() &&
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return false;
        }
        return true;
    }
}
