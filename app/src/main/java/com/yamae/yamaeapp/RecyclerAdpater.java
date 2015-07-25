package com.yamae.yamaeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by songmho on 15. 7. 25.
 */
public class RecyclerAdpater extends RecyclerView.Adapter<RecyclerAdpater.ViewHolder> {
    Context context;
    ArrayList<Category_listview_item> items_category;
    ArrayList<More_listview_item> items_more;
    int itemlayout;


    public RecyclerAdpater(Context context, ArrayList<Category_listview_item> items, int itemlayout) {
        this.context=context;
        this.items_category=items;
        this.itemlayout=itemlayout;
    }

    public RecyclerAdpater(Context context, ArrayList<More_listview_item> items, int itemlayout, int tmp) {
        this.context=context;
        this.items_more=items;
        this.itemlayout=itemlayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (itemlayout){
            case R.layout.item_category_listview:
                v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_listview,parent,false);
                return new ViewHolder(v,itemlayout);
            case R.layout.item_more_listview:
                v=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_listview,parent,false);
                return new ViewHolder(v,itemlayout);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        switch (itemlayout){
            case R.layout.item_category_listview:
                Category_listview_item item=items_category.get(position);
                holder.cate_name.setText(item.getName());
                holder.image.setImageResource(item.getImage());
                holder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context.getApplicationContext(),Menu_All_Activity.class);
                        switch (position) {
                            case 0:
                                intent.putExtra("category",0);
                                break;
                            case 1:
                                intent.putExtra("category",1);
                                break;
                            case 2:
                                intent.putExtra("category",2);
                               break;
                            case 3:
                                intent.putExtra("category",3);
                                 break;
                            case 4:
                                intent.putExtra("category",4);
                               break;
                            case 5:
                                intent.putExtra("category",5);
                                break;
                        }
                            context.startActivity(intent);
                    }
                });
                break;

            case R.layout.item_more_listview:
                More_listview_item item_more=items_more.get(position);
                holder.more_name.setText(item_more.getName());
                holder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        switch (itemlayout) {
            case R.layout.item_category_listview:
               return items_category.size();
            case R.layout.item_more_listview:
                return items_more.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout container;
        TextView cate_name;
        ImageView image;

        TextView more_name;
        public ViewHolder(View itemView, int itemlayout) {
            super(itemView);
            switch (itemlayout){
                case R.layout.item_category_listview:
                    container=(LinearLayout)itemView.findViewById(R.id.container);
                    cate_name=(TextView)itemView.findViewById(R.id.cate_name);
                    image=(ImageView)itemView.findViewById(R.id.image);
                    break;
                case R.layout.item_more_listview:
                    container=(LinearLayout)itemView.findViewById(R.id.container);
                    more_name=(TextView)itemView.findViewById(R.id.more_name);
                    break;

            }
        }
    }
}
