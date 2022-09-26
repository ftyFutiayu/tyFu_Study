package com.example.helloworld.activity.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;
import com.example.helloworld.activity.ui.constant.Constant;
import com.example.helloworld.activity.ui.listview.ListViewActivity;
import com.example.helloworld.activity.ui.recycleview.RecycleViewActivity;

/**
 * UI中各种常用控件 View
 */
public class UIWidgetActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;

    private ImageView imageView;

    private ProgressBar progressBar;

    private int imageState = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_widget_layout);
        Button send = findViewById(R.id.button_ui_widget_send);
        send.setOnClickListener(this);
        Button changePic = findViewById(R.id.button_ui_widget_change_pic);
        changePic.setOnClickListener(this);
        Button changeProgress = findViewById(R.id.button_ui_widget_change_progress);
        changeProgress.setOnClickListener(this);
        Button alertDialog = findViewById(R.id.button_ui_widget_alertdialog);
        alertDialog.setOnClickListener(this);
        Button progressDialog = findViewById(R.id.button_ui_widget_progress_dialog);
        progressDialog.setOnClickListener(this);
        Button surface = findViewById(R.id.button_ui_widget_surface_view);
        surface.setOnClickListener(this);
        Button button_ui_widget_listview = findViewById(R.id.button_ui_widget_listview);
        button_ui_widget_listview.setOnClickListener(this);
        Button button_ui_widget_recycleview = findViewById(R.id.button_ui_widget_recycleview);
        button_ui_widget_recycleview.setOnClickListener(this);
        editText = findViewById(R.id.edit_text_ui_widget);
        progressBar = findViewById(R.id.progress_bar_ui_widget);
        imageView = findViewById(R.id.image_view_ui_widget);
    }

    /**
     * Button点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_ui_widget_send:
                //获取EditView中的文本
                String input = editText.getText().toString();
                Toast.makeText(UIWidgetActivity.this, input, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_ui_widget_change_pic:
                if (imageState == 2) {
                    imageView.setImageResource(R.drawable.img_1);
                    imageState = 1;
                } else {
                    imageView.setImageResource(R.drawable.img_2);
                    imageState = 2;
                }
                break;
            case R.id.button_ui_widget_change_progress:
                int process = progressBar.getProgress();
                process += 10;
                progressBar.setProgress(process);
                break;
            case R.id.button_ui_widget_alertdialog:
                showDialog();
                break;
            case R.id.button_ui_widget_progress_dialog:
                ProgressDialog progressDialog = new ProgressDialog(UIWidgetActivity.this);
                progressDialog.setTitle("This is progress dialog");
                progressDialog.setMessage("Something is important");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            case R.id.button_ui_widget_surface_view:
                Intent intent = new Intent(UIWidgetActivity.this, SurfaceViewActivity.class);
                startActivity(intent);
                break;
            case R.id.button_ui_widget_listview:
                intent = new Intent(UIWidgetActivity.this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.button_ui_widget_recycleview:
                intent = new Intent(UIWidgetActivity.this, RecycleViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化并弹出对话框的方法
     */
    private void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.diy_dialog_layout, null, false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        dialog.setCancelable(false);
        //默认不显示错误提示信息
        //初始化Dialog中Button、edittext、hiddenTextView
        Button dialog_check = view.findViewById(R.id.dialog_check);
        EditText dialogEditText = view.findViewById(R.id.dialog_edittext);
        TextView hiddenTextView = view.findViewById(R.id.dialog_hidden_text);
        //错误提示信息先隐藏
        hiddenTextView.setVisibility(View.INVISIBLE);

        //增加对EditText的监听
        dialogEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable editable) {
                //获取当前输入的字数
                int curTextLen = editable.length();
                //输入小于三位数无法点击按钮
                if (curTextLen < 3) {
                    dialog_check.setBackgroundColor(R.color.gray);
                    dialog_check.setEnabled(false);
                }
                //输入三位数可以点击按钮
                else if (curTextLen == 3) {
                    dialog_check.setBackgroundColor(R.color.white);
                    dialog_check.setEnabled(true);
                }
                //输入四位数自动判断当前密码是否正确
                else if (curTextLen == Constant.PASSWORD.getIndex().length()) {
                    boolean isPasswordTrue = checkPassword(dialogEditText.getText().toString());
                    //只有密码正确点击Send才能退出dialog
                    if (isPasswordTrue) {
                        hiddenTextView.setVisibility(View.INVISIBLE);
                        dialog_check.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getToast(UIWidgetActivity.this, "恭喜你密码正确").show();
                                dialog.dismiss();
                            }
                        });
                    } else {
                        hiddenTextView.setVisibility(View.VISIBLE);
                        hiddenTextView.setText("密码错误");
                        dialog_check.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getToast(UIWidgetActivity.this, "密码错误").show();
                            }
                        });
                    }
                }
                //密码长度不对或密码错误： 显示红色文字 点击按钮弹窗密码错误
                else {
                    hiddenTextView.setText("密码格式错误");
                    hiddenTextView.setVisibility(View.VISIBLE);
                    dialog_check.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getToast(UIWidgetActivity.this, "密码格式错误").show();
                        }
                    });
                }
            }
        });
        //展示当前dialog
        dialog.show();
    }

    //检查密码是否正确
    private boolean checkPassword(String input) {
        if (input.equals(Constant.PASSWORD.getIndex())) {
            return true;
        }
        return false;
    }

    /**
     * 获得指定文字的弹窗
     *
     * @param toastText 弹窗文本
     * @return toast
     */
    private Toast getToast(Context context, String toastText) {
        Toast toast = Toast.makeText(UIWidgetActivity.this, toastText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        return toast;
    }
}
