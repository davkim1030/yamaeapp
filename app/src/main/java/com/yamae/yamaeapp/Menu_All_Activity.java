package com.yamae.yamaeapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;


/**
 * Created by HyunWook Kim on 2014-09-18.
 */
public class Menu_All_Activity extends ActionBarActivity  {

    private ArrayList<Menu_All_List_Data> data2 = null;
    String [] restname=new String[100];
    String [] tellnum=new String[100];
    int restnum=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list);
        //ActionBar
        getSupportActionBar().setIcon(new ColorDrawable(0x00ffffff));  //아이콘투명
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3f51b5));   //액션바색
        getSupportActionBar().setTitle("모든메뉴");

        final ListView listview = (ListView) findViewById(R.id.all_list);

        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbar);
//        progressBar.setVisibility(View.VISIBLE);


        //final int[] temp = new int[1];

        data2 = new ArrayList<Menu_All_List_Data>();
        //Menu_All_List_Data random=new Menu_All_List_Data("랜덤",tellnum[1],1);

        //data2.add(random);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                final ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("RestName");
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
                            restnum=parseObject.getInt("Num");
                            Menu_All_List_Data data=new Menu_All_List_Data(restname[i],tellnum[i],restnum);
                            data2.add(data);
                            //temp[0] = parseObjects.size();

                        }


                        Menu_All_Adapter allAdapter=new Menu_All_Adapter(getBaseContext(),R.layout.all_listview_item,data2);
                        listview.setAdapter(allAdapter);
//                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
        thread.start();


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                double rand  = Math.random();
                //int randn = (int) (rand*temp[0])+1;

                //Menu_All_List_Data random=new Menu_All_List_Data(restname[randn],tellnum[randn],randn);

                //data2.add(random);

                Intent detail1=new Intent(Menu_All_Activity.this, TestActivity.class);
                detail1.putExtra("restname",restname[position]);
                detail1.putExtra("tellnum",tellnum[position]);
                startActivity(detail1);
            }
        });
    }




}