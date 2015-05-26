package com.yamae.yamaeapp;

/**
 * Created by KB Kim on 2015-05-24.
 */

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TabHost;

public class MainActivity2 extends ActionBarActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mainpage);

        getSupportActionBar().setIcon(new ColorDrawable(0x00ffffff));  //아이콘투명
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3f51b5));   //액션바색
        getSupportActionBar().setTitle("야식 of 매지리");


        TabHost mTabHost = (TabHost)findViewById(R.id.mainpage_tab);
      //  mTabHost=getTabHost();

        mTabHost.setup();
        /*TabHost.TabSpec firstTabSpec = mTabHost.newTabSpec("tid1");
        TabHost.TabSpec secondTabSpec = mTabHost.newTabSpec("tid1");
        TabHost.TabSpec thirdTabSpec = mTabHost.newTabSpec("tid1");

        firstTabSpec.setIndicator("",getResources().getDrawable(R.drawable.menu_list_icon));
        secondTabSpec.setIndicator("",getResources().getDrawable(R.drawable.star_icon));
        thirdTabSpec.setIndicator("",getResources().getDrawable(R.drawable. overflow_icon));*/
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1")
                        .setContent(R.id.view1).setIndicator("tab_test1")
        );
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
                        .setContent(R.id.view2).setIndicator("tab_test2")
        );
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3")
                        .setContent(R.id.view3).setIndicator("tab_test3")
        );

    }

}
