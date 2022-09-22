package com.example.helloworld.activity.activity_life;

import android.os.Bundle;
import android.util.Log;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;

/**
 * 探究Activity 生命周期
 */
public class TestActivityLifecycle_2 extends BaseActivity {
    private static final String TAG = "TestActivityLifecycle_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lifecycle_layout_2);
        Log.d(TAG, "onCreate: 执行onCreate()方法");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 执行onStart()方法 ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: 执行onResume()方法");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: 执行onPause()方法");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: 执行onStop()方法");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 执行onDestroy()方法");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: 执行onRestart()方法");
    }
}