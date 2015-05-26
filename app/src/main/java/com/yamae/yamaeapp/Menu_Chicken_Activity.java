package com.yamae.yamaeapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HyunWook Kim on 2014-09-18.
 */
public class Menu_Chicken_Activity extends ActionBarActivity {
    private ArrayList<Menu_All_List_Data> data2 = null;
    String [] restname=new String[100];
    String [] tellnum=new String[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list);

        //ActionBar
        getSupportActionBar().setIcon(new ColorDrawable(0x00ffffff));  //아이콘투명
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3f51b5));   //액션바색
        getSupportActionBar().setTitle("치킨");


        final ListView listview = (ListView) findViewById(R.id.all_list);
        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbar);


        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                data2 = new ArrayList<Menu_All_List_Data>();
                final ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("RestName");
                query.whereContains("Category","2");
                query.addAscendingOrder("RestName");
                query.orderByAscending("RestName");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        int i = 0;
                        for(i=0;i<parseObjects.size();i++) {
                            ParseObject parseObject = parseObjects.get(i);
                            restname[i]=parseObject.getString("RestName");
                            tellnum[i]=parseObject.getString("TelNum");
                            Menu_All_List_Data data=new Menu_All_List_Data(restname[i],tellnum[i]);
                            data2.add(data);

                        }
                        Menu_All_Adapter allAdapter=new Menu_All_Adapter(getBaseContext(),R.layout.all_listview_item,data2);
                        listview.setAdapter(allAdapter);
                    }
                });
            }
        });
        thread.start();


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detail1=new Intent(Menu_Chicken_Activity.this, TestActivity.class);
                detail1.putExtra("restname",restname[position]);
                detail1.putExtra("tellnum",tellnum[position]);
                startActivity(detail1);
            }
        });
    }
}
