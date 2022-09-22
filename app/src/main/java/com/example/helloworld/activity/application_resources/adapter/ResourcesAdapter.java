package com.example.helloworld.activity.application_resources.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;
import com.example.helloworld.activity.application_resources.ResourcesActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @Author:  Tianyu.Fu
 * @CreateDate: 2022/8/19
 * @Description: This is ResourcesAdapter
 */
public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ResourcesViewHolder> {
    private List<String> mApples;


    static class ResourcesViewHolder extends RecyclerView.ViewHolder {
        TextView resource_item;

        ResourcesViewHolder(View view) {
            super(view);
            resource_item = (TextView) view.findViewById(R.id.resources_item);
        }
    }

    public ResourcesAdapter(String[] apples) {
        mApples = Arrays.asList(apples);
    }

    @NonNull
    @Override
    public ResourcesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.resources_string_item,
                parent,
                false);
        ResourcesViewHolder viewHolder = new ResourcesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResourcesViewHolder holder, int position) {
        String str = mApples.get(position);
        holder.resource_item.setText(str);
    }

    @Override
    public int getItemCount() {
        return mApples.size();
    }

}