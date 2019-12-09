package com.example.bazaarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        RecyclerView verticalList=findViewById(R.id.notifyList);
        verticalList.setHasFixedSize(true);
        verticalList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        verticalList.setItemAnimator(new DefaultItemAnimator());

        String[] v1={"HurryUP\\nLast Day Of Offer!","HurryUP\\nLast Day Of Offer!"};
        //String [] v2={"Toys and Gifts","Apparel and Footwear"};
        int [] vi1={R.drawable.logo1,R.drawable.logo1};
        //int[] vi2={R.drawable.robotic,R.drawable.shopping_bag};


        //verticalList.setAdapter();
        verticalList.setAdapter(new notifyAdapter(v1,vi1));
        verticalList.setNestedScrollingEnabled(false);
    }
}
