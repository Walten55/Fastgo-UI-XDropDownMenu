package me.walten.fastgo.ui.DropDownMenu;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import me.walten.fastgo.ui.DropDownMenu.bean.IDropDownMenu;
import me.walten.fastgo.ui.DropDownMenu.common.SimpleAnimationListener;
import me.walten.fastgo.ui.DropDownMenu.common.XMaxHeightLayout;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/17 : Create XDropDownMenu.java (Walten);
 * -----------------------------------------------------------------
 */
public class XDropDownMenu extends LinearLayout implements IDropDownMenu, View.OnClickListener {
    //下拉按钮组
    XDropDownButtonGroup mMenuButtonGroup;

    //包裹Pop 和 contentView
    FrameLayout mContainer;

    //下拉框
    FrameLayout mFrame;
    //下拉的内容
    XMaxHeightLayout mDropDownViewContainer;

    //数据内容
    View mContentView;

    //动画效果
    private Animation dismissAnimation;
    private Animation occurAnimation;
    private Animation alphaDismissAnimation;
    private Animation alphaOccurAnimation;

    //当前操作的button
    private XDropDownButton mCurMenuButton;

    //
    FrameLayout.LayoutParams flpContent;

    public XDropDownMenu(Context context) {
        this(context, null);
    }

    public XDropDownMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XDropDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.VERTICAL);
        initAnimation();
    }

    private void initAnimation() {
        occurAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.drop_down_menu_top_in);
        dismissAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.drop_down_menu_top_out);
        dismissAnimation.setAnimationListener(new SimpleAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                mFrame.setVisibility(View.GONE);
            }
        });
        alphaDismissAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_to_zero);
        alphaOccurAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_to_one);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        try {
            mMenuButtonGroup = (XDropDownButtonGroup) getChildAt(0);
        } catch (Exception e) {
            return;
        }

        handleViews();
    }

    private void handleViews() {
        if(getChildCount()>2){
            throw new InflateException("XDropDownMenu must include only XDropDownButtonGroup and ContentView(Any)");
        }else if(getChildCount()==2){
            mContentView = getChildAt(1);
        }else if(getChildCount() == 1){
            mContentView = getChildAt(0);
        }else {
            throw new InflateException("XDropDownMenu must include at least ContentView(Any)");
        }

        removeView(mContentView);
        //
        mContainer = new FrameLayout(getContext());

        //暗色背景层
        mFrame = new FrameLayout(getContext());
        mFrame.setOnClickListener(this);
        mFrame.setVisibility(View.GONE);
        mFrame.setBackgroundColor(Color.parseColor("#7f000000"));

        //下拉内容层
        mDropDownViewContainer = new XMaxHeightLayout(getContext());
        mDropDownViewContainer.setBackgroundColor(Color.parseColor("#F5F5F5"));
        flpContent = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT
                , FrameLayout.LayoutParams.WRAP_CONTENT);
        mFrame.addView(mDropDownViewContainer, flpContent);

        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mContainer.addView(mContentView, flp);
        mContainer.addView(mFrame, flp);

        //加到XDropDownMenu
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mContainer, 1, lp);
    }

    public XDropDownButton[] initTabs(String[] tabs){
        if(tabs == null)
            throw new NullPointerException("tabs is null");

        XDropDownButton[] btns = new XDropDownButton[tabs.length];
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelSize(R.dimen.grid_45));
        mMenuButtonGroup = new XDropDownButtonGroup(getContext());
        mMenuButtonGroup.setBackgroundColor(Color.WHITE);
        addView(mMenuButtonGroup,0,lp);
        int i = 0;
        for(String text : tabs){
            XDropDownButton btn = new XDropDownButton(getContext());
            btn.setMenuText(text);
            btns[i] = btn;
            mMenuButtonGroup.addView(btn);
            i++;
        }
        mMenuButtonGroup.handleChilds();
        handleViews();
        return btns;
    }

    @Override
    public View getDropDownView() {
        if (mCurMenuButton != null)
            return mCurMenuButton.getDropDownView();
        return null;
    }

    @Override
    public void expand(XDropDownButton curMenuButton) {
        if (mFrame == null && mDropDownViewContainer == null)
            return;
        this.mCurMenuButton = curMenuButton;

        mDropDownViewContainer.removeAllViews();
        if (getDropDownView() != null) {
            mDropDownViewContainer.addView(getDropDownView(), flpContent);
        }

        mFrame.setVisibility(View.VISIBLE);
        mFrame.startAnimation(alphaOccurAnimation);
        mDropDownViewContainer.startAnimation(occurAnimation);
    }

    @Override
    public void close() {
        if (mFrame == null && mDropDownViewContainer == null)
            return;
        mCurMenuButton = null;
        mFrame.startAnimation(alphaDismissAnimation);
        mDropDownViewContainer.startAnimation(dismissAnimation);
    }

    @Override
    public void onClick(View view) {
        mMenuButtonGroup.closeAll();
    }

}
