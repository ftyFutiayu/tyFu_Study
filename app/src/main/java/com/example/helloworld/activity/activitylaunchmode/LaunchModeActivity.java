package com.example.helloworld.activity.activitylaunchmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;
import com.example.helloworld.controller.ActivityController;

/**
 * 探究Activity四种启动模式使用
 */
public class LaunchModeActivity extends BaseActivity {
    private static final String TAG = "LaunchModeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Start Activity : Standard_Launch_Mode, Task id is  : " + getTaskId());
        setContentView(R.layout.launch_mode_layout);
        //Button对象
        Button buttonStandard = findViewById(R.id.Button_standard);
        Button buttonSingleTop = findViewById(R.id.Button_singleTop);
        Button buttonSingleTask = findViewById(R.id.Button_singleTask);
        Button buttonSingleInstance = findViewById(R.id.Button_singleInstance);
        //监听器
        buttonStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaunchModeActivity.this, LaunchModeActivity.class);
                startActivity(intent);
            }
        });

        buttonSingleTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaunchModeActivity.this, SingleTopActivity.class);
                startActivity(intent);
            }
        });

        buttonSingleTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaunchModeActivity.this, SingleTaskActivity.class);
                startActivity(intent);
            }
        });

        buttonSingleInstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaunchModeActivity.this, SingleInstanceActivity.class);
                startActivity(intent);
            }
        });

        //退出程序
        Button buttonExit = (Button) findViewById(R.id.Button_exit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityController.removeAllActivities();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
