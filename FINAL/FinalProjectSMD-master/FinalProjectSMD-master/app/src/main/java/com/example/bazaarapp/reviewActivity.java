package com.example.bazaarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;

public class reviewActivity extends AppCompatActivity {

    private static final String TAG = "PosterActivity";
    //private int Gallery_intent=2;
    private ImageView image;
    private EditText imageName,description,brandName;
    private Button b1,b2;
    private Uri mImageUri;
    private final static int mWidth=512;
    private final static int mLength=512;
    private ArrayList<String> pathArray;
    private int ArrayPosition;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    private FirebaseAuth auth;
    private StorageTask mUploadTask; //to prevent multiple uploads
    /////
    private RatingBar ratingBar;
    private Button ratingbtn;

    private float rating;
    private String userEmail;
    /////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Intent startingIntent = getIntent();
        final String whatYouSent = startingIntent.getStringExtra("name");

        ratingBar=findViewById(R.id.ratingBar2);
        ratingbtn=findViewById(R.id.ratingbtn);

        ratingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float msg=ratingBar.getRating();
                mDatabaseRef= FirebaseDatabase.getInstance().getReference("UserReview");

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    //User is logged in
                }
                reviewClass nope = new reviewClass(userEmail,msg,whatYouSent);
                String uploadId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(uploadId).setValue(nope);

               // Toast.makeText(getApplicationContext(),whatYouSent,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Thanks for the review!",Toast.LENGTH_SHORT).show();
             //   Toast.makeText(getApplicationContext(),String.valueOf(ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            }
        });








    }

    public void backAcivity(View view){
        view.getContext().startActivity(new Intent(view.getContext(), home.class));
    }
}
