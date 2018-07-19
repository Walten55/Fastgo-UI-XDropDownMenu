package me.walten.fastgo.ui.demo;

import java.util.ArrayList;
import java.util.List;

import me.walten.fastgo.ui.DropDownMenu.bean.IXMenuData;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/19 : Create DataHelper.java (Walten);
 * -----------------------------------------------------------------
 */
public class DataHelper {

    public static List<IXMenuData> getData3(){
        List<IXMenuData> data = new ArrayList<>();
        TestFirst noFilter = new TestFirst();
        noFilter.setName("不限");
        data.add(noFilter);
        for (int i = 0;i<7;i++){
            List<TestSecond> dataSecond = new ArrayList<>();
            TestFirst first = new TestFirst();
            first.setName("省"+(i+1));
            first.setData(dataSecond);
            data.add(first);

            TestSecond noFilter2 = new TestSecond();
            noFilter2.setName("不限");
            dataSecond.add(noFilter2);
            for (int j = 0;j<8;j++){
                List<TestThird> dataThird = new ArrayList<>();
                TestSecond second = new TestSecond();
                second.setName("市"+(j+1));
                second.setData(dataThird);
                dataSecond.add(second);

                TestThird noFilter3 = new TestThird();
                noFilter3.setName("不限");
                dataThird.add(noFilter3);
                for (int k = 0;k<9;k++){

                    TestThird third = new TestThird();
                    third.setName("区"+(k+1));
                    dataThird.add(third);
                }

            }
        }

        return data;
    }

    public static List<IXMenuData> getData2(){
        List<IXMenuData> data = new ArrayList<>();
        TestFirst noFilter = new TestFirst();
        noFilter.setName("不限");
        data.add(noFilter);
        for (int i = 0;i<7;i++){
            List<TestSecond> dataSecond = new ArrayList<>();
            TestFirst first = new TestFirst();
            first.setName("省"+(i+1));
            first.setData(dataSecond);
            data.add(first);

            TestSecond noFilter2 = new TestSecond();
            noFilter2.setName("不限");
            dataSecond.add(noFilter2);
            for (int j = 0;j<8;j++){
                TestSecond second = new TestSecond();
                second.setName("市"+(j+1));
                dataSecond.add(second);
            }
        }
        return data;
    }

    public static String[] getData(){
        return  new String[]{"厦门", "北京", "上海"};
    }
}
