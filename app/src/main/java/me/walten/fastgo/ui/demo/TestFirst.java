package me.walten.fastgo.ui.demo;

import java.util.List;

import me.walten.fastgo.ui.DropDownMenu.bean.IXMenuData;


/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/18 : Create Test.java (Walten);
 * -----------------------------------------------------------------
 */
public class TestFirst implements IXMenuData {
    private String name ;

    private List<TestSecond> data;

    @Override
    public String getItemText() {
        return name;
    }

    @Override
    public List getNext() {
        return data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestSecond> getData() {
        return data;
    }

    public void setData(List<TestSecond> data) {
        this.data = data;
    }
}
