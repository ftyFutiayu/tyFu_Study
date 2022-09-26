package com.example.helloworld.activity.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Toast;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;
import com.example.helloworld.activity.activitylaunchmode.LaunchModeActivity;
import com.example.helloworld.activity.activity_life.TestActivityLifecycle_1;
import com.example.helloworld.activity.applicationresources.ResourcesActivity;
import com.example.helloworld.activity.base.BaseActivity;
import com.example.helloworld.activity.bottomsheet.BottomSheetActivity;
import com.example.helloworld.activity.broadcast.BestBroadcastTest;
import com.example.helloworld.activity.countdowntimer.CountDownTimerActivity;
import com.example.helloworld.activity.fragment.FragmentActivity;
import com.example.helloworld.activity.handler.HandlerPractiseActivity;
import com.example.helloworld.activity.layout.LayoutDefaultActivity;
import com.example.helloworld.activity.secondactivity.SecondActivity;
import com.example.helloworld.activity.ui.UIWidgetActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    //按钮名字集合
    private List<String> indexButtonNames;
    //activity集合
    private static final int SECOND_ACTIVITY = 0;
    private static final int BAI_DU = 1;
    private static final int ACTIVITY_LAUNCH_MODE = 2;
    private static final int ACTIVITY_LIFECYCLE = 3;
    private static final int BOTTOM_SHEET_BEHAVIOR = 4;
    private static final int UI_NORMAL_VIEW = 5;
    private static final int APPLICATION_RESOURCES = 6;
    private static final int PILOT_UI_LAYOUT = 7;
    private static final int FRAGMENT = 8;
    private static final int COUNTDOWN_TIME_DEMO = 9;
    private static final int HANDLER_COUNTDOWN_TIME = 10;
    private static final int BROADCAST_TEST = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        Log.i(TAG, "onCreate");
        //初始化list数据
        initData();
        //初始化RecyclerView
        RecyclerView recyclerView = findViewById(R.id.main_activity_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //初始化Adapter
        MainActivityAdapter adapter = new MainActivityAdapter(indexButtonNames);
        recyclerView.setAdapter(adapter);
        //实现点击事件
        adapter.setOnItemClickListener(new MainActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = null;
                Log.i(TAG, "onItemClick: " + position);
                switch (position) {
                    //跳转至Activity2
                    case SECOND_ACTIVITY:
                        //Toast消息
                        Toast.makeText(MainActivity.this, "You Clicked Button 1",
                                Toast.LENGTH_SHORT).show();
                        //1.显式intent 构建Intent 两个参数：第一个传入Context 第二个参数传入需要启动的目标
                        String data = "Hello SecondActivity";
                        intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("extra_data", data);
                        break;
                    //跳转至网页
                    case BAI_DU:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://www.baidu.com/"));
                        break;
                    //跳转至 Activity四种启动模式
                    case ACTIVITY_LAUNCH_MODE:
                        intent = new Intent(MainActivity.this, LaunchModeActivity.class);
                        break;
                    //跳转至 测试Activity生命周期
                    case ACTIVITY_LIFECYCLE:
                        intent = new Intent(MainActivity.this, TestActivityLifecycle_1.class);
                        break;
                    //跳转至 BottomSheetActivity
                    case BOTTOM_SHEET_BEHAVIOR:
                        intent = new Intent(MainActivity.this, BottomSheetActivity.class);
                        Log.i(TAG, "to BottomSheetActivity");
                        break;
                    //跳转至UI 常用VIew
                    case UI_NORMAL_VIEW:
                        intent = new Intent(MainActivity.this, UIWidgetActivity.class);
                        break;
                    //跳转至 应用程序资源应用
                    case APPLICATION_RESOURCES:
                        intent = new Intent(MainActivity.this, ResourcesActivity.class);
                        break;
                    //跳转至  Layout实现pilot默认UI布局
                    case PILOT_UI_LAYOUT:
                        intent = new Intent(MainActivity.this, LayoutDefaultActivity.class);
                        break;
                    case FRAGMENT:
                        intent = new Intent(MainActivity.this, FragmentActivity.class);
                        break;
                    case COUNTDOWN_TIME_DEMO:
                        intent = new Intent(MainActivity.this, CountDownTimerActivity.class);
                        break;
                    case HANDLER_COUNTDOWN_TIME:
                        //Handler相关demo
                        intent = new Intent(MainActivity.this, HandlerPractiseActivity.class);
                        break;
                    case BROADCAST_TEST:
                        //跳转至broadcast
                        intent = new Intent(MainActivity.this, BestBroadcastTest.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化Button_Name列表
     */
    private void initData() {
        indexButtonNames = new ArrayList<>();
        indexButtonNames.add("To Activity 2"); //0
        indexButtonNames.add("To Baidu"); //1
        indexButtonNames.add("To Launch Mode"); //2
        indexButtonNames.add("Test Activity Lifecycle"); //2
        indexButtonNames.add("BottomSheet Behavior"); //4
        indexButtonNames.add("UI常用view控件"); //5
        indexButtonNames.add("Application Resources"); //6
        indexButtonNames.add("Layout实现默认页面"); //7
        indexButtonNames.add("Fragment"); //8
        indexButtonNames.add("倒计时器demo"); //9
        indexButtonNames.add("Handler 倒计时demo"); //10
        indexButtonNames.add("Best Broadcast"); //11
    }
}
