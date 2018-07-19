package me.walten.fastgo.ui.DropDownMenu.helper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import me.walten.fastgo.ui.DropDownMenu.DropDownView.XMultiRecyclerView;
import me.walten.fastgo.ui.DropDownMenu.XDropDownButton;
import me.walten.fastgo.ui.DropDownMenu.bean.IXMenuData;
import me.walten.fastgo.ui.DropDownMenu.defaultAdapter.MultiRecyclerViewAdapter;
import me.walten.fastgo.ui.DropDownMenu.defaultAdapter.StringForSingleRecyclerViewAdapter;
import me.walten.fastgo.ui.DropDownMenu.defaultAdapter.TForSingleRecyclerViewAdapter;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/18 : Create XSingleRecyclerView.java (Walten);
 * -----------------------------------------------------------------
 */
public class XDropDownMenuHelper {

    public static StringForSingleRecyclerViewAdapter initDataForSingleRecyclerView(Context context, final XDropDownButton btn, String[] array, int selectPosition, final BaseQuickAdapter.OnItemClickListener listener) {
        RecyclerView view = new RecyclerView(context);
        view.setLayoutManager(new LinearLayoutManager(context));
        final StringForSingleRecyclerViewAdapter adapter = new StringForSingleRecyclerViewAdapter(array);
        adapter.setSelectPosition(selectPosition);
        adapter.setSelectColor(btn.getFocusColor());
        view.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapterInside, View view, int position) {
                btn.setMenuText((String) adapterInside.getItem(position));
                btn.close();
                adapter.setSelectPosition(position);

                if (listener != null)
                    listener.onItemClick(adapterInside, view, position);
            }
        });
        btn.setDropDownView(view);
        return adapter;
    }

    public static StringForSingleRecyclerViewAdapter initDataForSingleRecyclerView(Context context, final XDropDownButton btn, String[] array, BaseQuickAdapter.OnItemClickListener listener) {
        return initDataForSingleRecyclerView(context, btn, array, 0, listener);
    }

    public static TForSingleRecyclerViewAdapter initDataForSingleRecyclerView(Context context, final XDropDownButton btn, List<IXMenuData> list, int selectPosition, final BaseQuickAdapter.OnItemClickListener listener) {
        RecyclerView view = new RecyclerView(context);
        view.setLayoutManager(new LinearLayoutManager(context));
        final TForSingleRecyclerViewAdapter adapter = new TForSingleRecyclerViewAdapter(list);
        adapter.setSelectPosition(selectPosition);
        adapter.setSelectColor(btn.getFocusColor());
        view.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapterInside, View view, int position) {
                btn.setMenuText(((IXMenuData) adapterInside.getItem(position)).getItemText());
                btn.close();
                adapter.setSelectPosition(position);

                if (listener != null)
                    listener.onItemClick(adapterInside, view, position);
            }
        });
        btn.setDropDownView(view);
        return adapter;
    }

    public static TForSingleRecyclerViewAdapter initDataForSingleRecyclerView(Context context, final XDropDownButton btn, List<IXMenuData> list, BaseQuickAdapter.OnItemClickListener listener) {
        return initDataForSingleRecyclerView(context, btn, list, 0, listener);
    }

    public static MultiRecyclerViewAdapter initDataForMultiRecyclerView(Context context, final XDropDownButton btn, final List<IXMenuData> list, final XMultiRecyclerView.OnSelectedListener listener){
        XMultiRecyclerView view = new XMultiRecyclerView(context);
        final MultiRecyclerViewAdapter adapter = new MultiRecyclerViewAdapter(list);
        adapter.setSelectColor(btn.getFocusColor());
        view.setAdapter(adapter);
        view.setOnSelectedListener(new XMultiRecyclerView.OnSelectedListener() {
            @Override
            public void onSelected(List<IXMenuData> data) {
                if(data.size()>0)
                    btn.setMenuText(data.get(data.size()-1).getItemText());
                btn.close();
                if(listener!=null)
                    listener.onSelected(data);
            }
        });
        btn.setDropDownView(view);
        return adapter;
    }
}
