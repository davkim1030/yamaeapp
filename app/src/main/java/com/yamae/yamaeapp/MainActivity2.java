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

    //private ArrayList<Category_listview_item> data2 = null;
    String [] catename=new String[100];
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


        final ListView listview = (ListView) findViewById(R.id.category_list);
        //data2 = new ArrayList<Category_listview_item>();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                catename[0]="모든 메뉴";
                catename[1]="식사";
                catename[2]="치킨";
                catename[3]="야식";
                catename[4]="술집";
                catename[5]="기타";

            }
        });
        thread.start();


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detail1=new Intent(MainActivity2.this, Menu_All_Activity.class);
                startActivity(detail1);
                Intent detail2=new Intent(MainActivity2.this, Menu_Dinning_Activity.class);
                startActivity(detail2);
            }
        });
    }





}
