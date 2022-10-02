package com.example.helloworld.activity.layout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.helloworld.activity.base.BaseActivity;
import com.example.helloworld.activity.utils.SlideButton;
import com.example.helloworld.controller.ActivityController;

/**
 * Layout 学习 创建pilot默认UI布局
 */
public class LayoutDefaultActivity extends BaseActivity implements View.OnClickListener {
    //当前类常数
    private static final int POP_WINDOW_WIDTH = 400;
    private static final int POP_WINDOW_HEIGHT = 400;
    private static final int POP_WINDOW_VIEW_HEIGHT = 10;
    private static final int POP_WINDOW_X = 200;
    private static final int POP_WINDOW_Y = 250;

    private static final String TAG = "LayoutDefaultActivity";

    private EditText editText; //文本框
    private Button defaultLayoutConfirmBtn; //输入目的地后确认0
    private String destination; //目的地信息
    private static boolean showChildLayout; //是否显示子布局
    private ImageButton settingBtn; //设置弹窗按钮
    private ImageView imageViewRight; //右图片
    private SlideButton slideButton; //滑动按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_default_activity);
        //设置布局左右image
        ImageView imageViewLeft = findViewById(R.id.default_layout_left_image);
        Drawable imageLeft = getDrawable(R.drawable.layout_left);
        imageViewLeft.setImageDrawable(imageLeft);
        imageViewRight = findViewById(R.id.default_layout_right_image);
        Drawable imageRight = getDrawable(R.drawable.layout_right);
        imageViewRight.setImageDrawable(imageRight);
        //初始化各控件
        showChildLayout = false;
        ImageButton exitApaBtn = findViewById(R.id.default_layout_exit_apa); //退出apa按钮
        exitApaBtn.setOnClickListener(this);
        ImageButton exitBtn = findViewById(R.id.default_layout_exit); //退出程序按钮
        exitBtn.setOnClickListener(this);
        editText = findViewById(R.id.default_layout_edittext);
        defaultLayoutConfirmBtn = findViewById(R.id.default_layout_confirm_btn); //确认目的地按钮
        defaultLayoutConfirmBtn.setOnClickListener(this);
        settingBtn = findViewById(R.id.default_layout_setting_btn); //设置弹窗按钮
        settingBtn.setOnClickListener(this);

    }

    /**
     * onResume 当可交互时 对EditView字数进行监听
     */
    @Override
    protected void onResume() {
        super.onResume();
        //当输入一个字符以上时显示隐藏的确认按钮
        editText.addTextChangedListener(new TextWatcher() {
            //editText改变前执行
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //editText改变中执行
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //editText改变后执行
            @Override
            public void afterTextChanged(Editable s) {
                //获得当前输入字符串长度 仅在一个字符以上显示确认按钮
                int curLen = s.length();
                if (curLen >= 1) {
                    defaultLayoutConfirmBtn.setVisibility(View.VISIBLE);
                } else {
                    defaultLayoutConfirmBtn.setVisibility(View.INVISIBLE);
                }
                destination = editText.getText().toString();
            }
        });
    }

    /**
     * 实现OnClickListener接口 重写onClick()方法
     */
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.default_layout_exit_apa://退出apa模式
                Toast.makeText(LayoutDefaultActivity.this, "Exit ApA", Toast.LENGTH_SHORT).show();
                break;
            case R.id.default_layout_exit://退出应用按钮
                //弹出自定义对话框
                showDialog();
                break;
            case R.id.default_layout_confirm_btn://目的地确认按钮
                if (destination != null) {
                    Toast.makeText(LayoutDefaultActivity.this,
                            "正在前往: " + destination,
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LayoutDefaultActivity.this,
                            "请输入目的地",
                            Toast.LENGTH_LONG).show();
                }
                break;
            //点击按钮显示隐藏设置子布局
            case R.id.default_layout_setting_btn:
                //加载layout_default_activity
                View view = LayoutInflater.from(LayoutDefaultActivity.this)
                        .inflate(R.layout.default_layout_setting, null);
                //初始化滑动按钮
                slideButton = view.findViewById(R.id.silde_button);
                if (slideButton == null) {
                    Log.i(TAG, "onClick: slide is null");
                }
                slideButton.setSmallCircleModel(Color.parseColor("#cccccc"), //圆角矩形的边颜色
                        Color.parseColor("#00000000"), //圆角矩形的填充颜色
                        R.color.color_cbc77ed7, //按钮被选中颜色
                        Color.parseColor("#cccccc")); //按钮没有被选中颜色

                //初始化PopWindows 将view传入
                PopupWindow myPop = new PopupWindow(view, POP_WINDOW_WIDTH, POP_WINDOW_HEIGHT);
                myPop.setContentView(view);
                //基本设置
                myPop.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
                myPop.setElevation(POP_WINDOW_VIEW_HEIGHT); //设置弹出动画高度
                myPop.setOutsideTouchable(true);
                // 如果不设置，在PopupWindow弹出的时候，点击返回键将直接退出Activity
                // 设置之后，在PopupWindow弹出的时候，点击返回键不会直接退出Activity而是关闭当前弹出的PopupWindow
                myPop.setFocusable(true); //设置是否可聚焦
                myPop.setTouchable(true); //设置可触摸
                //pop弹窗锚点选择为设置按钮 即坐标原点
                myPop.showAtLocation(settingBtn,
                        Gravity.BOTTOM,
                        POP_WINDOW_X,
                        POP_WINDOW_Y);
                Log.i(TAG, "onClick: myPop");
                break;
            default:
                break;
        }
    }

    /**
     * 点击default_layout_exit按钮显示退出dialog
     */
    public void showDialog() {
        //初始化 diy_exit_dialog 布局
        View exitDialogView = LayoutInflater.from(this).inflate(R.layout.diy_exit_dialog, null, false);
        //定义自定义dialog对象
        final AlertDialog exitDialog = new AlertDialog.Builder(this, R.style.DiyExitDialog)
                .setView(exitDialogView)
                .create();
        //对话框头部出现空白 取消头部试试
        exitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        exitDialog.setContentView(R.layout.diy_exit_dialog);
        exitDialog.setCancelable(false); //设置为不可取消
        //初始化exit_dialog中Button对象 这里一定要是View.findViewById
        // 一般生命周期函数中的findViewById为Activity的方法 其余的都是View的 所以前面需要加上View.findViewById
        AppCompatButton diyExitDialogPositive = exitDialogView.findViewById(R.id.diy_exit_dialog_positive);
        AppCompatButton diyExitDialogNegative = exitDialogView.findViewById(R.id.diy_exit_dialog_negative);
        diyExitDialogPositive.setOnClickListener((View v) -> ActivityController.removeAllActivities());
        diyExitDialogNegative.setOnClickListener((View v) -> exitDialog.dismiss());
        //弹出对话框 show
        exitDialog.show();
    }

}
