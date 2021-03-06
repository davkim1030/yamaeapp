package com.yamae.yamaeapp;

/**
 * Created by KB Kim on 2015-05-24.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.analytics.tracking.android.EasyTracker;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("야식 of 매지리");

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
        ViewPagerAdapter viewpageradapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(viewpageradapter);
        tabLayout.setupWithViewPager(viewPager);

   /*     tabLayout.getTabAt(0).setIcon(R.drawable.menu_list_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.star_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.overflow_icon);*/
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
    }
    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }

}
