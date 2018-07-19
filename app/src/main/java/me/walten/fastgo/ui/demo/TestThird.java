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
public class TestThird implements IXMenuData {
    private String name ;

    @Override
    public String getItemText() {
        return name;
    }

    @Override
    public List<IXMenuData> getNext() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
