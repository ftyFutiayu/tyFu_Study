package com.example.helloworld.activity.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;
import com.example.helloworld.activity.ui.pojo.Fruit;

import java.util.List;

/**
 * @Author: Tianyu.Fu
 * @CreateDate: 2022/8/11
 * @Description: This is FruitRecycleViewAdapter
 */
public class FruitRecycleViewAdapter extends RecyclerView.Adapter<FruitRecycleViewAdapter.FruitAdapterHolder> {
    //全局变量 存储Fruit对象
    private List<Fruit> mFruitList;

    static class FruitAdapterHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;

        FruitAdapterHolder(View view) {
            super(view);
            fruitImage = (ImageView) view.findViewById(R.id.recycle_view_imageview);
            fruitName = (TextView) view.findViewById(R.id.recycle_view_textview);
        }
    }

    public FruitRecycleViewAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    /**
     * onCreateViewHolder()方法创建ViewHolder实例
     */
    @NonNull
    @Override
    public FruitAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获取布局的view
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fruit_item_layout_recycleview, parent, false);
        FruitAdapterHolder adapterHolder = new FruitAdapterHolder(view);
        return adapterHolder;
    }

    /**
     * onBindViewHolder()方法对子项进行赋值
     */
    @Override
    public void onBindViewHolder(@NonNull FruitAdapterHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getFruitImage());
        holder.fruitName.setText(fruit.getFruitName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
