package com.example.helloworld.activity.fragment;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;

/**
 * 测试Fragment生命周期
 * 同时监测Activity与Fragment生命周期如何交替
 */
public class FragmentTestLifecycle extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "FragmentTestLifecycle";
    //判断当前显示是否为原来的右侧Fragment
    boolean isRightFragment;
    //原本右侧的Fragment
    private final TestLifecycleRightFragment rightFragment = new TestLifecycleRightFragment();
    //另一个右测的Fragment
    private final TestLifecycleAnotherRightFragment anotherRightFragment = new TestLifecycleAnotherRightFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test_lifecycle);
        Log.i(TAG, "onCreate: " + "--Activity --" + "test lifecycle");
        Button replaceButton = findViewById(R.id.fragment_test_lifecycle_left_btn);
        replaceButton.setOnClickListener(this);
        //首先添加原来的右侧Fragment
        replaceRightFragment(rightFragment);
        isRightFragment = true;
    }

    /**
     * 实现View.OnClickListener点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_test_lifecycle_left_btn:
                if (isRightFragment) {
                    replaceRightFragment(anotherRightFragment);
                } else {
                    replaceRightFragment(rightFragment);
                }
                isRightFragment = !isRightFragment;
                break;
            default:
                break;
        }
    }

    /**
     * 更换右侧Fragment
     */
    private void replaceRightFragment(Fragment fragment) {
        //通过getSupportFragmentManager获得FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启一个事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //replace方法 参数为需要传入的容器id和待添加的Fragment实例
        transaction.replace(R.id.fragment_test_lifecycle_right, fragment);
//        transaction.add(R.id.fragment_test_lifecycle_right, fragment);
        transaction.commit(); //提交事务
    }

     //###############生命周期####################
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: " + "--Activity --" + "test lifecycle");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: " + "--Activity --" + "test lifecycle");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: " + "--Activity --" + "test lifecycle");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: " + "--Activity --" + "test lifecycle");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: " + "--Activity --" + "test lifecycle");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: " + "--Activity --" + "test lifecycle");
    }
}