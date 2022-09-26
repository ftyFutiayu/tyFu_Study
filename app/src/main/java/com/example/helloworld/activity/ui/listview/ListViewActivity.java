package com.example.helloworld.activity.ui.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.helloworld.R;
import com.example.helloworld.activity.ui.adapter.FruitListViewAdapter;
import com.example.helloworld.activity.ui.pojo.Fruit;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);
        //初始化Fruit集合
        initFruits();
        //调用自定义适配器 加载fruit数据和布局
        FruitListViewAdapter fruitListViewAdapter = new FruitListViewAdapter(ListViewActivity.this, R.layout.fruit_item_layout_listview, fruitList);
        ListView listView = (ListView) findViewById(R.id.ui_widget_list_view);
        listView.setAdapter(fruitListViewAdapter);
    }

    /**
     * 初始化水果数据
     */
    public void initFruits() {
        for (int i = 0; i < 10; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit greenApple = new Fruit("GreenApple", R.drawable.green_apple_pic);
            fruitList.add(greenApple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit coconut = new Fruit("Coconut", R.drawable.coconut_pic);
            fruitList.add(coconut);
        }
    }
}
