package com.yamae.yamaeapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by songmho on 15. 7. 25.
 */
public class ListFragment extends Fragment{
    FrameLayout cur_container;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressbar;
    PackageManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cur_container=(FrameLayout)inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView=(RecyclerView)cur_container.findViewById(R.id.recyclerview);
        progressbar=(ProgressBar)cur_container.findViewById(R.id.progressbar);

        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle=getArguments();
        int cur_preg=bundle.getInt("cur_preg");
        switch (cur_preg) {
            case 0:
            makeList();
                break;
            case 1:
                progressbar.setVisibility(View.GONE);
                break;
            case 2:
                makeSetUpList();
                break;
        }

        return cur_container;
    }

    private void makeSetUpList() {
        progressbar.setVisibility(View.GONE);
        manager=getActivity().getPackageManager();
        String ver_num="";

        try {
            PackageInfo packageInfo=manager.getPackageInfo(getActivity().getPackageName(),0);
            ver_num=packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        ArrayList<More_listview_item> items=new ArrayList<>();
        More_listview_item send=new More_listview_item(0,"정보 수정사항 보내기");
        More_listview_item ver=new More_listview_item(1,"버젼 v "+ver_num);
        More_listview_item update=new More_listview_item(2,"2015.05.27 업데이트");

        items.add(send);
        items.add(ver);
        items.add(update);
        recyclerView.setAdapter(new RecyclerAdpater(getActivity(), items, R.layout.item_more_listview,0));

    }

    private void makeList() {
        progressbar.setVisibility(View.GONE);
        ArrayList<Category_listview_item> items=new ArrayList<>();

        Category_listview_item all=new Category_listview_item(R.drawable.all_icon,"모든메뉴");
        Category_listview_item dinning=new Category_listview_item(R.drawable.dinning_icon,"식당");
        Category_listview_item chicken=new Category_listview_item(R.drawable.chicken_icon,"치킨");
        Category_listview_item night=new Category_listview_item(R.drawable.night_icon,"야식");
        Category_listview_item drink=new Category_listview_item(R.drawable.drink_icon,"술집");
        Category_listview_item etc=new Category_listview_item(R.drawable.etc_icon,"기타");

        items.add(all);
        items.add(dinning);
        items.add(chicken);
        items.add(night);
        items.add(drink);
        items.add(etc);

        recyclerView.setAdapter(new RecyclerAdpater(getActivity(),items,R.layout.item_category_listview));
    }
}
