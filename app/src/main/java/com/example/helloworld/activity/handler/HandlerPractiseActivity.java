package com.example.helloworld.activity.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;

public class HandlerPractiseActivity extends BaseActivity {
    private static final String TAG = "HandlerPractiseActivity";

    public static final int COUNT_TIME = 2000;
    public static final int ONE_SECOND = 1000;
    public static final int HANDLER_WHAT = 8888;

    private TextView countTimeText; //倒计时文本
    private Button startCountTimeBtn; //倒计时按钮
    Handler mHandler = new Handler(msg -> {
        if (msg.what == 8888) {
            countTime();
        }
        return false;
    });

    private void countTime() {
        Message msg = mHandler.obtainMessage(HANDLER_WHAT); //获取消息
        Log.i(TAG, "countTime:  msg : " + msg.arg1);
        //msg可以存储三种形式数据   msg.arg1和msg.arg2存储int类型数据
        //obj存储任意类型数据
        int value = msg.arg1;
        //动态显示倒计时
        countTimeText.setText(value);
        Message msg2 = mHandler.obtainMessage(HANDLER_WHAT); //重新获取消息
        msg2.arg1 = value - 1;
        msg2.what = HANDLER_WHAT;
        if (value > 0) {
            mHandler.sendMessageDelayed(msg2, ONE_SECOND);
        }
        Log.i(TAG, "countTime:  msg2 : " + msg2.arg1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_practise_activity);
        //初始化textview和button
        countTimeText = findViewById(R.id.handler_count_time_text);
        startCountTimeBtn = findViewById(R.id.handler_start_count_time);
        //点击触发倒计时
//        startCountTimeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Message msg = mHandler.obtainMessage(8888);
//            }
//        });
    }
}
