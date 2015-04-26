package com.yamae.yamaeapp;

/**
 * Created by HyunWook Kim on 2014-09-18.
 */
public class Menu_All_List_Data {

    private String name;
    private String num;

    public String getName(){
            return name;
        }
    public String getNum() { return num; }


    public Menu_All_List_Data(String n, String b){
        this.name=n; this.num=b;
    }

}
