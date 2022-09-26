package com.example.helloworld.activity.second_activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;

/**
 * 练习Activity跳转
 */
public class SecondActivity extends BaseActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_layout);
        //获取上一个activity传递的数据
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.d(TAG, data);
        Button button2 = findViewById(R.id.Button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 显示菜单方法
     * 重写onCreateOptionsMenu()，接受Menu文件并创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //接受menu文件、菜单项传递给哪一个Menu对象
        getMenuInflater().inflate(R.menu.main, menu);
        //允许创建的菜单显示
        return true;
    }

    /**
     *重新定义菜单响应事件
     *根据菜单id不同显示不同内容
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
