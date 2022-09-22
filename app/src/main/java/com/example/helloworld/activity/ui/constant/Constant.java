package com.example.helloworld.activity.ui.constant;

/**
 * @Author: Tianyu.Fu
 * @CreateDate: 2022/8/12
 * @Description: This is Constant
 */
public enum Constant {
    PASSWORD("password", "1111");

    private String name;
    private String index;

    Constant(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }
}
