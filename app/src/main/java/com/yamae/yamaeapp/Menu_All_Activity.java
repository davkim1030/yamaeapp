package com.yamae.yamaeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import com.google.analytics.tracking.android.EasyTracker;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;


/**
 * Created by HyunWook Kim on 2014-09-18.
 */
public class Menu_All_Activity extends AppCompatActivity {

    private ArrayList<Menu_All_List_Data> data2 = null;
    String [] restname=new String[100];
    String [] tellnum=new String[100];
    int restnum=0;
    int category;

    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list);
        //ActionBar

        Intent intent=getIntent();
        category=intent.getIntExtra("category",0);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setActionbarTitle();        //분류별로 액션바 타이틀 지정

        final ListView listview = (ListView) findViewById(R.id.all_list);

        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbar);
        progressBar.setVisibility(ProgressBar.VISIBLE);


        data2 = new ArrayList<>();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                final ParseQuery<ParseObject> query=new ParseQuery<>("RestName");
                if(category!=0)
                    query.whereContains("Category",String.valueOf(category));
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


                        Menu_All_Adapter allAdapter=new Menu_All_Adapter(getBaseContext(),R.layout.item_all_listview,data2);
                        listview.setAdapter(allAdapter);
                        progressBar.setVisibility(ProgressBar.GONE);
                    }
                });
            }
        });
        thread.start();


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detail1=new Intent(Menu_All_Activity.this, DetailpageActivity.class);
                detail1.putExtra("restname",restname[position]);
                detail1.putExtra("tellnum",tellnum[position]);
                startActivity(detail1);
            }
        });
    }

    private void setActionbarTitle() {
        switch (category) {
            case 0:
               getSupportActionBar().setTitle("모든메뉴");
            break;
            case 1:
                getSupportActionBar().setTitle("식당");
                break;
            case 2:
                getSupportActionBar().setTitle("치킨");
                break;
            case 3:
                getSupportActionBar().setTitle("야식");
                break;
            case 4:
                getSupportActionBar().setTitle("술집");
                break;
            case 5:
                getSupportActionBar().setTitle("기타");
                break;

        }
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