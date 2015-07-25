package com.yamae.yamaeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by songmho on 15. 7. 25.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    Fragment[] f=new Fragment[3];
    Bundle[] b=new Bundle[3];


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "목록";
            case 1:
                return "즐겨찾기";
            case 2:
                return "더보기";

        }
        return super.getPageTitle(position);
    }

    @Override
    public Fragment getItem(int position) {
        return f[position];
    }

    @Override
    public int getCount() {
        return f.length;
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        f[0]=new ListFragment();
        b[0]=new Bundle();
        b[0].putInt("cur_preg",0);
        f[0].setArguments(b[0]);


        f[1]=new ListFragment();
        b[1]=new Bundle();
        b[1].putInt("cur_preg",1);
        f[1].setArguments(b[1]);


        f[2]=new ListFragment();
        b[2]=new Bundle();
        b[2].putInt("cur_preg",2);
        f[2].setArguments(b[2]);
    }
}
