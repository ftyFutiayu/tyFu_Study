package com.example.helloworld.activity.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

import java.util.List;

/**
 * @Author: zee001-w
 * @CreateDate: 2022/8/30
 * @Description: This is MainActivityAdapter 适配器
 * 将首页按钮重构为RecyclerView
 */
public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> implements View.OnClickListener {
    private static final String TAG = "MainActivityAdapter";

    //首页按钮的名称
    List<String> mIndexButtonsNames;

    /*
     自定义ViewHolder内部类
     */
    class MainActivityViewHolder extends RecyclerView.ViewHolder {
        //列表中按钮名称
        Button mIndexButton;

        MainActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            //初始化列表中button
            mIndexButton = itemView.findViewById(R.id.index_button);
            //为Button添加点击事件
            mIndexButton.setOnClickListener(MainActivityAdapter.this);
        }
    }

    //构造函数初始化mIndexButtons
    public MainActivityAdapter(List<String> indexButtonNames) {
        mIndexButtonsNames = indexButtonNames;
    }

    /**
     * 构建ViewHolder 加载item布局
     */
    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载子布局 获取view
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.main_activity_item_list, parent, false);
        //初始化自定义ViewHolder
        MainActivityViewHolder holder = new MainActivityViewHolder(view);
        return holder;
    }

    /**
     * 绑定
     * 对RecyclerView子项进行赋值 当屏幕显示某个子项时执行
     */
    @Override
    public void onBindViewHolder(@NonNull MainActivityViewHolder holder, int position) {
        //获得当前元素 -- 按钮名称
        String indexButtonName = mIndexButtonsNames.get(position);
        //设置ViewHolder当前按钮名称
        holder.mIndexButton.setText(indexButtonName);
        holder.itemView.setTag(position);
        holder.mIndexButton.setTag(position);
    }

    /**
     * 获取子项数量 有多少名字就有多少按钮
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mIndexButtonsNames.size();
    }


    //########################实现按钮点击事件#############################
    //自定义一个回调接口来实现Click事件
    public interface OnItemClickListener {
         void onItemClick(View v, int position);
    }

    private OnItemClickListener mOnItemClickListener; //声明自定义的接口

    //定义方法并暴露给外面的调用者
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        //getTag()获取数据
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, position);
        }
    }
}


