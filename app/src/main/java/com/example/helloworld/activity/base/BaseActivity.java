package com.example.helloworld.activity.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.controller.ActivityController;

/**
 * @Author: Tianyu.Fu
 * @CreateDate: 2022/8/3
 * @Description: This is BaseActivity
 * 用于管理Activity ， 展示当前位于哪个Activity以及销毁Activity
 */
public class BaseActivity extends AppCompatActivity {
    //重写 onCreate()
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前Activity名称
        Log.i("BaseActivity", getClass().getSimpleName());
        //添加ActivityController方便back
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
