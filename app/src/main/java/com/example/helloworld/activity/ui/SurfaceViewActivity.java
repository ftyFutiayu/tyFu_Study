package com.example.helloworld.activity.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.helloworld.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Surface实现：
 * 1.播放视频
 * 2.点击播放按钮播放 暂停按钮暂停
 * 3.进度条控制视频进度
 */
public class SurfaceViewActivity extends AppCompatActivity implements
        SeekBar.OnSeekBarChangeListener, SurfaceHolder.Callback, View.OnClickListener {
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private RelativeLayout relativeLayout;
    private ImageView play;
    private SeekBar seekBar;
    //定时器
    private Timer timer;
    private TimerTask timerTask;

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface_view_layout);
        //初始化surfaceView控件
        surfaceView = (SurfaceView) findViewById(R.id.ui_widget_surfaceview);
        //初始化surfaceHolder容器 界面内容显示在surfaceHolder中
        surfaceHolder = surfaceView.getHolder();
        //添加回调方法
        surfaceHolder.addCallback((SurfaceHolder.Callback) this);
        //初始化relativeLayout imageView SeekBar
        relativeLayout = (RelativeLayout) findViewById(R.id.ui_widget_relativelayout);
        play = (ImageView) findViewById(R.id.ui_widget_play);
        //播放暂停设置点击事件
        play.setOnClickListener(this);
        //初始化进度条
        seekBar = (SeekBar) findViewById(R.id.ui_widget_seekbar);
        seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
        //初始化定时器
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    //获得视频时长
                    int total = mediaPlayer.getDuration();
                    //进度条总长度与视频总长度同步
                    seekBar.setMax(total);
                    //获得当前的进度
                    int progress = mediaPlayer.getCurrentPosition();
                    //进度条当前长度与视频当前长度同步
                    seekBar.setProgress(progress);
                } else {
                    play.setImageResource(android.R.drawable.ic_media_play);
                }
            }
        };
        //设置TimerTask延迟500ms 每500ms执行一次
        timer.schedule(timerTask, 500, 500);
    }


    /**
     * Surface View创建时触发 实现加载视频和播放视频
     *
     * @param surfaceHolder :surfaceHolder容器
     */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        try {
            mediaPlayer = new MediaPlayer();
            //设置mediaPlayer属性
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE).build());
            Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + getPackageName() + "/" + R.raw.video);

            try {
                //MediaPlayer设置视频源
                mediaPlayer.setDataSource(SurfaceViewActivity.this, uri);
            } catch (IOException e) {
                Toast.makeText(SurfaceViewActivity.this,
                        "播放失败",
                        Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            //使用surfaceHolder显示多媒体
            mediaPlayer.setDisplay(surfaceHolder);
            //播放视频前需要处于Prepared状态 这里使用异步prepare
            mediaPlayer.prepareAsync();
            //添加监听 执行播放视频
            mediaPlayer.setOnPreparedListener((mp) -> {
                mediaPlayer.start();
            });
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    /**
     * 控制视频的暂停播放
     */
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    //进度条发生变化时触发
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    //进度条开始拖动时触发
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    //进度条结束拖动时触发
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //获取进度条拖动结束时的视频进程
        int position = seekBar.getProgress();
        //设置视频的播放开始时间seekTo()
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
        }
    }

    /**
     * 通过onTouchEvent()屏幕触摸事件实现RelativeLayout的隐藏与出现
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //通过不同事件执行不同功能 getAction()获取事件类型
        switch (event.getAction()) {
            //监听点击屏幕事件
            case MotionEvent.ACTION_DOWN:
                //当RelativeLayout隐藏时 显示layout
                if (relativeLayout.getVisibility() == View.INVISIBLE) {
                    relativeLayout.setVisibility(View.VISIBLE);
                }
                //倒计时 3s后隐藏
                CountDownTimer cdt = new CountDownTimer(3000, 1000) {
                    //计时器计时时
                    @SuppressWarnings("checkstyle:Regexp")
                    @Override
                    public void onTick(long l) {
                        System.out.println(l);
                    }
                    //计时器结束时
                    @Override
                    public void onFinish() {
                        relativeLayout.setVisibility(View.INVISIBLE);
                    }
                };
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设计点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ui_widget_play:
                //视频播放状态时 点击图片显示暂停
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    play.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    mediaPlayer.start();
                    play.setImageResource(android.R.drawable.ic_media_pause);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 回收创建的各对象资源
     */
    @Override
    protected void onDestroy() {
        timer.cancel();
        timerTask.cancel();
        timerTask = null;
        timer = null;
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();
    }
}
