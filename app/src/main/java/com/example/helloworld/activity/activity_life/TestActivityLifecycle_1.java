package com.example.helloworld.activity.activity_life;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;
import com.example.helloworld.controller.ActivityController;

/**
 * 探究Activity 生命周期
 */
public class TestActivityLifecycle_1 extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TestActivityLifecycle_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lifecycle_layout_1);
        Log.d(TAG, "onCreate: 执行onCreate()方法");
        Button buttonToTestActivityLifecycle = findViewById(R.id.btn_textactivity1_to_test_activity_lifecycle_2);
        buttonToTestActivityLifecycle.setOnClickListener(this);
        Button buttonAlertdialog = findViewById(R.id.btn_textactivity1_normal_alertdialog);
        buttonAlertdialog.setOnClickListener(this);
        Button buttonToast = findViewById(R.id.btn_textactivity1_toast);
        buttonToast.setOnClickListener(this);
        Button button_exit = findViewById(R.id.btn_exit_app);
        button_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_textactivity1_to_test_activity_lifecycle_2:
                intent = new Intent(TestActivityLifecycle_1.this, TestActivityLifecycle2.class);
                startActivity(intent);
              break;
            case R.id.btn_textactivity1_normal_alertdialog:
                AlertDialog.Builder dialog = new AlertDialog.Builder(TestActivityLifecycle_1.this);
                dialog.setTitle("This is a alertDialog");
                dialog.setMessage("Something is important");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
                break;
            case R.id.btn_textactivity1_toast:
                Toast.makeText(TestActivityLifecycle_1.this,
                        "This is a toast message",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_exit_app:
                ActivityController.removeAllActivities();
            default:
                break;
        }

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