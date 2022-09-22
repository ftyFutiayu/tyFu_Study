package com.example.helloworld.activity.activity_launch_mode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;

public class SingleTaskActivity extends BaseActivity {
    private static final String TAG = "SingleTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Start Activity : SingleTask_Launch_Mode, Task id is  : " + getTaskId());
        setContentView(R.layout.singletask_layout);
        Button backToLaunchModeUI = findViewById(R.id.Button_backTo_LaunchMode);
        backToLaunchModeUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleTaskActivity.this, LaunchModeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }
}