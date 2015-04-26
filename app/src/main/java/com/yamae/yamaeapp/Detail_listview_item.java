package com.yamae.yamaeapp;

import android.widget.TextView;

/**
 * Created by songmho on 2014-11-03.
 */
public class Detail_listview_item {
    private String name;
    private String price;
    private String timed;
    private String deliver;
    private String car;
    private String isTest;

    public Detail_listview_item(String name,String price){
        this.name=name;
        this.price= price;

        ///this.timed=timed;
        ///this.deliver=deliver;
        ///this.car=car;

    }
    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }
    //public String getTimed() { return timed; }
    //public String getDeliver() { return deliver; }
    //public String getCar() { return car; }
}
