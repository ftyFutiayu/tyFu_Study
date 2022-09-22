package com.example.helloworld.activity.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;
import com.example.helloworld.activity.fragment.demo1.FragmentActivityDemo1;
import com.example.helloworld.activity.fragment.demo2.FragmentActivityDemo2;

/**
 * Fragment学习
 * 1. 和Activity的区别
 * 2. 生命周期
 * 3. 切换Fragment的两种方式
 * 3. demo1 一个Activity四个Fragment相互切换
 * 4. demo2 与Tag Layout和View page联动
 */
public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_layout);
        Button testLifeCycle = findViewById(R.id.fragment_test_lifecycle);
        testLifeCycle.setOnClickListener(this);
        Button fragmentDemo1Btn = findViewById(R.id.fragment_test_demo1);
        fragmentDemo1Btn.setOnClickListener(this);
        Button fragmentDemo2Btn = findViewById(R.id.fragment_test_demo2);
        fragmentDemo2Btn.setOnClickListener(this);
    }

    /**
     * 实现View.OnClickListener点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.fragment_test_lifecycle:
                //跳转页面
                intent = new Intent(FragmentActivity.this, FragmentTestLifecycle.class);
                break;
            case R.id.fragment_test_demo1:
                //跳转页面
                intent = new Intent(FragmentActivity.this, FragmentActivityDemo1.class);
                break;
            case R.id.fragment_test_demo2:
                //跳转页面
                intent = new Intent(FragmentActivity.this, FragmentActivityDemo2.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

}