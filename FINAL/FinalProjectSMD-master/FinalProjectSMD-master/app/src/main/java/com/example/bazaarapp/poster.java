package com.example.bazaarapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class poster extends AppCompatActivity {
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

    //
    EditText edtTitle;
    EditText edtMessage;
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAQJD00fs:APA91bHy6NdAL1JcXSeVeTM0Bnmcjg_6edOPdtI-b9AA4QdabAmC10WAsQjFIqdtWepnAUW7lbZF_mfg6w58GfqvYMJGkApc-OfjQIzVmjTCIdImfSzfoQ6dRcMnJdmglCyeFbs8-bFT";
    final private String contentType = "application/json";
    final String TAG11 = "NOTIFICATION TAG";



    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);
        image=findViewById(R.id.DealPoster);
        imageName=findViewById(R.id.picName);
        description=findViewById(R.id.description);
        brandName=findViewById(R.id.brand);

        pathArray=new ArrayList<>();
        b1=findViewById(R.id.uploadAddPic);
        b2=findViewById(R.id.submitAdd);
        //

        edtTitle = findViewById(R.id.brand);
        edtMessage = findViewById(R.id.description);
        //   FirebaseMessaging.getInstance().subscribeToTopic("/topics/bazaarApp");
        //

        mStorageRef=FirebaseStorage.getInstance().getReference("Deal Poster");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Deal Poster");


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
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(getBaseContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
                }
                else {
                    //     Toast.makeText(getBaseContext(), "ON clickkk!", Toast.LENGTH_SHORT).show();
                    upload();
                }

            }
        });
        //mStorageRef= FirebaseStorage.getInstance().getReference();

        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        // checkFilePermissions();


    }

    public void backAcivity(View view){
        view.getContext().startActivity(new Intent(view.getContext(), home.class));
    }

    private void uploadPic() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
        startActivityForResult(intent,1) ;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(image);
/*
            try {
                Bitmap  bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

 */


        }
    }

    /*  private void checkFilePermissions() {
          if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
              int permissionCheck = poster.this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
              permissionCheck += poster.this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
              if (permissionCheck != 0) {
                  this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
              }
          }else{
              Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
          }
      }
  */
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

                            /////////////////////


                            // Toast.makeText(getApplicationContext(), info.onSubscribe().getSubs().get(0).getBrandName() , Toast.LENGTH_LONG ).show();
                            //  Toast.makeText(getApplicationContext(), brandName.getText().toString().trim() , Toast.LENGTH_LONG ).show();

                            // if(info.onSubscribe().getSubs().get(0).getBrandName()!=null) {

                            //    if (info.onSubscribe().getSubs().get(0).getBrandName().trim().equals(brandName.getText().toString().trim())) {
                            TOPIC = "/topics/bazaarApp"; //topic must match with what the receiver subscribed to

                            TOPIC="/topics/"+ brandName.getText().toString().trim();
                            NOTIFICATION_TITLE = edtTitle.getText().toString();
                            NOTIFICATION_MESSAGE = edtMessage.getText().toString();

                            JSONObject notification = new JSONObject();
                            JSONObject notifcationBody = new JSONObject();
                            try {
                                notifcationBody.put("title", NOTIFICATION_TITLE);
                                notifcationBody.put("message", NOTIFICATION_MESSAGE);

                                notification.put("to", TOPIC);
                                notification.put("data", notifcationBody);
                            } catch (JSONException e) {
                                Log.e(TAG11, "onCreate: " + e.getMessage());
                            }
                            sendNotification(notification);
                            // }
                            //   }

                            ////////////////////
                            Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                            while(!uriTask.isSuccessful());
                            Uri downloadUri=uriTask.getResult();
                            DealPoster deal = new DealPoster(downloadUri.toString(),
                                    brandName.getText().toString().trim(),
                                    imageName.getText().toString().trim(),
                                    description.getText().toString().trim());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(deal);
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

    public void checker(View view) {
        Toast.makeText(getApplicationContext(), "HEREEE!" , Toast.LENGTH_SHORT ).show();
    }
    private void sendNotification(JSONObject notification){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        edtTitle.setText("");
                        edtMessage.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(poster.this, "Request error in ihere", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };


        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}
