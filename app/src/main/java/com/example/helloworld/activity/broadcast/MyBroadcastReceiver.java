package com.example.helloworld.activity.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @Author: tyFu
 * @CreateDate: 2022/9/27
 * @Description: This is MyBroadcastReceiver自定义广播
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_LONG).show();
    }
}
