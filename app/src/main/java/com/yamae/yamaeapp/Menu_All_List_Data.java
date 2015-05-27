package com.yamae.yamaeapp;

/**
 * Created by HyunWook Kim on 2014-09-18.
 */
public class Menu_All_List_Data {

    private String name;
    private String num;
    private int restNum;

    public String getName(){
            return name;
        }
    public String getNum() { return num; }
    public int getRestNum() { return restNum;}


    public Menu_All_List_Data(String n, String b,int c){
        this.name=n; this.num=b; this.restNum=c;
    }

}
