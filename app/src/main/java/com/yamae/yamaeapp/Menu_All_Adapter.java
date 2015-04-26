package com.yamae.yamaeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HyunWook Kim on 2014-09-18.
 */
public class Menu_All_Adapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Menu_All_List_Data> data;
    private int layout;

    public Menu_All_Adapter(Context context, int layout, ArrayList<Menu_All_List_Data> data){
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout=layout;
    }

    @Override
    public int getCount() { return data.size(); }

    @Override
    public String getItem(int position) { return data.get(position).getName();}

    @Override
    public long getItemId(int position) {return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = inflater.inflate(layout, parent, false);
        }

        Menu_All_List_Data d=data.get(position);

        TextView name=(TextView)convertView.findViewById(R.id.restaurant_name);
        name.setText(d.getName());

        return convertView;
    }

}
