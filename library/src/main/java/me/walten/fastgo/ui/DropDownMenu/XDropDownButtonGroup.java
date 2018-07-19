package me.walten.fastgo.ui.DropDownMenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/17 : Create XDropDownMenuButtonGroup.java (Walten);
 * -----------------------------------------------------------------
 */
public class XDropDownButtonGroup extends LinearLayout {
    private Paint mPaint;

    private int cuttingLineColor = Color.parseColor("#e1e1e1");

    private int bottomLineColor = Color.parseColor("#e1e1e1");

    private List<View> views = new ArrayList<>();

    public XDropDownButtonGroup(Context context) {
        this(context,null);
    }

    public XDropDownButtonGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XDropDownButtonGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.XDropDownButtonGroup);
            cuttingLineColor = a.getColor(R.styleable.XDropDownButtonGroup_cutting_line_color,
                    ContextCompat.getColor(getContext(), R.color.line));
            bottomLineColor = a.getColor(R.styleable.XDropDownButtonGroup_bottom_line,
                    ContextCompat.getColor(getContext(), R.color.line));
            a.recycle();
        }

        setWillNotDraw(false);

        setOrientation(LinearLayout.HORIZONTAL);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(bottomLineColor);
        mPaint.setStrokeWidth(2);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        handleChilds();
    }

    public void handleChilds(){
        for(int i = 0;i<getChildCount();i++){
            if(getChildAt(i) instanceof XDropDownButton){
                views.add(getChildAt(i));
            }
        }
        removeAllViews();

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        lp.weight = 1;

        LayoutParams lineLp = new LayoutParams(1,LayoutParams.MATCH_PARENT);
        lineLp.setMargins(0,getResources().getDimensionPixelSize(R.dimen.grid_10),0,getResources().getDimensionPixelSize(R.dimen.grid_10));
        for(int i = 0;i<views.size();i++){
            addView(views.get(i),lp);

            if(i!=views.size()-1){
                View line = new View(getContext());
                line.setBackgroundColor(cuttingLineColor);
                addView(line,lineLp);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0,getHeight(),getWidth(),getHeight(),mPaint);
    }

    public void setFocusDropMenuButton(XDropDownButton button){
        for(int i = 0; i < getChildCount();i++){
            if(getChildAt(i) instanceof XDropDownButton){
                XDropDownButton temp = (XDropDownButton) getChildAt(i);
                if(temp != button){
                    temp.close();
                }
            }
        }
    }

    public void closeAll(){
        for(int i = 0; i < getChildCount();i++){
            if(getChildAt(i) instanceof XDropDownButton){
                XDropDownButton temp = (XDropDownButton) getChildAt(i);
                temp.close();
            }
        }
    }

    public int getCuttingLineColor() {
        return cuttingLineColor;
    }

    public void setCuttingLineColor(int cuttingLineColor) {
        this.cuttingLineColor = cuttingLineColor;
        handleChilds();
    }

    public int getBottomLineColor() {
        return bottomLineColor;
    }

    public void setBottomLineColor(int bottomLineColor) {
        this.bottomLineColor = bottomLineColor;
        invalidate();
    }
}
