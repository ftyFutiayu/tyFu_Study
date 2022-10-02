package com.example.helloworld.activity.ui.recycleview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.helloworld.R;
import com.example.helloworld.activity.ui.adapter.FruitRecycleViewAdapter;
import com.example.helloworld.activity.ui.pojo.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecycleViewActivity extends AppCompatActivity {
    private List<Fruit> fruits = new ArrayList<>();

    private static final int FRUIT_NUMBER = 10;
    private static final int FRUIT_NAME_LENGTH = 20;

    private static final int STAGGERED_GRID_LAYOUT_COLUMNS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_layout);
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ui_widget_recycleview);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(STAGGERED_GRID_LAYOUT_COLUMNS, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        FruitRecycleViewAdapter adapter = new FruitRecycleViewAdapter(fruits);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化水果数据
     */
    public void initFruits() {
        for (int i = 0; i < FRUIT_NUMBER; i++) {
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            fruits.add(apple);
            Fruit greenApple = new Fruit(getRandomLengthName("GreenApple"), R.drawable.green_apple_pic);
            fruits.add(greenApple);
            Fruit banana = new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic);
            fruits.add(banana);
            Fruit cherry = new Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic);
            fruits.add(cherry);
            Fruit grape = new Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic);
            fruits.add(grape);
            Fruit coconut = new Fruit(getRandomLengthName("Coconut"), R.drawable.coconut_pic);
            fruits.add(coconut);
        }
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(FRUIT_NAME_LENGTH) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(name);
        }
        return stringBuilder.toString();
    }
}
