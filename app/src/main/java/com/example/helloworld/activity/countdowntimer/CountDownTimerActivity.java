package com.example.helloworld.activity.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.R;

/**
 * 使用 CountDownTimer实现倒计时
 */
public class CountDownTimerActivity extends AppCompatActivity {
    private Button timerBtn;
    private Button timeCancel;
    private CountDownTimer countDownTimer;
    private TextView time;
    private static final long COUNTTIMEUNIT = 1000L;
    private static final long TOTALTIME = 5000L;

    /**
     * 初始化countDownTimer
     *
     * @param totalTime : 倒计时总时间
     * @param interval  : 倒计时间隔
     */
    public void initCountDownTimer(long totalTime, long interval) {

        countDownTimer = new CountDownTimer(totalTime, interval) {
            //当前任务每完成一次倒计时间隔时间时回调。
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                //倒计时显示在TextView中
                time.setText(millisUntilFinished / COUNTTIMEUNIT + "s");
                //计时时取消按钮可用
                timeCancel.setEnabled(true);
            }

            //倒计时结束时回调
            @Override
            public void onFinish() {
                Toast.makeText(CountDownTimerActivity.this, "倒计时结束", Toast.LENGTH_SHORT).show();
                timerBtn.setText("重新开始计时");
                timeCancel.setEnabled(false);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_down_timer_activity);
        time = findViewById(R.id.count_down_time_time);
        timerBtn = findViewById(R.id.count_down_time_start);
        timeCancel = findViewById(R.id.count_down_time_cancel);
        timeCancel.setEnabled(false);
        //初始化并倒计时
        initCountDownTimer(TOTALTIME, COUNTTIMEUNIT);
        timerBtn.setText("开始计时");
    }

    @Override
    protected void onResume() {
        super.onResume();
        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CountDownTimerActivity.this, "倒计时开始", Toast.LENGTH_SHORT).show();
                countDownTimer.start();
            }
        });
        timeCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CountDownTimerActivity.this, "倒计时暂停", Toast.LENGTH_SHORT).show();
                countDownTimer.cancel();
            }
        });
    }
}
