package com.yamae.yamaeapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KB Kim on 2015-04-26.
 */
public class TestActivity extends ActionBarActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        final String []menuPrice=new String[100];
        final String []menuName=new String[100];
        String restname=intent.getStringExtra("restname");
        String tellnum=intent.getStringExtra("tellnum");
        int storenum = 0;
        final boolean[] isdelivery = {true};
        final boolean[] iscar = {true};

        final ArrayList<Detail_listview_item> data2 = new ArrayList<Detail_listview_item>();

        final String[] time_str = new String[1];
        getSupportActionBar().setIcon(new ColorDrawable(0x00ffffff));  //아이콘투명
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3f51b5));   //액션바색
        getSupportActionBar().setTitle(restname);
        setContentView(R.layout.activity_test);

        TextView storeTel=(TextView)findViewById(R.id.callButton);
        final TextView time=(TextView)findViewById(R.id.Time);
        final TextView isDelivery=(TextView)findViewById(R.id.isDelivery);
        final TextView isCar=(TextView)findViewById(R.id.isCar);
        final ListView detail_list=(ListView)findViewById(R.id.detail_lsit);

        storeTel.setText(tellnum);

        ParseQuery<ParseObject> tell=ParseQuery.getQuery("RestName");
        tell.whereContains("RestName",restname);
        tell.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                ParseObject parseObject=parseObjects.get(0);
                time_str[0] =parseObject.getString("Time");
                iscar[0] =parseObject.getBoolean("Car");
                isdelivery[0] =parseObject.getBoolean("Delivery");
                if(!iscar[0])
                    isCar.setText("차운행안함");
                else
                    isCar.setText("차운행함");

                if(!isdelivery[0])
                    isDelivery.setText("배달안함");
                else
                    isDelivery.setText("배달함");
                time.setText(time_str[0]);
            }
        });

        ParseQuery<ParseObject> menu=ParseQuery.getQuery("MenuList1");
        menu.whereContains("StoreNum_str",restname);
        menu.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                int i;
                for(i=0;i<parseObjects.size();i++) {
                    ParseObject objects = parseObjects.get(i);
                    menuPrice[i]=objects.getString("MenuPrice");
                    menuName[i]=objects.getString("MenuName");
                    Detail_listview_item detail_listview_item=new Detail_listview_item(menuName[i],menuPrice[i]);
                    data2.add(detail_listview_item);
                }
                Detail_listview_Adapter allAdapter=new Detail_listview_Adapter(getBaseContext(),R.layout.detail_listview_item,data2);
                detail_list.setAdapter(allAdapter);
            }
        });

    }


}
