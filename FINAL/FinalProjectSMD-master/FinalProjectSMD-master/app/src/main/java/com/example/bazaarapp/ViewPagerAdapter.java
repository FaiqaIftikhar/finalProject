package com.example.bazaarapp;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    String timings;
    Double address;
    Double address2;
    String contact;
    String brandName;


    public ViewPagerAdapter(FragmentManager fm){
        super(fm);


    }

    public ViewPagerAdapter(FragmentManager fm,Double lat,String contact,String time,Double address2,String name){
        super(fm);

        this.brandName=name;
        this.timings=time;
        this.address=lat;
        this.contact=contact;
        this.address2=address2;

    }



    @Override
    public Fragment getItem(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new productInfo(address,contact,timings,address2);
                break;
            case 1:
                fragment = new reviewProduct(brandName);
                break;


        }
        return fragment;
    }
    @Override
    public int getCount(){
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        // position=position+1;
        CharSequence arr=null;
        switch(position) {
            case 0:
                arr = "Info";
                break;

            case 1:
                arr = "Review";
                break;

        }

        return arr;
    }
}
