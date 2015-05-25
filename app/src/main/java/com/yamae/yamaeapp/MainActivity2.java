package com.yamae.yamaeapp;

/**
 * Created by KB Kim on 2015-05-24.
 */

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TabHost;

public class MainActivity2 extends ActionBarActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

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

    }

}
