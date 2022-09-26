package com.example.helloworld.activity.bottom_sheet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.activity.base.BaseActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.example.helloworld.R;

/**
 * BottomSheetBehavior 使用 （未完成）
 */
public class BottomSheetActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "BottomSheetActivity";
    private BottomSheetBehavior<View> behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_layout);
        Button btnBehavior = findViewById(R.id.btn_Behavior);
        Button btnDialog = findViewById(R.id.btn_Dialog);
        btnBehavior.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        //使用from()静态方法获取behavior
        View bottomSheet = findViewById(R.id.bottom_sheet);
        if (bottomSheet != null) {
            behavior = BottomSheetBehavior.from(bottomSheet);
        }
    }

    //点击按钮事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Behavior:
                //获取当前behavior状态
                int state = behavior.getState();
                //EXPANDED 全部展开状态
                //COLLAPSED 收起状态
                //HIDDEN 隐藏状态
                if (state == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if (state == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else if (state == BottomSheetBehavior.STATE_HIDDEN) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.btn_Dialog:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout_include);
                bottomSheetDialog.show();
                break;
            default:
                break;
        }
    }
}
