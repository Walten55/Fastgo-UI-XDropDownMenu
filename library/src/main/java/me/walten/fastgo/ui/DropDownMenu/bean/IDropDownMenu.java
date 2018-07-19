package me.walten.fastgo.ui.DropDownMenu.bean;

import android.view.View;

import me.walten.fastgo.ui.DropDownMenu.XDropDownButton;


/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/17 : Create IDropDownMenu.java (Walten);
 * -----------------------------------------------------------------
 */
public interface IDropDownMenu {
     /**
      * 下拉view
      * @return
      */
     View getDropDownView();

     /**
      * 展开
      * @param curMenuButton 当前操作的MenuButton
      */
     void expand(XDropDownButton curMenuButton);

     /**
      * 合上
      */
     void close();
}
