package com.yamae.yamaeapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by HyunWook Kim on 2014-09-18.
 */
public class Menu_info_Activity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);
        PackageInfo packageInfo=null;
        //Typeface font_hanna=Typeface.createFromAsset(getAssets(),"bm_hanna.ttf");

        //ActionBar
        getSupportActionBar().setIcon(new ColorDrawable(0x00ffffff));  //아이콘투명
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff4ea99d));   //액션바색
        getSupportActionBar().setTitle("어플 정보");

        TextView cur_ver_name=(TextView)findViewById(R.id.ver);
        try {
            packageInfo=getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version=packageInfo.versionName;
        cur_ver_name.setText("v"+version);
    }


}