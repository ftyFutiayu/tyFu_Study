package com.example.helloworld.activity.fragment.demo1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;

/**
 * Demo 1 实现一个Activity四个Fragment切换
 */
public class FragmentActivityDemo1 extends BaseActivity {
    private static final String TAG = "FragmentActivityDemo1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_demo1);
        //初始化页面
        switchFragment(R.id.fragment_demo1_Left, new FragmentDemo1Left());
        switchFragment(R.id.fragment_demo1_Right, new FragmentDemo1Right());
    }

    //切换Fragment
    public void switchFragment(int layoutId, Fragment fragment) {
        //首先获取FragmentManager
        FragmentManager manager = getSupportFragmentManager();
        //通过fragmentManager获取Fragment事务
        FragmentTransaction transaction = manager.beginTransaction();
        //增加最开始的两个Fragment
        transaction.replace(layoutId, fragment);
        transaction.commit();
    }
}