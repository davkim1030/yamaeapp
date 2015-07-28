package com.yamae.yamaeapp;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    String search_res_name =null;

    Toolbar toolbar;
    ProgressBar progressBar;
    ListView listview;

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

        data2 = new ArrayList<>();

        listview = (ListView) findViewById(R.id.all_list);

        progressBar=(ProgressBar)findViewById(R.id.progressbar);


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

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(ProgressBar.VISIBLE);
        data2.clear();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                final ParseQuery<ParseObject> query=new ParseQuery<>("RestName");
                if(category!=0)
                    query.whereContains("Category",String.valueOf(category));
                if(search_res_name !=null)
                    query.whereContains("RestName",search_res_name);
                query.addAscendingOrder("RestName");
                query.orderByAscending("RestName");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        if(search_res_name!=null && parseObjects.size()==0)
                            Toast.makeText(getApplicationContext(),"식당이 없습니다.",Toast.LENGTH_SHORT).show();
                        for(int i=0;i<parseObjects.size();i++) {
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
                        search_res_name=null;
                    }
                });
            }
        });
        thread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchManager searchManager=(SearchManager)Menu_All_Activity.this.getSystemService(SEARCH_SERVICE);
        SearchView searchView=null;
        if(searchItem!=null){
            searchView=(android.support.v7.widget.SearchView)searchItem.getActionView();
        }
        if(searchView!=null){
            searchView.setQueryHint("가게 이름");
            searchView.setSearchableInfo(searchManager.getSearchableInfo(Menu_All_Activity.this.getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.d("query", query);
                    search_res_name = query;
                    onResume();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.d("newText", newText);
                    return false;
                }
            });

            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    search_res_name = null;
                    onResume();
                    return false;
                }
            });
        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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