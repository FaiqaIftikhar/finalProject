package com.example.bazaarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    Button button;
    TextView textview;
    FirebaseAuth mAuth;

    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setContentView(R.layout.activity_home);
        mAuth=FirebaseAuth.getInstance();

        email=findViewById(R.id.editText3);
        password=findViewById(R.id.editText4);
        addListenerOnTextView();
        addListenerOnButton();

    }

    public void addListenerOnTextView() {

        final Context context = this;

        textview = (TextView) findViewById(R.id.textViewSignup_btn);

        textview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                Intent intent = new Intent(context, activitySignup.class);
                //  intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }

        });


    }


    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.buttonLogin);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(context,"Please enter your Email Address to continue.",Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(Password)){
                    Toast.makeText(context,"Please enter your password to continue.",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    Toast.makeText(context,"Please enter a valid Email address!",Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent intent = new Intent(getApplicationContext(), home.class);
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(context,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });



            }

        });

    }
}
