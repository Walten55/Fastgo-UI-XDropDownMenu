package me.walten.fastgo.ui.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import me.walten.fastgo.ui.DropDownMenu.DropDownView.XMultiRecyclerView;
import me.walten.fastgo.ui.DropDownMenu.XDropDownButton;
import me.walten.fastgo.ui.DropDownMenu.bean.IXMenuData;
import me.walten.fastgo.ui.DropDownMenu.helper.XDropDownMenuHelper;

public class CustomStyleActivity extends AppCompatActivity {
    private XDropDownButton btn1;
    private XDropDownButton btn2;
    private XDropDownButton btn3;
    private XDropDownButton btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_style);

        btn1 = findViewById(R.id.drop_down_btn1);
        btn2 = findViewById(R.id.drop_down_btn2);
        btn3 = findViewById(R.id.drop_down_btn3);
        btn4 = findViewById(R.id.drop_down_btn4);

        XDropDownMenuHelper.initDataForSingleRecyclerView(this,
                btn1,
                DataHelper.getData()
                , new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    }
                });



        XDropDownMenuHelper.initDataForMultiRecyclerView(this, btn2, DataHelper.getData3(), new XMultiRecyclerView.OnSelectedListener() {
            @Override
            public void onSelected(List<IXMenuData> data) {
                if("不限".equals(data.get(0).getItemText())){
                    btn2.setMenuText("省市区");
                }else {
                    for(int i=data.size()-1;i>=0;i--){
                        if(!"不限".equals(data.get(i).getItemText())){
                            btn2.setMenuText(data.get(i).getItemText());
                            break;
                        }
                    }
                }
            }
        });


        XDropDownMenuHelper.initDataForMultiRecyclerView(this, btn3, DataHelper.getData2(), new XMultiRecyclerView.OnSelectedListener() {
            @Override
            public void onSelected(List<IXMenuData> data) {

            }
        });

        TextView tv = new TextView(this);
        tv.setText("我是自定义布局");
        btn4.setDropDownView(tv);
    }
}
