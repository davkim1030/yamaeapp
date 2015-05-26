package com.yamae.yamaeapp;

/**
 * Created by KB Kim on 2015-05-24.
 */

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TabHost;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        getSupportActionBar().setIcon(new ColorDrawable(0x00ffffff));  //아이콘투명
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3f51b5));   //액션바색
        getSupportActionBar().setTitle("야식 of 매지리");


        TabHost mTabHost = (TabHost)findViewById(R.id.mainpage_tab);
      //  mTabHost=getTabHost();

        mTabHost.setup();
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1")
                        .setContent(R.id.view1).setIndicator("tab_test1")
        );
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
                        .setContent(R.id.view2).setIndicator("tab_test2")
        );
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3")
                        .setContent(R.id.view3).setIndicator("tab_test3")
        );

        ListView listView=(ListView)findViewById(R.id.category_list);
        ArrayList<Category_listview_item> data=new ArrayList<Category_listview_item>();
        Category_listview_item all=new Category_listview_item(R.drawable.all_icon,"모든메뉴");
        Category_listview_item dinning=new Category_listview_item(R.drawable.dinning_icon,"식당");
        Category_listview_item chicken=new Category_listview_item(R.drawable.chicken_icon,"치킨");
        Category_listview_item night=new Category_listview_item(R.drawable.night_icon,"야식");
        Category_listview_item drink=new Category_listview_item(R.drawable.drink_icon,"술집");
        Category_listview_item etc=new Category_listview_item(R.drawable.etc_icon,"기타");

        data.add(all);
        data.add(dinning);
        data.add(chicken);
        data.add(night);
        data.add(drink);
        data.add(etc);

        Category_listview_Adapter adapter = new Category_listview_Adapter(this,R.layout.category_listview_item,data);
        listView.setAdapter(adapter);

    }

}
