package me.walten.fastgo.ui.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import me.walten.fastgo.ui.DropDownMenu.DropDownView.XMultiRecyclerView;
import me.walten.fastgo.ui.DropDownMenu.XDropDownButton;
import me.walten.fastgo.ui.DropDownMenu.XDropDownMenu;
import me.walten.fastgo.ui.DropDownMenu.bean.IXMenuData;
import me.walten.fastgo.ui.DropDownMenu.helper.XDropDownMenuHelper;

public class DefaultStyleActivity extends AppCompatActivity {
    private XDropDownMenu mXDropDownMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_style);

        mXDropDownMenu = findViewById(R.id.drop_down_menu);
        final XDropDownButton[] xDropDownButtons = mXDropDownMenu.initTabs(new String[]{"城市", "省市区", "省市", "自定义布局"});


        XDropDownMenuHelper.initDataForSingleRecyclerView(this,
                xDropDownButtons[0],
                DataHelper.getData()
                , new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    }
                });


        XDropDownMenuHelper.initDataForMultiRecyclerView(this, xDropDownButtons[1], DataHelper.getData3(), new XMultiRecyclerView.OnSelectedListener() {
            @Override
            public void onSelected(List<IXMenuData> data) {
                if ("不限".equals(data.get(0).getItemText())) {
                    xDropDownButtons[1].setMenuText("省市区");
                } else {
                    for (int i = data.size() - 1; i >= 0; i--) {
                        if (!"不限".equals(data.get(i).getItemText())) {
                            xDropDownButtons[1].setMenuText(data.get(i).getItemText());
                            break;
                        }
                    }
                }
            }
        });


        XDropDownMenuHelper.initDataForMultiRecyclerView(this, xDropDownButtons[2], DataHelper.getData2(), new XMultiRecyclerView.OnSelectedListener() {
            @Override
            public void onSelected(List<IXMenuData> data) {

            }
        });

        TextView tv = new TextView(this);
        tv.setText("我是自定义布局");
        xDropDownButtons[3].setDropDownView(tv);
    }
}
