package com.yamae.yamaeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class DetailpageActivity extends AppCompatActivity {
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
        final String tellnum=intent.getStringExtra("tellnum");
        final boolean[] isdelivery = {true};
        final boolean[] iscar = {true};

        final ArrayList<Detail_listview_item> data2 = new ArrayList<>();

        final String[] time_str = new String[1];


        setContentView(R.layout.activity_detailpage);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(restname);

        Button callButton=(Button)findViewById(R.id.callBut);
        TextView callText=(TextView)findViewById(R.id.callText);

        final TextView time=(TextView)findViewById(R.id.Time);
        final TextView isDelivery=(TextView)findViewById(R.id.isDelivery);
        final TextView isCar=(TextView)findViewById(R.id.isCar);
        final ListView detail_list=(ListView)findViewById(R.id.detail_list);
        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbar);

        progressBar.setVisibility(ProgressBar.VISIBLE);

        callText.setText(tellnum);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(tellnum));
                callIntent.setData(Uri.parse("tel:"+tellnum));
                startActivity(callIntent);
            }
        });

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
                Detail_listview_Adapter allAdapter=new Detail_listview_Adapter(getBaseContext(),R.layout.item_detail_listview,data2);
                detail_list.setAdapter(allAdapter);
                progressBar.setVisibility(ProgressBar.GONE);
                if(i<2){
                    Toast TM= Toast.makeText(getApplicationContext(),"메뉴 정보가 없습니다", Toast.LENGTH_SHORT);
                    TM.show();}
            }
        });

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
