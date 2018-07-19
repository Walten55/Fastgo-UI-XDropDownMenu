package me.walten.fastgo.ui.DropDownMenu.defaultAdapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.walten.fastgo.ui.DropDownMenu.R;
import me.walten.fastgo.ui.DropDownMenu.bean.IXMenuData;


/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/18 : Create MultiRecyclerViewAdapter.java (Walten);
 * -----------------------------------------------------------------
 */
public class MultiRecyclerViewAdapter extends BaseQuickAdapter<IXMenuData,BaseViewHolder>{

    //默认不选中
    private int selectPosition = -1;

    private int selectColor = -1;

    private boolean isFirst = true;

    public MultiRecyclerViewAdapter(@Nullable List<IXMenuData> data) {
        super(R.layout.drop_down_menu_left_text, data);
    }

    public void notFirst(){
        isFirst = false;
    }

    @Override
    protected void convert(BaseViewHolder helper, IXMenuData item) {
        helper.setText(R.id.tv_text,item.getItemText());

        if(isFirst){
            if(selectPosition == helper.getAdapterPosition()){
                helper.setBackgroundColor(R.id.rl_item, Color.WHITE);
                if(selectColor!=-1)
                    helper.setTextColor(R.id.tv_text,selectColor);
            }else {
                helper.setBackgroundColor(R.id.rl_item, Color.parseColor("#F5F5F5"));
                if(selectColor!=-1)
                    helper.setTextColor(R.id.tv_text,Color.parseColor("#5c5c5c"));
            }
        }else {
            helper.setBackgroundColor(R.id.rl_item, Color.WHITE);
            if(selectPosition == helper.getAdapterPosition()){
                if(selectColor!=-1)
                    helper.setTextColor(R.id.tv_text,selectColor);
            }else {
                if(selectColor!=-1)
                    helper.setTextColor(R.id.tv_text,Color.parseColor("#5c5c5c"));
            }
        }

    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    public int getSelectColor() {
        return selectColor;
    }

    public int getSelectPosition() {
        return selectPosition;
    }
}
