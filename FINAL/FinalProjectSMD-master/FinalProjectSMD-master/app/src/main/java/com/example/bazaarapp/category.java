package com.example.bazaarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class category extends AppCompatActivity {

    private Toolbar toolbar;
    private static RecyclerView.Adapter adapter;
    private static ArrayList<categoryData> data;
    String category;
    RecyclerView verticalList;
    DatabaseReference mDatabaseRef;
    private Brand artist;
    private TextView brandName;
    private TextView brandCategory;
    private ImageView logo;
    private ViewPager viewPager;
    //private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        category=intent.getStringExtra("categoryName");
        TextView tv=findViewById(R.id.categoryHead);
        tv.setText(category);
        //toolbar

        ////faiqa

        verticalList=findViewById(R.id.categoryList1);
        verticalList.setHasFixedSize(true);
        verticalList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        verticalList.setItemAnimator(new DefaultItemAnimator());
        //mthirdRecyclerViewInHomes=new ArrayList<>();
        data = new ArrayList<categoryData>();

        Query query3 = FirebaseDatabase.getInstance().getReference("Registered Brands")
                .orderByChild("category")
                .equalTo(category);
        query3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Toast.makeText(getBaseContext(), "in ONDataChange!" , Toast.LENGTH_SHORT ).show();
                    // ThirdRecyclerViewInHome upload1 = postSnapshot.getValue(ThirdRecyclerViewInHome.class);
                    Brand checker=postSnapshot.getValue(Brand.class);

                    categoryData upload1=new categoryData(checker.getBrandName(),checker.getCategory(),1,checker.getBrandLogo(),checker.getBrandLogo());
                    data.add(upload1);
                }

                adapter = new categoryAdapter(data);
                verticalList.setAdapter(adapter);
                //  mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(category.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //   mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

        ////faiqa



    }

    public void backAcivity(View view){
        view.getContext().startActivity(new Intent(view.getContext(), home.class));
    }
    public void addToFavourite(View view){
        Toast.makeText(getApplicationContext(), "Added to favourites!" , Toast.LENGTH_SHORT ).show();
    }

}