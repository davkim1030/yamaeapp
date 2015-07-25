package com.yamae.yamaeapp;

/**
 * Created by KB Kim on 2015-05-27.
 */
public class Category_listview_item {

    private String name;
    private int id;


    public Category_listview_item(int id, String name){
        this.name=name;
        this.id=id;

    }
    public String getName(){
        return name;
    }
    public int getImage() { return id; }

}
