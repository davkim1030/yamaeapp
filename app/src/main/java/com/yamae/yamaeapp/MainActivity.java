package com.yamae.yamaeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button_all);
        Button button2 = (Button) findViewById(R.id.button_dinning);
        Button button3 = (Button) findViewById(R.id.button_chicken);
        Button button4 = (Button) findViewById(R.id.button_night);
        Button button5 = (Button) findViewById(R.id.button_drink);
        Button button6 = (Button) findViewById(R.id.button_info);
        Button button7 = (Button) findViewById(R.id.button_etc);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent menu_all = new Intent(MainActivity.this, Menu_All_Activity.class);
                startActivity(menu_all);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent menu_dinning = new Intent(MainActivity.this, Menu_Dinning_Activity.class);
                startActivity(menu_dinning);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent menu_chicken = new Intent(MainActivity.this, Menu_Chicken_Activity.class);
                startActivity(menu_chicken);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent menu_night = new Intent(MainActivity.this, Menu_Night_Activity.class);
                startActivity(menu_night);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent menu_drink = new Intent(MainActivity.this, Menu_Drink_Activity.class);
                startActivity(menu_drink);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent menu_info = new Intent(MainActivity.this, Menu_info_Activity.class);
                startActivity(menu_info);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent main2 = new Intent(MainActivity.this, ETC_Activity.class);
                startActivity(main2);
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
