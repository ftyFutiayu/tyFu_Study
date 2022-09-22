package com.example.helloworld.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworld.R;

/**
 * @Author: ${Tianyu.Fu}
 * @CreateDate: 2022/8/31
 * @Description: This is TestLifecycleRightFragment
 */
public class TestLifecycleAnotherRightFragment extends Fragment {

    private static final String TAG = "TestLifecycleAnotherRightFragment";

    /**
     * 与Activity建立生命周期时调用
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: " + "--Fragment--" + "test lifecycle");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: " + "--Fragment--" + "test lifecycle");
    }

    /**
     * Fragment建立视图时调用
     *
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: " + "--Fragment--" + "test lifecycle");
        View view = inflater.inflate(R.layout.fragment_test_lifecycle_another_right, container, false);
        return view;
    }

    /**
     * Activity创建后调用
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: " + "--Fragment--" + "test lifecycle");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: " + "--Fragment--"+ "test lifecycle");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: " + "--Fragment--" + "test lifecycle");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: " + "--Fragment--" + "test lifecycle");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: " + "--Fragment--" + "test lifecycle");
    }

    /**
     * 与Fragment相关视图被移除时调用
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: " + "--Fragment--" + "test lifecycle");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: " + "--Fragment--" + "test lifecycle");
    }

    /**
     * 与Activity解除联系
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: " + "--Fragment--" + "test lifecycle");
    }
}