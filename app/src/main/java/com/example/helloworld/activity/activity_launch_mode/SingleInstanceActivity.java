package com.example.helloworld.activity.activity_launch_mode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;

public class SingleInstanceActivity extends BaseActivity {
    private static final String TAG = "SingleInstanceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Start Activity : SingleInstance_Launch_Mode, Task id is  : " + getTaskId());
        setContentView(R.layout.singleinstance_layout);
        Button backToLaunchModeUI = findViewById(R.id.Button_backTo_LaunchMode);
        backToLaunchModeUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleInstanceActivity.this, LaunchModeActivity.class);
                startActivity(intent);
            }
        });
    }
}
