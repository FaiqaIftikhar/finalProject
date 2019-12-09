package com.example.bazaarapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference mDatabaseRef;
    DatabaseReference mmDatabaseRef;
    List<DealPoster> mdeals;
    List<ThirdRecyclerViewInHome>mthirdRecyclerViewInHomes;
    RecyclerView mRecyclerview;
    RecyclerView verticalList;
    DealAdapter mAdapter;
    ThirdRecyclerViewInHomeAdapter mmAdapter;
    TextView textCartItemCount;
    int mCartItemCount = 10;
    ViewFlipper flipper;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        int [] images={R.drawable.ad1,R.drawable.ad2,R.drawable.ad3};

        flipper= findViewById(R.id.flipper);
        for(int image:images){
            flipperImages(image);

        }



        RecyclerView typeList= findViewById(R.id.typeList);
        typeList.setHasFixedSize(true);
        typeList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        typeList.setItemAnimator(new DefaultItemAnimator());
        String[] languages={"Food and Beverages","Electronics","Apparel and Footwear","Home and Living","Other Services"};

        int [] imageViews={R.drawable.food,R.drawable.electronics,R.drawable.apparel,R.drawable.home,R.drawable.others};

        typeList.setAdapter(new adapterMe(languages,imageViews) );



        /////////RECYCLER VIEW 2


     //   DealAdapter mAdapter;



        mRecyclerview=findViewById(R.id.pickList);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mdeals=new ArrayList<>();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Deal Poster");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                   // Toast.makeText(getBaseContext(), "in ONDataChange!" , Toast.LENGTH_SHORT ).show();
                    DealPoster upload = postSnapshot.getValue(DealPoster.class);
                    mdeals.add(upload);
                }

                mAdapter = new DealAdapter(home.this, mdeals);
                  mRecyclerview.setAdapter(mAdapter);
              //  mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(home.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
             //   mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

/*

        RecyclerView pickList=findViewById(R.id.pickList);
        pickList.setHasFixedSize(true);
        pickList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        pickList.setItemAnimator(new DefaultItemAnimator());
        String [] a1={"Mc Chicken Deal","Friday Deal","Clearance Sale","Summer Sale"};
        String [] a2={"McDonald's","Hardees","Junaid Jamshed","ShoeBox"};


        int [] i1={R.drawable.deal1,R.drawable.deal2,R.drawable.deal3,R.drawable.apparel};
        int [] i2={R.drawable.logo1,R.drawable.logo2,R.drawable.logo3,R.drawable.logo4};
        pickList.setAdapter(new pickAdapter(a1,i1,a2,i2));

*/
        /////RECYCELR VIEW 3

        /*
        RecyclerView verticalList=findViewById(R.id.verticalList);
        verticalList.setHasFixedSize(true);
        verticalList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        verticalList.setItemAnimator(new DefaultItemAnimator());

        String[] v1={"Kids Fashion City","Deja VU Men's Collection","Ali Pharmacy","Shah Curtains","Noor Electric Company"};
        String [] v2={"Toys and Gifts","Apparel and Footwear","Health and Fitness","Home and Living","Electronics"};
        int [] vi1={R.drawable.kidfashion,R.drawable.menclothes,R.drawable.pharmacy ,R.drawable.shahcurtain,R.drawable.lapnoor};
        int[] vi2={R.drawable.robotic,R.drawable.shopping_bag,R.drawable.medicine,R.drawable.homeicon,R.drawable.electricicon};


        //verticalList.setAdapter();
        verticalList.setAdapter(new verticalAdapter(v1,vi1,v2,vi2));*/

        verticalList=findViewById(R.id.verticalList);
        verticalList.setHasFixedSize(true);
        verticalList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        verticalList.setItemAnimator(new DefaultItemAnimator());
        mthirdRecyclerViewInHomes=new ArrayList<>();
        mmDatabaseRef= FirebaseDatabase.getInstance().getReference("Registered Brands");
        mmDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Toast.makeText(getBaseContext(), "in ONDataChange!" , Toast.LENGTH_SHORT ).show();
                   // ThirdRecyclerViewInHome upload1 = postSnapshot.getValue(ThirdRecyclerViewInHome.class);
                    Brand checker=postSnapshot.getValue(Brand.class);
                    ThirdRecyclerViewInHome upload1=new ThirdRecyclerViewInHome(checker.getBrandLogo(),checker.getBrandName(),checker.getCategory());
                    mthirdRecyclerViewInHomes.add(upload1);
                }

                mmAdapter = new ThirdRecyclerViewInHomeAdapter(home.this, mthirdRecyclerViewInHomes);

                verticalList.setAdapter(mmAdapter);
                //  mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(home.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //   mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void flipperImages(int image){

        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        flipper.addView(imageView);
        flipper.setFlipInterval(4000); //4 seconds interval
        flipper.setAutoStart(true);

        flipper.setInAnimation(this,android.R.anim.slide_in_left);
        flipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }
    public void addToFavourite(View view){
        Toast.makeText(getBaseContext(), "Added to favourites!" , Toast.LENGTH_SHORT ).show();
    }
    public void addLocation(View view){
        Snackbar.make(view, "Change your location!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();
        actionView.setOnClickListener(this);


        return true;
    }
    @Override
    public void onClick(View v) {



        Intent startNewScreen=new Intent(this,notification.class);
        startActivity(startNewScreen);
        //  switch (v.getId() /to get clicked view id*/) {
        // case R.id.cardviewVerlist:



        // do something when the corky is clicked

        //   break;

        //    default:
//
        //       break;
        //  }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_add:
                Intent intent = new Intent(this, poster.class);
                startActivity(intent);
                //  getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout,new shit()).commit();
                Toast.makeText(getBaseContext(), "Post your add now!!!!" , Toast.LENGTH_SHORT ).show();
                break;
            case R.id.nav_brand:
                Intent intent7 = new Intent(this, BrandActivity.class);
                startActivity(intent7);
                //  getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout,new shit()).commit();
                Toast.makeText(getBaseContext(), "Register your brand NOW!" , Toast.LENGTH_SHORT ).show();
                break;
            case R.id.nav_favourites:
                Intent intent1 = new Intent(this, favourite.class);
                startActivity(intent1);
                //  getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout,new shit()).commit();
                Toast.makeText(getBaseContext(), "Here are the favourites!" , Toast.LENGTH_SHORT ).show();
                break;
            case R.id.nav_logout:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                //  getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout,new shit()).commit();
                Toast.makeText(getBaseContext(), "You have Logged out!" , Toast.LENGTH_SHORT ).show();
                break;
            case R.id.nav_user:
                Intent intent3 = new Intent(this, userProfile.class);
                startActivity(intent3);
                //  getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout,new shit()).commit();
                Toast.makeText(getBaseContext(), "Here is your Profile" , Toast.LENGTH_SHORT ).show();
                break;
            case R.id.nav_profile:
                Intent intent4 = new Intent(this, subscription.class);
                startActivity(intent4);
                //  getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout,new shit()).commit();
                Toast.makeText(getBaseContext(), "Here are your subscriptions" , Toast.LENGTH_SHORT ).show();
                break;
            case R.id.retrofit:
                Intent intent5=new Intent(this,retrofit.class);
                startActivity(intent5);
                Toast.makeText(getBaseContext(), "Here is retrofit , json object" , Toast.LENGTH_SHORT ).show();
                break;



        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}