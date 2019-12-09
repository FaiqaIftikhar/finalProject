package com.example.bazaarapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class BrandActivity extends AppCompatActivity {
    private ImageView image;
    private EditText Name,category,description,timings,contact;
    private Button b1,b2,b3;
    private Uri mImageUri;
    private Double longitude;
    private Double latitude;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask; //to prevent multiple uploads

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        image=findViewById(R.id.brandPoster);
        Name=findViewById(R.id.brandName);
        category=findViewById(R.id.brandCategory);
        description=findViewById(R.id.description);
        contact=findViewById(R.id.contact);
        timings=findViewById(R.id.timings);
        b1=findViewById(R.id.uploadBrandPic);
        b2=findViewById(R.id.uploadBrandLoc);
        b3=findViewById(R.id.submitBrand);

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude",0.0);
        longitude=intent.getDoubleExtra("longitude",0.0);
        String msg="Updated Location: "+
                Double.toString(latitude)+","+ Double.toString(longitude);
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        //text.setText(str);
        mStorageRef= FirebaseStorage.getInstance().getReference("Registered Brands");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Registered Brands");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                uploadPic();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                uploadLocation();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(getBaseContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
                }
                else {
                    //     Toast.makeText(getBaseContext(), "ON clickkk!", Toast.LENGTH_SHORT).show();
                    upload();
                }
            }

        });

    }
    private void uploadPic() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
        startActivityForResult(intent,1) ;
    }
    private void uploadLocation() {

        startActivity(new Intent(this,MapActivity.class));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(image);
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void upload() {

        Toast.makeText(getBaseContext(), "in upload function!" , Toast.LENGTH_SHORT ).show();
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Uploading.....");
            progressDialog.show();


            // StorageReference reference=storageReference.child("images/"+ UUID.randomUUID().toString());
            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Image Added!" , Toast.LENGTH_SHORT ).show();

                            Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                            while(!uriTask.isSuccessful());
                            Uri downloadUri=uriTask.getResult();
                            Brand b = new Brand(Name.getText().toString().trim(),
                                    category.getText().toString().trim(),
                                    downloadUri.toString(),
                                    longitude,
                                    latitude,
                                    timings.getText().toString().trim(),
                                    contact.getText().toString().trim()
                                    );

                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(b);
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                            double progres=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progres+"%");
                        }
                    });
        }
    }
    public void backAcivity(View view){
        view.getContext().startActivity(new Intent(view.getContext(), home.class));
    }
    /*
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        Intent intent = getIntent();
        Double latitude = intent.getDoubleExtra("latitude",0.0);
        Double longitude=intent.getDoubleExtra("longitude",0.0);
        String msg="Updated Location: "+
                Double.toString(latitude)+","+ Double.toString(longitude);
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

    }*/

}
