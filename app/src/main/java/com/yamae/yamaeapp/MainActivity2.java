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
import android.widget.ImageView;
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
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3f51b5));   //액션바색
        getSupportActionBar().setTitle("야식 of 매지리");


        TabHost mTabHost = (TabHost)findViewById(R.id.mainpage_tab);

//        ImageView tabwidget01 = new ImageView(this);
//        tabwidget01.setImageResource(R.drawable.menu_list_icon);
//        ImageView tabwidget02 = new ImageView(this);
//        tabwidget02.setImageResource(R.drawable.star_icon);
//        ImageView tabwidget03 = new ImageView(this);
//        tabwidget03.setImageResource(R.drawable.overflow_icon);



        mTabHost.setup();
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1")
                        .setContent(R.id.view1).setIndicator("",getResources().getDrawable(R.drawable.menu_list_icon))

        );
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
                        .setContent(R.id.view2).setIndicator("",getResources().getDrawable(R.drawable.star_icon))
        );
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3")
                        .setContent(R.id.view3).setIndicator("",getResources().getDrawable(R.drawable.overflow_icon))
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity2.this, Menu_All_Activity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity2.this, Menu_Dinning_Activity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity2.this, Menu_Chicken_Activity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity2.this, Menu_Night_Activity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity2.this, Menu_Drink_Activity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity2.this, ETC_Activity.class));
                        break;
                }
            }
        });






        ListView list_view=(ListView)findViewById(R.id.more_list);
        ArrayList<More_listview_item> data2=new ArrayList<More_listview_item>();
        More_listview_item send=new More_listview_item(0,"정보 수정사항 보내기");
        More_listview_item ver=new More_listview_item(1,"버젼 v1.0.0");
        More_listview_item update=new More_listview_item(2,"2015.05.27 업데이트");

        data2.add(send);
        data2.add(ver);
        data2.add(update);

        More_listview_Adapter adapter2 = new More_listview_Adapter(this,R.layout.more_listview_item,data2);
        list_view.setAdapter(adapter2);


    }

}
