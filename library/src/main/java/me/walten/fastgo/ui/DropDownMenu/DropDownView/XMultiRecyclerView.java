package me.walten.fastgo.ui.DropDownMenu.DropDownView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.SlideInRightAnimation;

import java.util.ArrayList;
import java.util.List;

import me.walten.fastgo.ui.DropDownMenu.bean.IXMenuData;
import me.walten.fastgo.ui.DropDownMenu.defaultAdapter.MultiRecyclerViewAdapter;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/18 : Create XDoubleRecyclerView.java (Walten);
 * -----------------------------------------------------------------
 */
public class XMultiRecyclerView extends LinearLayout implements BaseQuickAdapter.OnItemClickListener {

    private MultiRecyclerViewAdapter firstAdapter;
    private MultiRecyclerViewAdapter secondAdapter;
    private MultiRecyclerViewAdapter thirdAdapter;
    private OnSelectedListener listener;
    private List<IXMenuData> selectedList = new ArrayList<>();

    public XMultiRecyclerView(Context context) {
        this(context,null);
    }

    public XMultiRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XMultiRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        setOrientation(HORIZONTAL);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        lp.weight = 1;
        addView(new RecyclerView(getContext()),lp);
        addView(new RecyclerView(getContext()),lp);
        addView(new RecyclerView(getContext()),lp);

        ((RecyclerView) getChildAt(0)).setLayoutManager(new LinearLayoutManager(getContext()));
        ((RecyclerView) getChildAt(1)).setLayoutManager(new LinearLayoutManager(getContext()));
        ((RecyclerView) getChildAt(2)).setLayoutManager(new LinearLayoutManager(getContext()));

        getChildAt(1).setVisibility(View.GONE);
        getChildAt(2).setVisibility(View.GONE);
    }

    public void setAdapter(MultiRecyclerViewAdapter adapter){
        firstAdapter = adapter;
        RecyclerView first = (RecyclerView) getChildAt(0);
        first.setAdapter(firstAdapter);
        firstAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        IXMenuData data = (IXMenuData) adapter.getItem(position);

        if(adapter == firstAdapter){
            firstAdapter.setSelectPosition(position);
        }else if(adapter == secondAdapter){
            secondAdapter.setSelectPosition(position);
        }else if(adapter == thirdAdapter){
            thirdAdapter.setSelectPosition(position);
        }

        if(data.getNext()!=null&&data.getNext().size()!=0){
            if(adapter == firstAdapter){
                secondAdapter = new MultiRecyclerViewAdapter(data.getNext());
                secondAdapter.setSelectPosition(-1);
                if(thirdAdapter!=null)
                    thirdAdapter.setSelectPosition(-1);
                secondAdapter.setSelectColor(firstAdapter.getSelectColor());
                secondAdapter.notFirst();
                RecyclerView second = (RecyclerView) getChildAt(1);
                secondAdapter.openLoadAnimation(new SlideInRightAnimation());
                second.setAdapter(secondAdapter);
                secondAdapter.setOnItemClickListener(this);

                getChildAt(1).setVisibility(View.VISIBLE);
                getChildAt(2).setVisibility(View.GONE);
            }else if(adapter == secondAdapter){
                thirdAdapter = new MultiRecyclerViewAdapter(data.getNext());
                thirdAdapter.setSelectPosition(-1);
                thirdAdapter.setSelectColor(firstAdapter.getSelectColor());
                thirdAdapter.notFirst();
                RecyclerView third = (RecyclerView) getChildAt(2);
                thirdAdapter.openLoadAnimation(new SlideInRightAnimation());
                third.setAdapter(thirdAdapter);
                thirdAdapter.setOnItemClickListener(this);

                getChildAt(2).setVisibility(View.VISIBLE);
            }else {
                //仅支持3级
                if(listener!=null){
                    addSelectedList();
                    listener.onSelected(selectedList);
                }
            }
        }else {
            if(adapter == firstAdapter){
                getChildAt(1).setVisibility(View.GONE);
                getChildAt(2).setVisibility(View.GONE);
                if(secondAdapter!=null)
                    secondAdapter.setSelectPosition(-1);
                if(thirdAdapter!=null)
                    thirdAdapter.setSelectPosition(-1);
            }else if(adapter == secondAdapter){
                getChildAt(2).setVisibility(View.GONE);
                if(thirdAdapter!=null)
                    thirdAdapter.setSelectPosition(-1);
            }

            if(listener!=null){
                addSelectedList();
                listener.onSelected(selectedList);
            }
        }

    }

    private void addSelectedList(){
        selectedList.clear();
        selectedList.add(firstAdapter.getData().get(firstAdapter.getSelectPosition()));
        if(secondAdapter!=null&&secondAdapter.getSelectPosition()!=-1)
            selectedList.add(secondAdapter.getData().get(secondAdapter.getSelectPosition()));
        if(thirdAdapter!=null&&thirdAdapter.getSelectPosition()!=-1)
            selectedList.add(thirdAdapter.getData().get(thirdAdapter.getSelectPosition()));
    }

    public void setOnSelectedListener(OnSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnSelectedListener{
        void onSelected(List<IXMenuData> data);
    }
}
