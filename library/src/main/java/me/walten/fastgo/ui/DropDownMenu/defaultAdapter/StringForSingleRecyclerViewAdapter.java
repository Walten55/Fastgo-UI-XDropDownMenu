package me.walten.fastgo.ui.DropDownMenu.defaultAdapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Arrays;
import java.util.List;

import me.walten.fastgo.ui.DropDownMenu.R;


/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/17 : Create SingleRecycleView.java (Walten);
 * -----------------------------------------------------------------
 */
public class StringForSingleRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    //默认选中第一个
    private int selectPosition = 0;

    private int selectColor = -1;

    public StringForSingleRecyclerViewAdapter(@Nullable List<String> data) {
        super(R.layout.drop_down_menu_center_text, data);
    }

    public StringForSingleRecyclerViewAdapter(@Nullable String[] data) {
        super(R.layout.drop_down_menu_center_text, Arrays.asList(data));
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_text,item);

        if(selectPosition == helper.getAdapterPosition()){
            helper.setBackgroundColor(R.id.rl_item, Color.WHITE);
            if(selectColor!=-1)
                helper.setTextColor(R.id.tv_text,selectColor);
        }else {
            helper.setBackgroundColor(R.id.rl_item, Color.parseColor("#F5F5F5"));
            if(selectColor!=-1)
                helper.setTextColor(R.id.tv_text,Color.parseColor("#5c5c5c"));
        }
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }
}
