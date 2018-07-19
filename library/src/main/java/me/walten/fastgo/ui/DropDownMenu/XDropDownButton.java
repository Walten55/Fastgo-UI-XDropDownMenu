package me.walten.fastgo.ui.DropDownMenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.walten.fastgo.ui.DropDownMenu.bean.IDropDownMenu;


/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/16 : Create XDropDownButton.java (Walten);
 * -----------------------------------------------------------------
 */
public class XDropDownButton extends RelativeLayout implements View.OnClickListener,IDropDownMenu {

    private Drawable arrowIconUp;

    private Drawable arrowIconDown;

    private String menuText;

    private float menuTextSize;

    private int menuTextColor;

    private int focusColor;

    private TextView menuView;

    private boolean isExpand;

    private boolean isSetArrowUp;
    private boolean isSetArrowDown;

    private View mDropDownView;

    public XDropDownButton(Context context) {
        this(context, null);
    }

    public XDropDownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XDropDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.XDropDownButton);
            arrowIconUp = a.getDrawable(R.styleable.XDropDownButton_arrow_icon_up);
            arrowIconDown = a.getDrawable(R.styleable.XDropDownButton_arrow_icon_down);
            isSetArrowUp = arrowIconUp!=null;
            isSetArrowDown = arrowIconDown!=null;
            menuText = a.getString(R.styleable.XDropDownButton_menu_text);
            menuTextSize = a.getDimension(R.styleable.XDropDownButton_menu_text_size,
                    getResources().getDimensionPixelSize(R.dimen.grid_15));
            menuTextColor = a.getColor(R.styleable.XDropDownButton_menu_text_color,
                    ContextCompat.getColor(getContext(), R.color.fastgo_text_gray));
            focusColor = a.getColor(R.styleable.XDropDownButton_focus_color,
                    ContextCompat.getColor(getContext(), R.color.colorPrimary));
            a.recycle();
        }else {
            menuTextSize = getResources().getDimensionPixelSize(R.dimen.grid_15);
            menuTextColor = ContextCompat.getColor(getContext(), R.color.fastgo_text_gray);
            focusColor =    ContextCompat.getColor(getContext(), R.color.colorPrimary);
        }

        menuView = new TextView(getContext());
        setViewStatus();

        LayoutParams lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        menuView.setLayoutParams(lp);
        addView(menuView, lp);

        setPadding(getResources().getDimensionPixelSize(R.dimen.grid_6),
                getResources().getDimensionPixelSize(R.dimen.grid_4),
                getResources().getDimensionPixelSize(R.dimen.grid_6),
                getResources().getDimensionPixelSize(R.dimen.grid_4));

        setOnClickListener(this);
    }

    private void setViewStatus() {
        menuView.setGravity(Gravity.CENTER);
        menuView.setSingleLine();
        menuView.setMaxLines(1);
        menuView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        menuView.setSelected(true);
        menuView.setText(menuText);
        menuView.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
        menuView.setTextColor(isExpand ? focusColor : menuTextColor);
        setDrawableRight();
    }

    private void setDrawableRight() {
        if(!isSetArrowUp&&isExpand)
            arrowIconUp = getAutoColorArrow(R.drawable.common_icon_up);
        if(!isSetArrowDown&&!isExpand)
            arrowIconDown = getAutoColorArrow(R.drawable.common_icon_down);

        Drawable drawable = isExpand ? arrowIconUp : arrowIconDown;
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menuView.setCompoundDrawablePadding(5);
        menuView.setCompoundDrawables(null, null, drawable, null);
        menuView.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.grid_5));
    }

    public void setArrowIconUp(Drawable arrowIconUp) {
        this.arrowIconUp = arrowIconUp;
        setViewStatus();
    }

    public void setArrowIconDown(Drawable arrowIconDown) {
        this.arrowIconDown = arrowIconDown;
        setViewStatus();
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
        setViewStatus();
    }

    public void setMenuTextSize(float menuTextSize) {
        this.menuTextSize = menuTextSize;
        setViewStatus();
    }

    public void setMenuTextColor(int menuTextColor) {
        this.menuTextColor = menuTextColor;
        setViewStatus();
    }

    public Drawable getArrowIconUp() {
        return arrowIconUp;
    }

    public Drawable getArrowIconDown() {
        return arrowIconDown;
    }

    public String getMenuText() {
        return menuText;
    }

    public float getMenuTextSize() {
        return menuTextSize;
    }

    public int getMenuTextColor() {
        return menuTextColor;
    }

    public int getFocusColor() {
        return focusColor;
    }

    public boolean isExpand() {
        return isExpand;
    }

    private Drawable getAutoColorArrow(int arrow) {
        Drawable drawable = getDrawable(arrow);
        drawable.setColorFilter(new PorterDuffColorFilter(isExpand ? focusColor : menuTextColor, PorterDuff.Mode.SRC_IN));
        return drawable;
    }

    private final Drawable getDrawable(@DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return getContext().getDrawable(id);
        else
            return getContext().getResources().getDrawable(id);
    }

    @Override
    public void onClick(View view) {
        if (!isExpand) {
            expand(this);
        }else {
            close();
        }
    }

    public void setDropDownView(View dropDownView) {
        this.mDropDownView = dropDownView;
    }

    @Override
    public View getDropDownView() {
        return mDropDownView;
    }

    public void expand(XDropDownButton curMenuButton){
        if(isExpand)
            return;

        isExpand = true;
        setViewStatus();

        if(getParent() instanceof XDropDownButtonGroup){
            XDropDownButtonGroup group = (XDropDownButtonGroup) getParent();
            group.setFocusDropMenuButton(this);

            if(group.getParent() instanceof XDropDownMenu){
                XDropDownMenu menu = (XDropDownMenu) group.getParent();
                menu.expand(this);
            }
        }
    }

    public void close(){
        if(!isExpand)
            return;
        isExpand = false;
        setViewStatus();

        if(getParent().getParent() instanceof XDropDownMenu){
            XDropDownMenu menu = (XDropDownMenu) getParent().getParent();
            menu.close();
        }
    }

}
