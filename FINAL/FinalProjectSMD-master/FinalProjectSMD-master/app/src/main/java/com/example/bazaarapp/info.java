package com.example.bazaarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.util.List;

public class info extends AppCompatActivity {

    private String ImageUrl;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private List<Brand> artistList;
    private TextView brandName;
    private TextView brandCategory;
    private TextView brandContact;
    private TextView brandTimings;
    private TextView brandAddress;
    //private Brand artist;
    private ImageView logo;
    private ImageButton crub;
    private static DatabaseReference mDatabaseRef;
    private static Brand artist;


    DatabaseReference dbBrands;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();
        String g=intent.getStringExtra("brandName");
        brandName=findViewById(R.id.titleProduct);
        logo=findViewById(R.id.imageBack);
        crub = findViewById(R.id.sub);
        crub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "You will now recieve it's notification! :))!", Toast.LENGTH_SHORT).show();
                //
                onSubscribe();
            }
        });

        Query query3 = FirebaseDatabase.getInstance().getReference("Registered Brands")
                .orderByChild("brandName")
                .equalTo(g);
        query3.addListenerForSingleValueEvent(valueEventListener);


        //////
        brandCategory=findViewById(R.id.infoProductType);



        /////



    }
    public static void onSubscribe() {

        FirebaseMessaging.getInstance().subscribeToTopic("/topics/"+artist.getBrandName());
       /* mDatabaseRef = FirebaseDatabase.getInstance().getReference("Subscribe");
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            //User is logged in
        }
        allower nope = new allower(Email, artist.getBrandName());
        String uploadId = mDatabaseRef.push().getKey();
        mDatabaseRef.child(uploadId).setValue(nope);
*/
    }


    public String getMyData() {

        return brandName.getText().toString();
    }


    public void backAcivity(View view){
        view.getContext().startActivity(new Intent(view.getContext(), home.class));
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          //  artistList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    artist = snapshot.getValue(Brand.class);
                    //artistList.add(artist);
                    Toast.makeText(getBaseContext(), artist.getContact() , Toast.LENGTH_SHORT ).show();
                    brandName.setText(artist.getBrandName());
                    brandCategory.setText(artist.getCategory());
                    Picasso.get().load(artist.getBrandLogo())
                            .error(R.drawable.home).into(logo);

                    //Bundle bundle = new Bundle();
                 //   bundle.putString("edttext", brandName.getText().toString());
// set Fragmentclass Arguments
                   // productInfo fragobj = new productInfo();
                    //fragobj.setArguments(bundle);
                }
                //adapter.notifyDataSetChanged();
                viewPagerfunction();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void viewPagerfunction() {

        viewPager=findViewById(R.id.pager);
        adapter=new ViewPagerAdapter(getSupportFragmentManager(),artist.getLatitude(),
                artist.getContact(),artist.getTimings(),artist.getLongitude(),artist.getBrandName());




        viewPager.setAdapter(adapter);

        tabLayout=findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);


    }
}
