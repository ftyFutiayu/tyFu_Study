package com.example.helloworld.activity.fragment.demo1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.helloworld.R;


/**
 * @Author: ${Tianyu.Fu}
 * @CreateDate: 2022/8/31
 * @Description: This is FragmentDemo1Left
 * demo1左侧Fragment
 */
public class FragmentDemo1Left extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_demo1_left, container, false);
        Button button = view.findViewById(R.id.fragment_demo1_left_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_demo1_Left, new FragmentDemo1AnotherLeft()).commit();
            }
        });
        return view;
    }

}