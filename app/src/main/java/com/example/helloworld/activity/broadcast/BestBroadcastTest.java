package com.example.helloworld.activity.broadcast;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;

//广播机制学习
public class BestBroadcastTest extends BaseActivity {
    private static final String TAG = "BestBroadcastTest";

    public IntentFilter intentFilter;
    public NetworkChangeReceiver networkChangeReceiver;
    private static ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_broadcast_test);
        //初始化按钮
        Button button = findViewById(R.id.best_broadcast_button);
        //初始化广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
        //初始化ConnectivityManager：用于管理网络连接的系统服务类
        connectivityManager = (ConnectivityManager) getApplication().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        button.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.broadcast.broadcasttext.MY_BROADCAST");
            //TODO 不增加这部没办法发送管广播 PS:不同包下怎么发送广播
            intent.setComponent(new ComponentName(getPackageName(), //包名
                    "com.example.helloworld.activity.broadcast.MyBroadcastReceiver")); //广播接收器的路径
            sendBroadcast(intent);
            Log.i(TAG, "onClick:button ");
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册广播
        unregisterReceiver(networkChangeReceiver);
    }

    //网络发生变化后执行onReceive()方法
    static class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
            //NetworkInfo实例 调用isAvailable()方法判断网络情况
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            if (networkInfo != null && networkInfo.isAvailable()) {
//                Toast.makeText(context, "network is isAvailable", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "network is not  isAvailable", Toast.LENGTH_SHORT).show();
//            }
            if (isEnableToNetwork(context, connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork()))) {
                Toast.makeText(context, "network is isAvailable", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unAvailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * networkCapabilities监听网络情况
     * 以下是可用网络:
     * NetworkCapabilities.TRANSPORT_CELLULAR,
     * NetworkCapabilities.TRANSPORT_WIFI,
     * NetworkCapabilities.TRANSPORT_BLUETOOTH,
     * NetworkCapabilities.TRANSPORT_ETHERNET,
     * NetworkCapabilities.TRANSPORT_VPN,
     * NetworkCapabilities.TRANSPORT_WIFI_AWARE,
     * NetworkCapabilities.TRANSPORT_LOWPAN
     *
     * @return
     */
    public static boolean isEnableToNetwork(Context context, NetworkCapabilities networkCapabilities) {
        //API29中NetworkInfo弃用 增加判断build SDK
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            if (context == null) {
                return false;
            }
            if (connectivityManager == null) {
                return false;
            }
            //NetworkInfo实例 调用isAvailable()方法判断网络情况
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable();
        } else {
            return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) //判断是否有网
                    && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED); //判断网络是否有效
        }
    }
}
