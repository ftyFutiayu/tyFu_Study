package com.example.helloworld.activity.applicationresources;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;
import com.example.helloworld.activity.applicationresources.adapter.ResourcesAdapter;
import com.example.helloworld.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Android应用程序中资源类调用 (字符串、格式、字体、颜色)
 */
public class ResourcesActivity extends BaseActivity {

    private List<String> stringList = new ArrayList<>();

    private static final String TAG = "ResourcesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_resources_activity);
        //通过 getResources获取字符串数组 并显示RecyclerView
        RecyclerView recyclerView = findViewById(R.id.application_resources_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        ResourcesAdapter adapter = new ResourcesAdapter(getResources().getStringArray(R.array.apples));
        recyclerView.setAdapter(adapter);
       /*
        java代码中设置图片
         */
        ImageView imageView1 = findViewById(R.id.resources_imageview_java_1);
        ImageView imageView2 = findViewById(R.id.resources_imageview_java_2);
        ImageView imageView3 = findViewById(R.id.resources_imageview_java_3);
        //java代码调用图片--setImageDrawable(Drawable) 效率最高
        Drawable drawable = getDrawable(R.drawable.banana_pic);
        imageView1.setImageDrawable(drawable);
        //java代码调用图片--setImageBitmap(Bitmap)
        //先把bitmap放在一个BitmapDrawable中，然后调用的setImageDrawable方法。
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cherry_pic);
        imageView2.setImageBitmap(bitmap);
        //java代码调用图片--setImageResource(id)
        //setImageResource会使用UI thread线程执行resolveUri()从resource id解析出drawable对象，造成主线程的延迟阻塞
        imageView3.setImageResource(R.drawable.grape_pic);
        /*
        java代码中设置textView的文字
         */
        TextView textView1 = findViewById(R.id.resources_textview_java_1);
        TextView textView2 = findViewById(R.id.resources_textview_java_2);
        TextView textView3 = findViewById(R.id.resources_textview_java_3);
        //java代码调用文本--setText(res id)
        textView1.setText(R.string.app_name);
        //java代码调用文本--setText(String)
        textView2.setText("This is from java");
        //java代码调用文本--setText(res id,BufferType)
        //BufferType:EDITABLE、SPANNABLE、NORMAL
        //EDITABLE，多用于在保存数据持久化 可在之后使用append方法，SPANNAABLE则用于设置如TextView，
        textView3.setText(R.string.string_resources, TextView.BufferType.SPANNABLE);
        /*
        java代码中设置尺寸和颜色
         */
        //java代码调用尺寸--setTextSize(数字)默认的size 单位为sp
        textView1.setTextSize(18);
        textView2.setTextSize(getResources().getDimension(R.dimen.dimen_40));
        //setTextSize单位为px
        textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.dimen_40));
        textView1.setText(textView1.getText() + " 这是追加文本");
        //java代码调用颜色--setTextColor--直接获取
        textView1.setTextColor(getResources().getColor(R.color.purple_200));
        //java代码调用颜色--setTextColor--colorStateList获取
        ColorStateList colorStateList = getResources().getColorStateList(R.color.color_state, null);
        textView2.setTextColor(colorStateList);
    }
}
