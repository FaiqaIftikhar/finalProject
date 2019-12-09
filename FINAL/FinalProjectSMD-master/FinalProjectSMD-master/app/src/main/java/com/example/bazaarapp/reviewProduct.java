package com.example.bazaarapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class reviewProduct extends Fragment {


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<reviewClass> data;
    private String brandName;

    public reviewProduct() {
        // Required empty public constructor
    }

    public reviewProduct(String name){
        brandName=name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public static reviewProduct newInstance() {
        reviewProduct fragment = new reviewProduct();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_product, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.review_recycler_view);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        //

      //
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.addRev);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RatingBar ratingBar = new RatingBar(view.getContext());
                ratingBar.setStepSize((float) 0.5);


                Intent intent5=new Intent(getContext(),reviewActivity.class);
                intent5.putExtra("name",brandName);
                startActivity(intent5);
                Snackbar.make(view, "Add your own review", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
///////////////////////////////////
        recyclerView = (RecyclerView) view.findViewById(R.id.review_recycler_view);;
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

      //  verticalList.setHasFixedSize(true);
     //   verticalList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
       // verticalList.setItemAnimator(new DefaultItemAnimator());
        //mthirdRecyclerViewInHomes=new ArrayList<>();
        data = new ArrayList<reviewClass>();

        Query query3 = FirebaseDatabase.getInstance().getReference("UserReview")
                .orderByChild("brandName")
                .equalTo(brandName);
        query3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Toast.makeText(getBaseContext(), "in ONDataChange!" , Toast.LENGTH_SHORT ).show();
                    // ThirdRecyclerViewInHome upload1 = postSnapshot.getValue(ThirdRecyclerViewInHome.class);
                    reviewClass checker=postSnapshot.getValue(reviewClass.class);

                   // categoryData upload1=new categoryData(checker.getBrandName(),checker.getCategory(),1,checker.getBrandLogo(),checker.getBrandLogo());
                    data.add(checker);
                }

                adapter = new CustomAdapterReview(data);
                recyclerView.setAdapter(adapter);
                //  mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //   mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
//////////////////////////



        // Inflate the layout for this fragment
        return view;
    }


}
