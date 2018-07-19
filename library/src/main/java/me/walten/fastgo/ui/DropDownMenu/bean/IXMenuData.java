package me.walten.fastgo.ui.DropDownMenu.bean;

import java.util.List;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/18 : Create IXDropDownMenuBean.java (Walten);
 * -----------------------------------------------------------------
 */
public interface IXMenuData {

    String getItemText();

    List<IXMenuData> getNext();
}
