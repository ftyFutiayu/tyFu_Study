package com.example.helloworld.activity.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.helloworld.R;
import com.example.helloworld.activity.ui.pojo.Fruit;

import java.util.List;

/**
 * @Author: Tianyu.Fu
 * @CreateDate: 2022/8/11
 * @Description: This is FruitAdapter自定义水果适配器
 */
public class FruitListViewAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitListViewAdapter(Context context, int textViewResourceId, List<Fruit> data) {
        super(context, textViewResourceId, data);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //获得当前子项书局
        Fruit currentFruit = getItem(position);
        //首先判断convertView是否有当前子项缓存
        View view;
        FruitHolder fruitHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            fruitHolder = new FruitHolder();
            fruitHolder.fruitImage = (ImageView) view.findViewById(R.id.list_view_imageview);
            fruitHolder.fruitName = (TextView) view.findViewById(R.id.list_view_textview);
            view.setTag(fruitHolder);
        } else {
            view = convertView;
            fruitHolder = (FruitHolder) view.getTag();
        }
        fruitHolder.fruitImage.setImageResource(currentFruit.getFruitImage());
        fruitHolder.fruitName.setText(currentFruit.getFruitName());
        return view;
    }
    //内部类 用于记录view中的image和name信息
    class FruitHolder {
        ImageView fruitImage;
        TextView fruitName;
    }
}