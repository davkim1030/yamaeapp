package com.yamae.yamaeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by songmho on 2014-11-03.
 */
public class Detail_listview_Adapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Detail_listview_item> data;
    private int layout;

    public Detail_listview_Adapter(Context context, int layout, ArrayList<Detail_listview_item> data){
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout=layout;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = inflater.inflate(layout, parent, false);
        }

        Detail_listview_item d=data.get(position);


        TextView name=(TextView)convertView.findViewById(R.id.menu_name);
        TextView price=(TextView)convertView.findViewById(R.id.menu_price);
        //TextView timed=(TextView)convertView.findViewById(R.id.menu_timed);
        //TextView deliver=(TextView)convertView.findViewById(R.id.menu_deliver);
        //TextView car=(TextView)convertView.findViewById(R.id.menu_car);

        name.setText(d.getName());
        price.setText(d.getPrice());
        return convertView;
    }
}
