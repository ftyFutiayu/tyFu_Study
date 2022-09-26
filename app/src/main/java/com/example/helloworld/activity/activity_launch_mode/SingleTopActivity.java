package com.example.helloworld.activity.activity_launch_mode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;

public class SingleTopActivity extends BaseActivity {
    private static final String TAG = "SingleTopActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singletop_layout);
        Log.i(TAG, "Start Activity : SingleTop_Launch_Mode, Task id is  : " + getTaskId());
        Button backToLaunchModeUI = findViewById(R.id.Button_backTo_LaunchMode);
        backToLaunchModeUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleTopActivity.this, LaunchModeActivity.class);
                startActivity(intent);
            }
        });
    }
}
